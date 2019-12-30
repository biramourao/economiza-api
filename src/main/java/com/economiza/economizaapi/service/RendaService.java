package com.economiza.economizaapi.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economiza.economizaapi.exception.NotFoundException;
import com.economiza.economizaapi.model.Renda;
import com.economiza.economizaapi.model.Gasto;
import com.economiza.economizaapi.repository.RendaRepository;

@Service
public class RendaService {

	@Autowired
	private RendaRepository fonteDeRendaRepository;
	
	
	public Renda save(Renda fonteDeRenda) {
		return fonteDeRendaRepository.save(fonteDeRenda);
		
	}
	
	
	public Renda update(Renda fonteDeRenda) {
		return fonteDeRendaRepository.save(fonteDeRenda);
	}
	
	
	public Renda getById(Long id) {
		Optional<Renda> result = fonteDeRendaRepository.findById(id);
		return result.orElseThrow(()-> new NotFoundException("Fonte de Renda com id " + id + ", não encontrado!"));
	}
	
	public List<Renda> findByUsuarioCodAndDtVencimento(Long cod, String inicio, String fim) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dateFormat = formato;
		String fimMaisUm = "";
		try {
			Date dfim = formato.parse(fim);
			dfim.setDate(dfim.getDate()+1);
			fimMaisUm = dateFormat.format(dfim);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fonteDeRendaRepository.findByUsuarioCodAndDtValidade(cod, inicio, fimMaisUm);
	}
	
	public List<Renda> listAll() {
		return fonteDeRendaRepository.findAll();
	}
	
	
	public void deleteById(Long id) {
		fonteDeRendaRepository.deleteById(id);
	}
	
	public List<Renda> findByUsuarioCod(Long cod){
		return fonteDeRendaRepository.findByUsuarioCod(cod);
	}
}
