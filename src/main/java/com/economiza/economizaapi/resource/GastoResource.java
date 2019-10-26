package com.economiza.economizaapi.resource;

import java.util.Date;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.economiza.economizaapi.exception.NotFoundException;
import com.economiza.economizaapi.model.Gasto;
import com.economiza.economizaapi.model.Usuario;
import com.economiza.economizaapi.repository.UsuarioRepository;
import com.economiza.economizaapi.service.GastoService;

@RestController
@RequestMapping("/gastos")
public class GastoResource {
	@Autowired private GastoService gastoService;
	@Autowired private UsuarioRepository usuarioRepository;

	@CrossOrigin
	@PostMapping
	public ResponseEntity<Gasto> save(@RequestBody Gasto gasto) {
		Gasto createdGasto = gastoService.save(gasto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdGasto);
	}

	@CrossOrigin
	@PreAuthorize("@accessManager.usuarioDoGasto(#cod)")
	@PutMapping("/{cod}")
	public ResponseEntity<Gasto> update(@PathVariable(name = "cod") Long cod, @RequestBody Gasto gasto){
		gasto.setCod(cod);
		Gasto updatedGasto = gastoService.update(gasto);
		return ResponseEntity.ok(updatedGasto);
	}

	@CrossOrigin
	@PreAuthorize("@accessManager.usuarioDoGasto(#cod)")
	@GetMapping("/{cod}")
	public ResponseEntity<Gasto> getById(@PathVariable("cod") Long cod) {
		Gasto gasto = gastoService.getById(cod);
		return ResponseEntity.ok(gasto);
	}
	
	@SuppressWarnings("rawtypes")
	@CrossOrigin
	@PreAuthorize("@accessManager.usuarioDoGasto(#cod)")
	@DeleteMapping("/{cod}")
	@ResponseBody
	public ResponseEntity delete(@PathVariable(name = "cod") Long cod) {
		Gasto deletedGasto = null;
		deletedGasto = gastoService.getById(cod);
		if(deletedGasto != null) {
			gastoService.deleteById(cod);
			return new ResponseEntity(HttpStatus.OK);
		}else{
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<Gasto>> getByUserId() {
		
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Usuario> result = usuarioRepository.findByEmail(email);
		if(!result.isPresent())  {
			throw new NotFoundException("Não foi encontrado um usuário com esse email = " + email);
		}
		Usuario usuario = result.get();
		
		List<Gasto> gasto = gastoService.findByUsuarioCod(usuario.getCod());
		return ResponseEntity.ok(gasto);
	}

	@CrossOrigin
	@PreAuthorize("@accessManager.usuarioDoGasto(#cod)")
	@PatchMapping("/{cod}/pagamento")
	public ResponseEntity<Gasto> efetuarPagamento(@PathVariable("cod") Long cod) {
		Date dtPagamento = new Date();
		Gasto gasto = gastoService.efetuarPagamento(cod, dtPagamento);
		return ResponseEntity.ok(gasto);

	}
	
	@CrossOrigin
	@PreAuthorize("@accessManager.usuarioDoCartaoDeCredito(#cod)")
	@GetMapping("/cartao-de-credito/{cod}")
	public ResponseEntity<List<Gasto>> getByCartaoDeCreditoCod(@PathVariable("cod") Long cod) {
		List<Gasto> gasto = gastoService.findByCartaoDeCreditoCod(cod);
		return ResponseEntity.ok(gasto);
	}
	
	@CrossOrigin
	@GetMapping("/categoria-de-gasto/{cod}")
	public ResponseEntity<List<Gasto>> getByCategoriaGastoCod(@PathVariable("cod") Long cod) {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Usuario> result = usuarioRepository.findByEmail(email);
		if(!result.isPresent())  {
			throw new NotFoundException("Não foi encontrado um usuário com esse email = " + email);
		}
		Usuario usuario = result.get();
		List<Gasto> gasto = gastoService.findByCategoriaGastoCodAndUsuarioCod(cod, usuario.getCod());
		return ResponseEntity.ok(gasto);
	}
}
