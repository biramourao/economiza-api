package com.economiza.economizaapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economiza.economizaapi.model.FonteDeRenda;
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
		return fonteDeRendaRepository.findById(id).get();
	}
	
	
	public List<FonteDeRenda> listAll() {
		return fonteDeRendaRepository.findAll();
	}
	
	
	public void deleteById(Long id) {
		fonteDeRendaRepository.deleteById(id);
	}
}
