package com.economiza.economizaapi.model;

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

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

@Entity
@Table(name = "Cartao_de_Credito")
public class CartaoDeCredito {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int cod;
	
	@Column(length = 45, nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private int diaVencFatura;
	
	@Column(nullable = false)
	private double limiteTotal;
	
	@Column(nullable = false)
	private double SaldoRestante;
	
	@ManyToOne
	private Usuario usuario;
}
