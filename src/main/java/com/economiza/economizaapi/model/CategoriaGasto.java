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

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

@Entity
@Table(name = "Categoria_Gasto")
public class CategoriaGasto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod;
	
	@Column(length = 45, nullable = false)
	private String descricao;
	
	@ManyToOne
	private Usuario usuario;

	@Getter(onMethod = @__({@JsonIgnore}))
	@OneToMany(mappedBy = "categoriaGasto")
    private Set<Gasto> gastos;
	
	@PreRemove
    private void removeCategoriaGastoFromGasto() {
		for (Gasto gasto : this.getGastos()) {
            gasto.setCategoriaGasto(null);
        }
    }
	
}
