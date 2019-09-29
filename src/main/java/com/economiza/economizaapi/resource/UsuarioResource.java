package com.economiza.economizaapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.economiza.economizaapi.dto.UsuarioLoginDTO;
import com.economiza.economizaapi.model.Usuario;
import com.economiza.economizaapi.repository.UsuarioRepository;
import com.economiza.economizaapi.service.UsuarioService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	@Autowired private UsuarioService usuarioService;
	@Autowired private UsuarioRepository UsuarioRepository;

	@PostMapping
	public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
		Usuario createdUser = usuarioService.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}

	@PutMapping("/{cod}")
	public ResponseEntity<Usuario> update(@PathVariable(name = "cod") Long cod, @RequestBody Usuario user) {
		user.setCod(cod);
		Usuario updatedUser = usuarioService.update(user);
		return ResponseEntity.ok(updatedUser);

	}

	@GetMapping("/{cod}")
	public ResponseEntity<Usuario> getById(@PathVariable("cod") Long cod) throws NotFoundException {
		Usuario user = usuarioService.getById(cod);
		return ResponseEntity.ok(user);
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> listAll() {
		List<Usuario> users = UsuarioRepository.findAll();
		return ResponseEntity.ok(users);
	}

	@PostMapping("/login")
	public ResponseEntity<Usuario> login(@RequestBody UsuarioLoginDTO user) {
		Usuario loggedUser = usuarioService.login(user.getEmail(), user.getSenha());
		return ResponseEntity.ok(loggedUser);
	}
}
