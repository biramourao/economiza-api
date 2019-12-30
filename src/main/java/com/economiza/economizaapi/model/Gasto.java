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
public class Gasto extends Movimentacao{
	
	
	@Column(nullable = false)
	private Date vencimento;
	
	@Column()
	private Date dtPagamento;
	
	@ManyToOne
	private CategoriaGasto categoriaGasto;
	
	@ManyToOne
	private CartaoDeCredito cartaoDeCredito;

}
