package com.economiza.economizaapi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.economiza.economizaapi.model.FonteDeRenda;
import com.economiza.economizaapi.model.Usuario;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class FonteDeRendaRepositoryTest {


	@Autowired
	private FonteDeRendaRepository fonteDeRendaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test
	public void asaveTest() {
		
		Usuario usuario = new Usuario(null,"ubiratan.mourao@gmail.com","Ubiratan Leitão Mourão","123456");
		Usuario savedUsuario = usuarioRepository.save(usuario);
		
		FonteDeRenda fonteDeRenda= new FonteDeRenda(null, "Salario Bira", 1700.00, 30,new Date(), savedUsuario);
		FonteDeRenda savedFonteDeRenda = fonteDeRendaRepository.save(fonteDeRenda);
		
		Usuario usuario2 = new Usuario(null,"raianyramos1k@gmail.com","Raiany Ramos","123");
		Usuario savedUsuario2 = usuarioRepository.save(usuario2);
		
		FonteDeRenda fonteDeRenda2= new FonteDeRenda(null, "Salario Raiany", 1700.00, 30,new Date(), savedUsuario2);
		fonteDeRendaRepository.save(fonteDeRenda2);
		
		assertThat(savedFonteDeRenda.getDescricao()).isEqualTo("Salario Bira");
		
	}
	
	@Test
	public void bupdateTest() {
		Optional<Usuario> usuario = usuarioRepository.findById(1L);
		
		FonteDeRenda fonteDeRenda= new FonteDeRenda(1L, "Salario", 1700.00, 30,new Date(), usuario.get());
		FonteDeRenda savedFonteDeRenda = fonteDeRendaRepository.save(fonteDeRenda);
		
		assertThat(savedFonteDeRenda.getDescricao()).isEqualTo("Salario");	
	}
	
	@Test
	public void cgetByIdTest() {
		Optional<FonteDeRenda> savedFonteDeRenda = fonteDeRendaRepository.findById(1L);
		
		assertThat(savedFonteDeRenda.get().getDescricao()).isEqualTo("Salario");
	}
	
	@Test
	public void dfindAllTest() {
		List<FonteDeRenda> fonteDeRenda = fonteDeRendaRepository.findAll();
		
		assertThat(fonteDeRenda.size()).isEqualTo(2);
	}
	
	@Test
	public void edeleteById() {
		fonteDeRendaRepository.deleteById(2L);
		List<FonteDeRenda> cartaoDeCredito = fonteDeRendaRepository.findAll();
		
		assertThat(cartaoDeCredito.size()).isEqualTo(1);
	}
}
