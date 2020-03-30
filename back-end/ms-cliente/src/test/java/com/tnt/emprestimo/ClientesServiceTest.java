package com.tnt.emprestimo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.tnt.emprestimo.entity.Cliente;
import com.tnt.emprestimo.entity.dto.ClienteDTO;
import com.tnt.emprestimo.exception.ClienteNaoEncontradoException;
import com.tnt.emprestimo.repository.ClienteRepository;
import com.tnt.emprestimo.service.impl.ClienteServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ClientesServiceTest {
	
	@InjectMocks
	private ClienteServiceImpl clienteService;

	@Mock
	private ClienteRepository clienteRepository;
	
	@Test
	public void listaTodosClientes() {
		when(clienteRepository.buscarClientePorNome(anyString()))				
				.thenReturn(List.of(Cliente.builder().build()));
		clienteService.buscarCliente("Cliente 01");
		verify(clienteRepository, times(1)).buscarClientePorNome(anyString());
	}
	
	@Test
	public void salvaClienteComumRiscoC() {
		ClienteDTO dto = ClienteDTO.builder()
				.id(1L)
				.nome("Joao da Silva")
				.endereco("Rua A, Bairro Novo")
				.rendimentoMensal(new BigDecimal(2000))
				.tipo("comum")
				.empregado(Boolean.TRUE)
				.build();
		
		Cliente cliente = buildCliente();
		
		when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);		
		dto = clienteService.salvar(dto);		
		verify(clienteRepository, times(1)).save(cliente);
		assertEquals("C", dto.getRisco());
	}
	
	@Test
	public void salvaClienteEspecialRiscoB() {
		ClienteDTO dto = ClienteDTO.builder()
				.id(1L)
				.nome("Maria da Silva")
				.endereco("Rua do passarinho, Bairro Novo")
				.rendimentoMensal(new BigDecimal(8000))
				.tipo("especial")
				.vlrTotalPatrimonio(135000.00)
				.build();
		
		Cliente cliente = buildCliente();
		
		when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);		
		dto = clienteService.salvar(dto);		
		verify(clienteRepository, times(1)).save(cliente);
		assertEquals("B", dto.getRisco());
	}
	
	@Test
	public void salvaClientePotencialRiscoA() {
		ClienteDTO dto = ClienteDTO.builder()
				.id(1L)
				.nome("Antonio da Silva")
				.endereco("Rua das abelhas, Bairro Novo")
				.rendimentoMensal(new BigDecimal(18000))
				.tipo("potencial")
				.vlrDividasAtuais(230000.00)
				.build();
		
		Cliente cliente = buildCliente();
		
		when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);		
		dto = clienteService.salvar(dto);		
		verify(clienteRepository, times(1)).save(cliente);
		assertEquals("A", dto.getRisco());
	}

	
	@Test
	public void obtemRiscoDoClientePeloRendimentoMensal() {
		Cliente cliente = buildCliente();
		
		String risco = clienteService.calcularRisco(cliente.getRendimentoMensal());		
		assertEquals("A", risco);
	}
	
	@Test
	public void deletarUmCliente() {
		ClienteDTO dto = ClienteDTO.builder()
				.id(1L)
				.nome("Antonio da Silva")
				.endereco("Rua das abelhas, Bairro Novo")
				.rendimentoMensal(new BigDecimal(18000))
				.tipo("potencial")
				.vlrDividasAtuais(230000.00)
				.build();
		
		Cliente cliente = buildCliente();
		
		when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);		
		clienteService.salvar(dto);	
		
		when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));		
		clienteService.excluir(dto.getId());
		
		verify(clienteRepository, times(1)).delete(cliente);
	}
	
	@Test(expected = ClienteNaoEncontradoException.class)
	public void clienteNaoEncontradoExceptionQuandoDeleta() {		
		Long id = 2L;
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());		
		clienteService.excluir(id);
	}
	
	private Cliente buildCliente() {
		return Cliente.builder()
				.id(1L)
				.nome("Joao da Silva")
				.endereco("Rua A, Bairro Novo")
				.rendimentoMensal(new BigDecimal(12000.00))
				.tipo("comum")
				.empregado(Boolean.TRUE)
				.build();
	}
}
