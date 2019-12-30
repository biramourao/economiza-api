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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.economiza.economizaapi.exception.NotFoundException;
import com.economiza.economizaapi.model.Renda;
import com.economiza.economizaapi.model.Gasto;
import com.economiza.economizaapi.model.Usuario;
import com.economiza.economizaapi.repository.UsuarioRepository;
import com.economiza.economizaapi.service.RendaService;

@RestController
@RequestMapping("/fontes-de-renda")
public class RendaResource {
	@Autowired private RendaService fonteDeRendaService;
	@Autowired private UsuarioRepository usuarioRepository;

	@CrossOrigin
	@PostMapping
	public ResponseEntity<Renda> save(@RequestBody Renda fonteDeRenda) {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Usuario> result = usuarioRepository.findByEmail(email);
		if(!result.isPresent())  {
			throw new NotFoundException("Não foi encontrado um usuário com esse email = " + email);
		}
		Usuario usuario = result.get();
		fonteDeRenda.setUsuario(usuario);
		Renda createdFonteDeRenda = fonteDeRendaService.save(fonteDeRenda);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdFonteDeRenda);
	}
	

	@CrossOrigin
	@PreAuthorize("@accessManager.usuarioDaFonteDeRenda(#cod)")
	@PutMapping("/{cod}")
	public ResponseEntity<Renda> update(@PathVariable(name = "cod") Long cod, @RequestBody Renda fonteDeRenda){
		fonteDeRenda.setCod(cod);
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Usuario> result = usuarioRepository.findByEmail(email);
		if(!result.isPresent())  {
			throw new NotFoundException("Não foi encontrado um usuário com esse email = " + email);
		}
		Usuario usuario = result.get();
		fonteDeRenda.setUsuario(usuario);
		Renda updatedFonteDeRenda = fonteDeRendaService.update(fonteDeRenda);
		return ResponseEntity.ok(updatedFonteDeRenda);
	}

	@CrossOrigin
	@PreAuthorize("@accessManager.usuarioDaFonteDeRenda(#cod)")
	@GetMapping("/{cod}")
	public ResponseEntity<Renda> getById(@PathVariable("cod") Long cod) {
		Renda fonteDeRenda = fonteDeRendaService.getById(cod);
		return ResponseEntity.ok(fonteDeRenda);
	}
	
	@SuppressWarnings("rawtypes")
	@CrossOrigin
	@PreAuthorize("@accessManager.usuarioDaFonteDeRenda(#cod)")
	@DeleteMapping("/{cod}")
	@ResponseBody
	public ResponseEntity delete(@PathVariable(name = "cod") Long cod) {
		Renda deletedFonteDeRenda = null;
		deletedFonteDeRenda = fonteDeRendaService.getById(cod);
		if(deletedFonteDeRenda != null) {
			fonteDeRendaService.deleteById(cod);
			return new ResponseEntity(HttpStatus.OK);
		}else{
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<Renda>> getByUserId(@RequestParam(required = false) String dtInicio,
			@RequestParam(required = false) String dtFim) {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Usuario> result = usuarioRepository.findByEmail(email);
		if(!result.isPresent())  {
			throw new NotFoundException("Não foi encontrado um usuário com esse email = " + email);
		}
		Usuario usuario = result.get();
		
		if ((dtInicio == null && dtFim == null)||(dtInicio == "" && dtFim == "" )) {
			List<Renda> fonteDeRenda = fonteDeRendaService.findByUsuarioCod(usuario.getCod());
			return ResponseEntity.ok(fonteDeRenda);
		} else {
			List<Renda> fonteDeRenda = fonteDeRendaService.findByUsuarioCodAndDtVencimento(usuario.getCod(),dtInicio,dtFim);
			return ResponseEntity.ok(fonteDeRenda);
		}
		
	}
	
	
}
