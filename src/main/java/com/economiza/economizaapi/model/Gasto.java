package com.economiza.economizaapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString

@Entity
@Table
public class Gasto {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod;
	
	@Column(length = 45, nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private double valor;
	
	@Column(nullable = false)
	private Date vencimento;
	
	@Column(nullable = false)
	private int qtdParcelas;
	
	@Column()
	private Date dtPagamento;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private CategoriaGasto categoriaGasto;
	
	@ManyToOne
	private CartaoDeCredito cartaoDeCredito;

}
