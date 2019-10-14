package com.economiza.economizaapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.economiza.economizaapi.dto.UsuarioLoginDTO;
import com.economiza.economizaapi.model.CategoriaGasto;
import com.economiza.economizaapi.model.Usuario;
import com.economiza.economizaapi.repository.CategoriaGastoRepository;
import com.economiza.economizaapi.repository.UsuarioRepository;
import com.economiza.economizaapi.service.CategoriaGastoService;
import com.economiza.economizaapi.service.UsuarioService;
import com.economiza.economizaapi.service.util.HashUtil;

@RestController
@RequestMapping("/categorias-de-gasto")
public class CategoriaGastoResource {
	@Autowired private CategoriaGastoService categoriaGastoService;
	@Autowired private CategoriaGastoRepository categoriaGastoRepository;

	@CrossOrigin
	@PostMapping
	public ResponseEntity<CategoriaGasto> save(@RequestBody CategoriaGasto categoriaGasto) {
		CategoriaGasto createdCategoriaGasto = categoriaGastoService.save(categoriaGasto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoriaGasto);
	}

	@CrossOrigin
	@PutMapping("/{cod}")
	public ResponseEntity<CategoriaGasto> update(@PathVariable(name = "cod") Long cod, @RequestBody CategoriaGasto categoriaGasto){
		categoriaGasto.setCod(cod);
		CategoriaGasto updatedCategoriaGasto = categoriaGastoService.update(categoriaGasto);
		return ResponseEntity.ok(updatedCategoriaGasto);
	}

	@CrossOrigin
	@GetMapping("/{cod}")
	public ResponseEntity<CategoriaGasto> getById(@PathVariable("cod") Long cod) {
		CategoriaGasto categoriaGasto = categoriaGastoService.getById(cod);
		return ResponseEntity.ok(categoriaGasto);
	}

	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<CategoriaGasto>> listAll() {
		List<CategoriaGasto> categoriaGasto = categoriaGastoRepository.findAll();
		return ResponseEntity.ok(categoriaGasto);
	}
}
