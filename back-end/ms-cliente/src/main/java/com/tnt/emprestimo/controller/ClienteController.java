package com.tnt.emprestimo.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tnt.emprestimo.entity.dto.ClienteDTO;
import com.tnt.emprestimo.service.ClienteService;

import lombok.RequiredArgsConstructor;

/**
 * Controller utilizado para prover endpoints para a camada de frontend.
 * @author waldecleber
 *
 */
@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteController {
	
	private final ClienteService clienteService;
	
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> listarClientes() {		
		return ResponseEntity.ok().body(clienteService.listarClientes());
	}
	
	@CrossOrigin
	@GetMapping(path = "/search/{nome}")
	public ResponseEntity<List<ClienteDTO>> buscarCliente(@PathVariable(name = "nome", required = false) String nome) {		
		return ResponseEntity.ok().body(clienteService.buscarCliente(nome));
	}
	
	@CrossOrigin
	@GetMapping(path = "/{id}")
	public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable(name = "id") Long id) {		
		return ResponseEntity.ok().body(clienteService.buscarClientePorId(id));
	}
	
	@CrossOrigin
	@GetMapping(path = "/risco")
	public ResponseEntity<String> calcularRisco(@RequestParam(name = "rendimentoMensal") BigDecimal rendimentoMensal) {		
		return ResponseEntity.ok().body(clienteService.calcularRisco(rendimentoMensal));
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<ClienteDTO> salvar(@RequestBody ClienteDTO cliente) {    	
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvar(cliente));			
	}
	
	@CrossOrigin
	@DeleteMapping("/{id}")
	public Map<String, Boolean> excluir(@PathVariable Long id) {
		clienteService.excluir(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
