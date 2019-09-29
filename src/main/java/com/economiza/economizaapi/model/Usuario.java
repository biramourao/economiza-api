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
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString

@Entity
@Table
public class Usuario {
	
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod;
	
	@Column(length = 60, nullable = false, unique = true)
	private String email;
	
	@Column(length = 100, nullable = false)
	private String nome;
	
	@Column(length = 64, nullable = false)
	private String senha;
	
}
