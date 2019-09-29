package com.economiza.economizaapi.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.economiza.economizaapi.model.Usuario;
import com.economiza.economizaapi.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
/*
	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping
	public Usuario adicionar(@Valid @RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@GetMapping
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Usuario>> buscar(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(usuario);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
		Usuario existente = usuarioRepository.findById(id);
		
		if (existente == null) {
			return ResponseEntity.notFound().build();
		}else {
			
		existente = usuarioRepository.save(usuario);
		return ResponseEntity.ok(usuario);
		}	
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Usuario usuario = usuarioRepository.findOne(id);

		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}

		usuarioRepository.delete(contato);

		return ResponseEntity.noContent().build();
	}
*/
}
