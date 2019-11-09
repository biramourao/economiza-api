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
import com.economiza.economizaapi.model.CategoriaGasto;
import com.economiza.economizaapi.model.Usuario;
import com.economiza.economizaapi.repository.CategoriaGastoRepository;
import com.economiza.economizaapi.repository.UsuarioRepository;
import com.economiza.economizaapi.service.CategoriaGastoService;

@RestController
@RequestMapping("/categorias-de-gasto")
public class CategoriaGastoResource {
	@Autowired private CategoriaGastoService categoriaGastoService;
	@Autowired private CategoriaGastoRepository categoriaGastoRepository;
	@Autowired private UsuarioRepository usuarioRepository;

	@CrossOrigin
	@PostMapping
	public ResponseEntity<CategoriaGasto> save(@RequestBody CategoriaGasto categoriaGasto) {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Usuario> result = usuarioRepository.findByEmail(email);
		if(!result.isPresent())  {
			throw new NotFoundException("Não foi encontrado um usuário com esse email = " + email);
		}
		Usuario usuario = result.get();
		categoriaGasto.setUsuario(usuario);
		CategoriaGasto createdCategoriaGasto = categoriaGastoService.save(categoriaGasto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoriaGasto);
	}

	@CrossOrigin
	@PreAuthorize("@accessManager.usuarioDaCategoriaGasto(#cod)")
	@PutMapping("/{cod}")
	public ResponseEntity<CategoriaGasto> update(@PathVariable(name = "cod") Long cod, @RequestBody CategoriaGasto categoriaGasto){
		categoriaGasto.setCod(cod);
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Usuario> result = usuarioRepository.findByEmail(email);
		if(!result.isPresent())  {
			throw new NotFoundException("Não foi encontrado um usuário com esse email = " + email);
		}
		Usuario usuario = result.get();
		categoriaGasto.setUsuario(usuario);
		CategoriaGasto updatedCategoriaGasto = categoriaGastoService.update(categoriaGasto);
		return ResponseEntity.ok(updatedCategoriaGasto);
	}

	@CrossOrigin
	@PreAuthorize("@accessManager.usuarioDaCategoriaGasto(#cod)")
	@GetMapping("/{cod}")
	public ResponseEntity<CategoriaGasto> getById(@PathVariable("cod") Long cod) {
		CategoriaGasto categoriaGasto = categoriaGastoService.getById(cod);
		return ResponseEntity.ok(categoriaGasto);
	}

	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<CategoriaGasto>> findByUserCod() {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Usuario> result = usuarioRepository.findByEmail(email);
		if(!result.isPresent())  {
			throw new NotFoundException("Não foi encontrado um usuário com esse email = " + email);
		}
		Usuario usuario = result.get();
		List<CategoriaGasto> categoriaGasto = categoriaGastoRepository.findByUsuarioCod(usuario.getCod());
		return ResponseEntity.ok(categoriaGasto);
	}
	@SuppressWarnings("rawtypes")
	@CrossOrigin
	@PreAuthorize("@accessManager.usuarioDaCategoriaGasto(#cod)")
	@DeleteMapping("/{cod}")
	@ResponseBody
	public ResponseEntity delete(@PathVariable(name = "cod") Long cod) {
		CategoriaGasto categoriaGasto = null;
		categoriaGasto = categoriaGastoService.getById(cod);
		if(categoriaGasto != null) {
			categoriaGastoService.deleteById(cod);
			return new ResponseEntity(HttpStatus.OK);
		}else{
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
}
