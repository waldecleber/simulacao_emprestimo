package com.tnt.emprestimo.service;

import java.math.BigDecimal;
import java.util.List;

import com.tnt.emprestimo.entity.Cliente;
import com.tnt.emprestimo.entity.dto.ClienteDTO;

public interface ClienteService {
	
	List<ClienteDTO> listarClientes();
	
	List<ClienteDTO> buscarCliente(String name);
	
	ClienteDTO salvar(ClienteDTO cliente);
	
	String calcularRisco(BigDecimal rendimentoMensal);

	void excluir(Long id);

	ClienteDTO buscarClientePorId(Long id);

}
