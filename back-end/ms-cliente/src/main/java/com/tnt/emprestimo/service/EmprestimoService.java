package com.tnt.emprestimo.service;

import com.tnt.emprestimo.exception.RiscoForaDaFaixaException;

public interface EmprestimoService {
	
	Double calcularJuros(Long idCliente) throws RiscoForaDaFaixaException;
	
}
