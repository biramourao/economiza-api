package com.economiza.economizaapi.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.economiza.economizaapi.exception.NotFoundException;
import com.economiza.economizaapi.model.Usuario;
import com.economiza.economizaapi.repository.UsuarioRepository;
import com.economiza.economizaapi.service.util.HashUtil;


@Service
public class UsuarioService implements UserDetailsService{
@Autowired private UsuarioRepository usuarioRepository;
	
	public Usuario save(Usuario user) {
		String hash = HashUtil.getSecureHash(user.getSenha());
		user.setSenha(hash);
		
		Usuario createdUser = usuarioRepository.save(user);
		return createdUser;
	}
	
	public Usuario update(Usuario user){
		
			Usuario updatedUser = usuarioRepository.save(user);
			return updatedUser;
		
	}
	
	public Usuario getById(Long id) {
		Optional<Usuario> result = usuarioRepository.findById(id);
		
		return result.orElseThrow(()-> new NotFoundException("Usuário com id " + id + ", não encontrado!"));
	}
	
	public List<Usuario> listAll() {
		List<Usuario> users = usuarioRepository.findAll();
		return users;
	}
	
	public Usuario login(String email, String password) {
		password = HashUtil.getSecureHash(password);
		
		Optional<Usuario> result = usuarioRepository.login(email, password);
		return result.orElseThrow(()-> new NotFoundException("Login ou Senha Incorretos!"));
		
	}
	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> resultado = usuarioRepository.findByEmail(username);
		if (!resultado.isPresent()) throw new UsernameNotFoundException("Não existe usuário com esse email: "+username);
			
		Usuario usuario = resultado.get();
		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE BASIC"));
		User user  = new User(usuario.getEmail(), usuario.getSenha(), authorities);
		return user;
	}
}
