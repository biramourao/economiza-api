package com.economiza.economizaapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.economiza.economizaapi.model.CartaoDeCredito;
import com.economiza.economizaapi.model.CategoriaGasto;
import com.economiza.economizaapi.model.FonteDeRenda;
import com.economiza.economizaapi.repository.CartaoDeCreditoRepository;
import com.economiza.economizaapi.repository.CategoriaGastoRepository;
import com.economiza.economizaapi.service.CartaoDeCreditoService;
import com.economiza.economizaapi.service.CategoriaGastoService;

@RestController
@RequestMapping("/cartoes-de-credito")
public class CartaoDeCreditoResource {
	@Autowired private CartaoDeCreditoService cartaoDeCreditoService;
	@Autowired private CartaoDeCreditoRepository cartaoDeCreditoRepository;

	@CrossOrigin
	@PostMapping
	public ResponseEntity<CartaoDeCredito> save(@RequestBody CartaoDeCredito cartaoDeCredito) {
		CartaoDeCredito createdCartaoDeCredito = cartaoDeCreditoService.save(cartaoDeCredito);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCartaoDeCredito);
	}

	@CrossOrigin
	@PutMapping("/{cod}")
	public ResponseEntity<CartaoDeCredito> update(@PathVariable(name = "cod") Long cod, @RequestBody CartaoDeCredito cartaoDeCredito){
		cartaoDeCredito.setCod(cod);
		CartaoDeCredito updatedCartaoDeCredito = cartaoDeCreditoService.update(cartaoDeCredito);
		return ResponseEntity.ok(updatedCartaoDeCredito);
	}

	@CrossOrigin
	@GetMapping("/{cod}")
	public ResponseEntity<CartaoDeCredito> getById(@PathVariable("cod") Long cod) {
		CartaoDeCredito cartaoDeCredito = cartaoDeCreditoService.getById(cod);
		return ResponseEntity.ok(cartaoDeCredito);
	}

	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<CartaoDeCredito>> listAll() {
		List<CartaoDeCredito> categoriaGasto = cartaoDeCreditoRepository.findAll();
		return ResponseEntity.ok(categoriaGasto);
	}
	@CrossOrigin
	@DeleteMapping("/{cod}")
	@ResponseBody
	public ResponseEntity delete(@PathVariable(name = "cod") Long cod) {
		CartaoDeCredito categoriaGasto = null;
		categoriaGasto = cartaoDeCreditoService.getById(cod);
		if(categoriaGasto != null) {
			cartaoDeCreditoService.deleteById(cod);
			return new ResponseEntity(HttpStatus.OK);
		}else{
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	@CrossOrigin
	@GetMapping("/usuario/{cod}")
	public ResponseEntity<List<CartaoDeCredito>> getByUserId(@PathVariable("cod") Long cod) {
		List<CartaoDeCredito> cartoesDeCredito = cartaoDeCreditoService.FindByUsuarioCod(cod);
		return ResponseEntity.ok(cartoesDeCredito);
	}
}
