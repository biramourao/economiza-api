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

import com.economiza.economizaapi.model.FonteDeRenda;
import com.economiza.economizaapi.repository.FonteDeRendaRepository;
import com.economiza.economizaapi.service.FonteDeRendaService;

@RestController
@RequestMapping("/fontes-de-renda")
public class FonteDeRendaResource {
	@Autowired private FonteDeRendaService fonteDeRendaService;
	@Autowired private FonteDeRendaRepository fonteDeRendaRepository;

	@CrossOrigin
	@PostMapping
	public ResponseEntity<FonteDeRenda> save(@RequestBody FonteDeRenda fonteDeRenda) {
		FonteDeRenda createdFonteDeRenda = fonteDeRendaService.save(fonteDeRenda);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdFonteDeRenda);
	}

	@CrossOrigin
	@PutMapping("/{cod}")
	public ResponseEntity<FonteDeRenda> update(@PathVariable(name = "cod") Long cod, @RequestBody FonteDeRenda fonteDeRenda){
		fonteDeRenda.setCod(cod);
		FonteDeRenda updatedFonteDeRenda = fonteDeRendaService.update(fonteDeRenda);
		return ResponseEntity.ok(updatedFonteDeRenda);
	}

	@CrossOrigin
	@GetMapping("/{cod}")
	public ResponseEntity<FonteDeRenda> getById(@PathVariable("cod") Long cod) {
		FonteDeRenda fonteDeRenda = fonteDeRendaService.getById(cod);
		return ResponseEntity.ok(fonteDeRenda);
	}

	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<FonteDeRenda>> listAll() {
		List<FonteDeRenda> fontesDeRenda = fonteDeRendaRepository.findAll();
		return ResponseEntity.ok(fontesDeRenda);
	}
	
	@SuppressWarnings("rawtypes")
	@CrossOrigin
	@DeleteMapping("/{cod}")
	@ResponseBody
	public ResponseEntity delete(@PathVariable(name = "cod") Long cod) {
		FonteDeRenda deletedFonteDeRenda = null;
		deletedFonteDeRenda = fonteDeRendaService.getById(cod);
		if(deletedFonteDeRenda != null) {
			fonteDeRendaService.deleteById(cod);
			return new ResponseEntity(HttpStatus.OK);
		}else{
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@CrossOrigin
	@GetMapping("/usuario/{cod}")
	public ResponseEntity<List<FonteDeRenda>> getByUserId(@PathVariable("cod") Long cod) {
		List<FonteDeRenda> fonteDeRenda = fonteDeRendaService.findByUsuarioCod(cod);
		return ResponseEntity.ok(fonteDeRenda);
	}
	
}
