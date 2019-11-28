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
import com.economiza.economizaapi.model.Gasto;
import com.economiza.economizaapi.repository.GastoRepository;

@Service
public class GastoService {

	@Autowired
	private GastoRepository gastoRepository;

	public Gasto save(Gasto gasto) {
		gasto.getVencimento().setDate(gasto.getVencimento().getDate()+1);
		if(gasto.getDtPagamento() != null) {
			gasto.getDtPagamento().setDate(gasto.getDtPagamento().getDate()+1);
		}
		return gastoRepository.save(gasto);

	}

	public Gasto update(Gasto gasto) {
		gasto.getVencimento().setDate(gasto.getVencimento().getDate()+1);
		if(gasto.getDtPagamento() != null) {
			gasto.getDtPagamento().setDate(gasto.getDtPagamento().getDate()+1);
		}
		return gastoRepository.save(gasto);
	}

	public Gasto getById(Long id) {
		Optional<Gasto> result = gastoRepository.findById(id);
		return result.orElseThrow(() -> new NotFoundException("Gasto com id " + id + ", não encontrado!"));
	}

	public List<Gasto> listAll() {
		return gastoRepository.findAll();
	}

	public void deleteById(Long id) {
		gastoRepository.deleteById(id);
	}

	public List<Gasto> findByUsuarioCod(Long cod) {
		return gastoRepository.findByUsuarioCod(cod);
	}
	public List<Gasto> findByUsuarioCodAndVencimento(Long cod, String inicio, String fim) {
		
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
		return gastoRepository.findByUsuarioCodAndVencimento(cod, inicio, fimMaisUm);
	}
	public List<Gasto> findByCartaoDeCreditoCod(Long cod) {
		return gastoRepository.findByCartaoDeCreditoCod(cod);
	}
	public List<Gasto> findByCategoriaGastoCod(Long codCategoria) {
		return gastoRepository.findByCategoriaGastoCod(codCategoria);
	}
	public Gasto efetuarPagamento(Long cod, Date dtPagamento) {
		Optional<Gasto> gasto;
		gastoRepository.efetuarPagamento(cod, dtPagamento);
		gasto = gastoRepository.findById(cod);
		return gasto.orElseThrow(() -> new NotFoundException("Gasto com id " + cod + ", não encontrado!"));
	
	}

}
