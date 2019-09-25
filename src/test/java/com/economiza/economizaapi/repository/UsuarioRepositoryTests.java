package com.economiza.economizaapi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.FixMethodOrder;
import com.economiza.economizaapi.model.Usuario;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class UsuarioRepositoryTests {
	@Autowired private UsuarioRepository usuarioRepository;
	
	@Test
	public void AsaveTest() {
		Usuario usuario = new Usuario(null,"ubiratan.mourao@gmail.com","Ubiratan Leitão Mourão","123456");
		Usuario createdUser = usuarioRepository.save(usuario);
		Usuario usuario2 = new Usuario(null,"Joao@gmail.com","Joao","123");
		usuarioRepository.save(usuario2);
		
		assertThat(createdUser.getEmail()).isEqualTo("ubiratan.mourao@gmail.com");
	}
	
	@Test
	public void bupdateTest() {
		Usuario usuario = new Usuario(1L,"ubiratan.mourao@live.com","Ubiratan Leitão Mourão","123456");
		Usuario createdUser = usuarioRepository.save(usuario);
		
		assertThat(createdUser.getEmail()).isEqualTo("ubiratan.mourao@live.com");	
	}
	
	@Test
	public void cgetByIdTest() {
		Optional<Usuario> result = usuarioRepository.findById(1L);
		Usuario usuario = result.get();
		
		assertThat(usuario.getSenha()).isEqualTo("123456");
	}
	
	@Test
	public void dfindAllTest() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		assertThat(usuarios.size()).isEqualTo(2);
	}
	
	@Test
	public void eloginTest() {
		Optional<Usuario> result = usuarioRepository.login("ubiratan.mourao@live.com", "123456");
		Usuario usuarioLogado = result.get();
		
		assertThat(usuarioLogado.getCod()).isEqualTo(1L);
	}
}
