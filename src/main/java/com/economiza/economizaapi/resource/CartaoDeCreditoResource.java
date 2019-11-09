package com.economiza.economizaapi.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.economiza.economizaapi.exception.NotFoundException;
import com.economiza.economizaapi.model.CartaoDeCredito;
import com.economiza.economizaapi.model.Usuario;
import com.economiza.economizaapi.repository.UsuarioRepository;
import com.economiza.economizaapi.service.CartaoDeCreditoService;

@RestController
@RequestMapping("/cartoes-de-credito")
public class CartaoDeCreditoResource {
	@Autowired private CartaoDeCreditoService cartaoDeCreditoService;
	@Autowired private UsuarioRepository usuarioRepository;

	@CrossOrigin
	@PostMapping
	public ResponseEntity<CartaoDeCredito> save(@RequestBody CartaoDeCredito cartaoDeCredito) {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Usuario> result = usuarioRepository.findByEmail(email);
		if(!result.isPresent())  {
			throw new NotFoundException("Não foi encontrado um usuário com esse email = " + email);
		}
		Usuario usuario = result.get();
		cartaoDeCredito.setUsuario(usuario);
		CartaoDeCredito createdCartaoDeCredito = cartaoDeCreditoService.save(cartaoDeCredito);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCartaoDeCredito);
	}

	@CrossOrigin
	@PreAuthorize("@accessManager.usuarioDoCartaoDeCredito(#cod)")
	@PutMapping("/{cod}")
	public ResponseEntity<CartaoDeCredito> update(@PathVariable(name = "cod") Long cod, @RequestBody CartaoDeCredito cartaoDeCredito){
		cartaoDeCredito.setCod(cod);
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Usuario> result = usuarioRepository.findByEmail(email);
		if(!result.isPresent())  {
			throw new NotFoundException("Não foi encontrado um usuário com esse email = " + email);
		}
		Usuario usuario = result.get();
		cartaoDeCredito.setUsuario(usuario);
		CartaoDeCredito updatedCartaoDeCredito = cartaoDeCreditoService.update(cartaoDeCredito);
		return ResponseEntity.ok(updatedCartaoDeCredito);
	}

	@CrossOrigin
	@PreAuthorize("@accessManager.usuarioDoCartaoDeCredito(#cod)")
	@GetMapping("/{cod}")
	public ResponseEntity<CartaoDeCredito> getById(@PathVariable("cod") Long cod) {
		CartaoDeCredito cartaoDeCredito = cartaoDeCreditoService.getById(cod);
		return ResponseEntity.ok(cartaoDeCredito);
	}

	@SuppressWarnings("rawtypes")
	@CrossOrigin
	@PreAuthorize("@accessManager.usuarioDoCartaoDeCredito(#cod)")
	@DeleteMapping("/{cod}")
	@ResponseBody
	public ResponseEntity delete(@PathVariable(name = "cod") Long cod) {
		CartaoDeCredito cartaoDeCredito = null;
		cartaoDeCredito = cartaoDeCreditoService.getById(cod);
		if(cartaoDeCredito != null) {
			cartaoDeCreditoService.deleteById(cod);
			return new ResponseEntity(HttpStatus.OK);
		}else{
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<CartaoDeCredito>> getByUserId() {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Usuario> result = usuarioRepository.findByEmail(email);
		if(!result.isPresent())  {
			throw new NotFoundException("Não foi encontrado um usuário com esse email = " + email);
		}
		Usuario usuario = result.get();
		List<CartaoDeCredito> cartoesDeCredito = cartaoDeCreditoService.FindByUsuarioCod(usuario.getCod());
		return ResponseEntity.ok(cartoesDeCredito);
	}
}
