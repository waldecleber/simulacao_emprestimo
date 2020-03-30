package com.tnt.emprestimo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tnt.emprestimo.service.EmprestimoService;

import lombok.RequiredArgsConstructor;

/**
 * Controller utilizado para prover endpoints para a camada de frontend.
 * @author waldecleber
 *
 */
@RestController
@RequestMapping("/emprestimos")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SimulacaoEmprestimoController {
	
	private final EmprestimoService emprestimoService;
	
	@CrossOrigin
	@GetMapping(path = "/calcularJuros/{id}")
	public ResponseEntity<Double> calcularJuros(@PathVariable(name = "id") Long idCliente) {
		return ResponseEntity.ok().body(emprestimoService.calcularJuros(idCliente));
	}

}
