package com.economiza.economizaapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

@Entity
@Table(name = "Categoria_Gasto")
public class CategoriaGasto {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int cod;
	
	@Column(length = 45, nullable = false)
	private String descricao;
	
}
