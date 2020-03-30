/**
 * 
 */
package com.tnt.emprestimo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author waldecleber
 * 
 *         Classe para manter cadastros de clientes, seu rendimento mensal
 *
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "id" })
@EqualsAndHashCode(callSuper = false, of = { "id" })
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Column(name = "rendimento_mensal")
	private BigDecimal rendimentoMensal;

	private String risco;

	private String endereco;

	private String tipo;
	
	private boolean empregado;
	
	@Column(name = "vlr_total_patrimonio")
	private Double vlrTotalPatrimonio;
	
	@Column(name = "vlr_dividas_atuais")
	private Double vlrDividasAtuais;

}
