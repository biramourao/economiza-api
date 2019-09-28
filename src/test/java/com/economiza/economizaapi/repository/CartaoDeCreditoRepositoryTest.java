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

import com.economiza.economizaapi.model.CartaoDeCredito;
import com.economiza.economizaapi.model.Usuario;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class CartaoDeCreditoRepositoryTest {

	@Autowired
	private CartaoDeCreditoRepository cartaoDeCreditoRepository;
	@Autowired 
	private UsuarioRepository usuarioRepository;

	
	@Test
	public void asaveTest() {
		
		Usuario usuario = new Usuario(null,"ubiratan.mourao@gmail.com","Ubiratan Leitão Mourão","123456");
		Usuario savedUsuario = usuarioRepository.save(usuario);
		CartaoDeCredito cartaoDeCredito= new CartaoDeCredito(null, "NuBank", 6, 1400, 300, savedUsuario);
		
		Usuario usuario2 = new Usuario(null,"raianyramos1k@gmail.com","Raiany Ramos","123");
		Usuario savedUsuario2 = usuarioRepository.save(usuario2);
		CartaoDeCredito cartaoDeCredito2 = new CartaoDeCredito(null, "Nu Raiany", 6, 1400.50, 300, savedUsuario2);
		cartaoDeCreditoRepository.save(cartaoDeCredito2);
		
		CartaoDeCredito savedCartaoDeCredito = cartaoDeCreditoRepository.save(cartaoDeCredito);
		
		assertThat(savedCartaoDeCredito.getLimiteTotal()).isEqualTo(1400);
		
	}
	
	@Test
	public void bupdateTest() {
		Optional<Usuario> usuario = usuarioRepository.findById(1L);
		
		CartaoDeCredito cartaoDeCredito= new CartaoDeCredito(1L, "NuBank Alterado", 6, 1400, 300, usuario.get());
		CartaoDeCredito savedCartaoDeCredito = cartaoDeCreditoRepository.save(cartaoDeCredito);
		
		assertThat(savedCartaoDeCredito.getNome()).isEqualTo("NuBank Alterado");	
	}
	
	@Test
	public void cgetByIdTest() {
		Optional<CartaoDeCredito> savedCartaoDeCredito = cartaoDeCreditoRepository.findById(1L);
		
		assertThat(savedCartaoDeCredito.get().getNome()).isEqualTo("NuBank Alterado");
	}
	
	@Test
	public void dfindAllTest() {
		List<CartaoDeCredito> cartaoDeCredito = cartaoDeCreditoRepository.findAll();
		
		assertThat(cartaoDeCredito.size()).isEqualTo(2);
	}
	
	@Test
	public void edeleteById() {
		cartaoDeCreditoRepository.deleteById(2L);
		List<CartaoDeCredito> cartaoDeCredito = cartaoDeCreditoRepository.findAll();
		
		assertThat(cartaoDeCredito.size()).isEqualTo(1);
	}
	
	@Test
	public void fAlteraSaldoRestanteTest() {
		int qtAlteracoes = cartaoDeCreditoRepository.alteraSaldoRestante(1L, 250.00);
		assertThat(qtAlteracoes).isEqualTo(1);
		
		Optional<CartaoDeCredito> savedCartaoDeCredito = cartaoDeCreditoRepository.findById(1L);
		assertThat(savedCartaoDeCredito.get().getSaldoRestante()).isEqualTo(250.00);
	}
	@Test
	public void gFindByUsuarioTest() {
		List<CartaoDeCredito> cartaoDeCredito = cartaoDeCreditoRepository.findByUsuarioCod(1L);
		System.out.println(cartaoDeCredito);
		assertThat(cartaoDeCredito.size()).isEqualTo(1);
		
	}
}
