package com.tnt.emprestimo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.tnt.emprestimo.converter.ClienteApiConverter;
import com.tnt.emprestimo.entity.Cliente;
import com.tnt.emprestimo.entity.dto.ClienteDTO;
import com.tnt.emprestimo.exception.RiscoForaDaFaixaException;
import com.tnt.emprestimo.repository.ClienteRepository;
import com.tnt.emprestimo.service.impl.ClienteServiceImpl;
import com.tnt.emprestimo.service.impl.EmprestimoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmprestimoServiceTest {

	@InjectMocks
	private EmprestimoServiceImpl emprestimoService;
	
	@InjectMocks
	private ClienteServiceImpl clienteService;
	
	@Mock
	private ClienteRepository clienteRepository;
	
	@Test
	public void realizaSimulacaoDeEmprestimoParaClienteRiscoA() {
		ClienteDTO dto = ClienteDTO.builder()
				.id(1L)
				.nome("Joao da Silva")
				.endereco("Rua A, Bairro Novo")
				.rendimentoMensal(new BigDecimal(12000))
				.empregado(Boolean.TRUE)
				.build();
		
		Cliente cliente = Cliente.builder()
				.build();
		
		when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);	
		dto = clienteService.salvar(dto);
		
		cliente = ClienteApiConverter.map(dto, Cliente.class);
		
		when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
		Double juros;
		try {
			juros = emprestimoService.calcularJuros(cliente.getId());
			assertEquals(1.9, juros);
		} catch (RiscoForaDaFaixaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void realizaSimulacaoDeEmprestimoParaClienteRiscoB() {
		ClienteDTO dto = ClienteDTO.builder()
				.id(1L)
				.nome("Joao da Silva")
				.endereco("Rua A, Bairro Novo")
				.rendimentoMensal(new BigDecimal(7500))
				.empregado(Boolean.TRUE)
				.build();
		
		Cliente cliente = Cliente.builder().build();
		
		when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);	
		dto = clienteService.salvar(dto);		
		cliente = ClienteApiConverter.map(dto, Cliente.class);

		when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
		Double juros = emprestimoService.calcularJuros(cliente.getId());
		
		assertEquals(5.0, juros);
	}
	
	@Test
	public void realizaSimulacaoDeEmprestimoParaClienteRiscoC() {
		ClienteDTO dto = ClienteDTO.builder()
				.id(1L)
				.nome("Joao da Silva")
				.endereco("Rua A, Bairro Novo")
				.rendimentoMensal(new BigDecimal(1700))
				.empregado(Boolean.TRUE)
				.build();
		
		Cliente cliente = Cliente.builder().build();
		
		when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);	
		dto = clienteService.salvar(dto);		
		cliente = ClienteApiConverter.map(dto, Cliente.class);
		
		when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
		Double juros = emprestimoService.calcularJuros(cliente.getId());
		
		assertEquals(10.0, juros);
	}
	
	@Test(expected = RiscoForaDaFaixaException.class)
	public void realizaSimulacaoDeEmprestimoJurosException() {
		Cliente cliente = Cliente.builder()
				.id(1L)
				.nome("Joao da Silva")
				.endereco("Rua A, Bairro Novo")
				.rendimentoMensal(new BigDecimal(7500))
				.empregado(Boolean.TRUE)
				.risco("D")
				.build();

		when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
		Double juros = emprestimoService.calcularJuros(cliente.getId());
		
		assertEquals(5.0, juros);
	}
}
