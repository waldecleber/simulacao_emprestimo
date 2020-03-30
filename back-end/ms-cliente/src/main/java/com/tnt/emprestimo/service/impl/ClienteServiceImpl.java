package com.tnt.emprestimo.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tnt.emprestimo.converter.ClienteApiConverter;
import com.tnt.emprestimo.entity.Cliente;
import com.tnt.emprestimo.entity.dto.ClienteDTO;
import com.tnt.emprestimo.exception.ClienteNaoEncontradoException;
import com.tnt.emprestimo.repository.ClienteRepository;
import com.tnt.emprestimo.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	private static final BigDecimal RENDIMENTO_RISCO_8000 = new BigDecimal(8000);
	private static final BigDecimal RENDIMENTO_RISCO_2000 = new BigDecimal(2000);
	
	@Autowired
	private ClienteRepository clienteRepository;

	/**
	 * Método utilizado para buscar todos os cliente do banco.
	 */
	@Override
	public List<ClienteDTO> listarClientes() {
		List<Cliente> listaClientes = clienteRepository.findAll();		
		return ClienteApiConverter.mapList(listaClientes, ClienteDTO.class);	
	}
	
	/**
	 * Realiza busca no banco pelo nome do cliente.
	 */
	@Override
	public List<ClienteDTO> buscarCliente(String name) {		
		List<Cliente> listaClientes = clienteRepository.buscarClientePorNome(name);		
		return ClienteApiConverter.mapList(listaClientes, ClienteDTO.class);		
	}

	/**
	 * Salva os dados do cliente no banco.
	 */
	@Override
	public ClienteDTO salvar(ClienteDTO dto) {
		Cliente cliente = Cliente.builder().build();		
		BeanUtils.copyProperties(dto, cliente);
		cliente.setRisco(calcularRisco(cliente.getRendimentoMensal()));
		
		clienteRepository.save(cliente);
		return ClienteApiConverter.map(cliente, ClienteDTO.class);
	}

	/**
	 * Método para calcular o Risco do cliente a partir do rendimento mensal.
	 */
	@Override
	public String calcularRisco(BigDecimal rendimentoMensal) {		
		if (bigDecimalMenorOuIgual(rendimentoMensal, RENDIMENTO_RISCO_2000)) {
			return "C";
		} else if ((rendimentoMensal.compareTo(RENDIMENTO_RISCO_2000) > 0)
				&& (bigDecimalMenorOuIgual(rendimentoMensal, RENDIMENTO_RISCO_8000))) {
			return "B";
		} 		
		return "A";
		
	}

	private boolean bigDecimalMenorOuIgual(BigDecimal rendimentoMensal, BigDecimal risco) {
		return rendimentoMensal.compareTo(risco) < 0 || rendimentoMensal.equals(risco);
	}

	/**
	 * Remove um cliente do banco.
	 */
	@Override
	public void excluir(Long id) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new ClienteNaoEncontradoException("Não foi encontrado nenhum cliente com este id."));
		clienteRepository.delete(cliente);		
	}

	/**
	 * Realiza uma busca no banco por id do cliente.
	 */
	@Override
	public ClienteDTO buscarClientePorId(Long id) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new ClienteNaoEncontradoException("Não foi encontrado nenhum cliente com este id."));
		return ClienteApiConverter.map(cliente, ClienteDTO.class);
	}
	
}
