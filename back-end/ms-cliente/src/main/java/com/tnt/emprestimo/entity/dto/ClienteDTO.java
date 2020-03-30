package com.tnt.emprestimo.entity.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

	private Long id;

	private String nome;

	private BigDecimal rendimentoMensal;

	private String risco;

	private String endereco;

	private String tipo;
	
	private boolean empregado;
	
	private Double vlrTotalPatrimonio;
	
	private Double vlrDividasAtuais;
	
}
