package com.economiza.economizaapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
public class Movimentacao {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod;
	
	@Column(length = 45, nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private double valor;
	
	@Column(nullable = false)
	private Date dtMovimento;
	
	@Column(nullable = false)
	private double saldo;
	
	@Column(nullable = false)
	private double saldoConsolidado;
	
	@ManyToOne
	private Usuario usuario;

}
