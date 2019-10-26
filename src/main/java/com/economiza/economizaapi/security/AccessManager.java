package com.economiza.economizaapi.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.economiza.economizaapi.exception.NotFoundException;
import com.economiza.economizaapi.model.CartaoDeCredito;
import com.economiza.economizaapi.model.Gasto;
import com.economiza.economizaapi.model.Usuario;
import com.economiza.economizaapi.repository.UsuarioRepository;
import com.economiza.economizaapi.service.CartaoDeCreditoService;
import com.economiza.economizaapi.service.GastoService;

@Component("accessManager")
public class AccessManager {

	@Autowired UsuarioRepository usuarioRepository;
	@Autowired GastoService gastoService;
	@Autowired CartaoDeCreditoService cartaoDeCreditoService;
	
	public boolean usuario(Long id) {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Usuario> result = usuarioRepository.findByEmail(email);
		if(!result.isPresent())  {
			throw new NotFoundException("Não foi encontrado um usuário com esse email = " + email);
		}
		Usuario usuario = result.get();
		return usuario.getCod() == id;
	}
	public boolean usuarioDoGasto(Long id) {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Usuario> result = usuarioRepository.findByEmail(email);
		if(!result.isPresent())  {
			throw new NotFoundException("Não foi encontrado um usuário com esse email = " + email);
		}
		Usuario usuario = result.get();
		Gasto gasto = gastoService.getById(id);
		return gasto.getUsuario().getCod() == usuario.getCod();
		
	}
	public boolean usuarioDoCartaoDeCredito(Long id) {
		
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Usuario> result = usuarioRepository.findByEmail(email);
		if(!result.isPresent())  {
			throw new NotFoundException("Não foi encontrado um usuário com esse email = " + email);
		}
		Usuario usuario = result.get();
		CartaoDeCredito cartaoDeCredito = cartaoDeCreditoService.getById(id);
		return cartaoDeCredito.getUsuario().getCod() == usuario.getCod();
		
	}
}
