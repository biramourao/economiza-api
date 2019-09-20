package com.economiza.economizaapi.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Gasto {
	
	private int cod;
	private String nome;
	private double valor;
	private Date vencimento;
	private String tipo;
	private int qtdParcelas;
	private String status;
	private Usuario usuario;
	private CategoriaGasto categoriaGasto;
	private CartaoDeCredito cartaoDeCredito;

}
