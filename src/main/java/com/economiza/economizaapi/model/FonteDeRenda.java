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

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter

@Entity
@Table(name = "Fonte_de_Renda")
public class FonteDeRenda {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod;
	
	@Column(length = 45, nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private double valor;
	
	@Column(nullable = false)
	private int frequencia;
	
	@Column(nullable = false)
	private Date dtInsercao;
	
	@ManyToOne
	private Usuario usuario;

}
