package com.economiza.economizaapi.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "cartao_de_credito")
public class CartaoDeCredito {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod;
	
	@Column(length = 45, nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private int diaVencFatura;
	
	@Column(nullable = false)
	private double limiteTotal;
	
	@Column(name="saldo_restante", nullable = false)
	private double saldoRestante;
	
	@ManyToOne
	private Usuario usuario;
	
	@Getter(onMethod = @__({@JsonIgnore}))
	@OneToMany(mappedBy = "cartaoDeCredito")
    private Set<Gasto> gastos;
	
	@PreRemove
    private void removeCategoriaGastoFromGasto() {
		for (Gasto gasto : this.getGastos()) {
            gasto.setCartaoDeCredito(null);
        }
    }
}
