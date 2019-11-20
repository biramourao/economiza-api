package com.economiza.economizaapi.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economiza.economizaapi.exception.NotFoundException;
import com.economiza.economizaapi.model.FonteDeRenda;
import com.economiza.economizaapi.model.Gasto;
import com.economiza.economizaapi.repository.FonteDeRendaRepository;

@Service
public class FonteDeRendaService {

	@Autowired
	private FonteDeRendaRepository fonteDeRendaRepository;
	
	
	public FonteDeRenda save(FonteDeRenda fonteDeRenda) {
		return fonteDeRendaRepository.save(fonteDeRenda);
		
	}
	
	
	public FonteDeRenda update(FonteDeRenda fonteDeRenda) {
		return fonteDeRendaRepository.save(fonteDeRenda);
	}
	
	
	public FonteDeRenda getById(Long id) {
		Optional<FonteDeRenda> result = fonteDeRendaRepository.findById(id);
		return result.orElseThrow(()-> new NotFoundException("Fonte de Renda com id " + id + ", não encontrado!"));
	}
	
	public List<FonteDeRenda> findByUsuarioCodAndDtVencimento(Long cod, String inicio, String fim) {
		return fonteDeRendaRepository.findByUsuarioCodAndDtValidade(cod, inicio, fim);
	}
	
	public List<FonteDeRenda> listAll() {
		return fonteDeRendaRepository.findAll();
	}
	
	
	public void deleteById(Long id) {
		fonteDeRendaRepository.deleteById(id);
	}
	
	public List<FonteDeRenda> findByUsuarioCod(Long cod){
		return fonteDeRendaRepository.findByUsuarioCod(cod);
	}
}
