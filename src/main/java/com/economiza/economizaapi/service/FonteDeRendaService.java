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
import com.economiza.economizaapi.model.FonteDeRenda;
import com.economiza.economizaapi.model.Gasto;
import com.economiza.economizaapi.repository.FonteDeRendaRepository;

@Service
public class FonteDeRendaService {

	@Autowired
	private FonteDeRendaRepository fonteDeRendaRepository;
	
	
	public FonteDeRenda save(FonteDeRenda fonteDeRenda) {
		fonteDeRenda.getDtValidade().setDate(fonteDeRenda.getDtValidade().getDate()+1);
		return fonteDeRendaRepository.save(fonteDeRenda);
		
	}
	
	
	public FonteDeRenda update(FonteDeRenda fonteDeRenda) {
		fonteDeRenda.getDtValidade().setDate(fonteDeRenda.getDtValidade().getDate()+1);
		return fonteDeRendaRepository.save(fonteDeRenda);
	}
	
	
	public FonteDeRenda getById(Long id) {
		Optional<FonteDeRenda> result = fonteDeRendaRepository.findById(id);
		return result.orElseThrow(()-> new NotFoundException("Fonte de Renda com id " + id + ", não encontrado!"));
	}
	
	public List<FonteDeRenda> findByUsuarioCodAndDtVencimento(Long cod, String inicio, String fim) {
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
