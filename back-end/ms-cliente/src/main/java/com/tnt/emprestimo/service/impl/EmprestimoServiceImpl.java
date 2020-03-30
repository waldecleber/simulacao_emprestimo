package com.tnt.emprestimo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tnt.emprestimo.entity.Cliente;
import com.tnt.emprestimo.exception.ClienteNaoEncontradoException;
import com.tnt.emprestimo.exception.RiscoForaDaFaixaException;
import com.tnt.emprestimo.repository.ClienteRepository;
import com.tnt.emprestimo.service.EmprestimoService;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	/**
	 * Método utilizado para calcular a taxa de juros para a simulação de empréstimo.
	 * @throws RiscoForaDaFaixaException 
	 */
	public Double calcularJuros(Long idCliente) {		
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new ClienteNaoEncontradoException("Não foi encontrado cliente com este id."));
		
		Double juros = null;
		
		switch (cliente.getRisco()) {
		case "A":
			juros = 1.9;
			break;
		case "B":
			juros = 5.0;
			break;
		case "C":
			juros = 10.0;
			break;
			
		default:
			throw new RiscoForaDaFaixaException("Risco fora da faixa.");
		}
			
		return juros;
	}

}
