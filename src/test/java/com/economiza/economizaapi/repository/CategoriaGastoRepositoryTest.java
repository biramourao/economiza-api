package com.economiza.economizaapi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.economiza.economizaapi.model.CategoriaGasto;
import com.economiza.economizaapi.model.Usuario;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class CategoriaGastoRepositoryTest {

	@Autowired
	private CategoriaGastoRepository categoriaGastoRepository;
	
	@Test
	public void asaveTest() {
		CategoriaGasto categoriaGasto = new CategoriaGasto(null,"Transporte");
		CategoriaGasto savedCategoriaGasto = categoriaGastoRepository.save(categoriaGasto);
		CategoriaGasto categoriaGasto2 = new CategoriaGasto(null, "Alimentação");

		categoriaGastoRepository.save(categoriaGasto2);
		
		assertThat(savedCategoriaGasto.getCod()).isEqualTo(1L);
		
	}
	
	@Test
	public void bupdateTest() {
		CategoriaGasto categoriaGasto = new CategoriaGasto(1L,"Carro");
		CategoriaGasto savedCategoriaGasto = categoriaGastoRepository.save(categoriaGasto);
		
		assertThat(savedCategoriaGasto.getDescricao()).isEqualTo("Carro");	
	}
	
	@Test
	public void cgetByIdTest() {
		Optional<CategoriaGasto> result = categoriaGastoRepository.findById(1L);
		CategoriaGasto categoriaGasto = result.get();
		
		assertThat(categoriaGasto.getDescricao()).isEqualTo("Carro");
	}
	
	@Test
	public void dfindAllTest() {
		List<CategoriaGasto> categoriasGasto = categoriaGastoRepository.findAll();
		
		assertThat(categoriasGasto.size()).isEqualTo(2);
	}
	
	@Test
	public void edeleteById() {
		categoriaGastoRepository.deleteById(1L);
		List<CategoriaGasto> categoriasGasto = categoriaGastoRepository.findAll();
		
		assertThat(categoriasGasto.size()).isEqualTo(1);
	}
}
