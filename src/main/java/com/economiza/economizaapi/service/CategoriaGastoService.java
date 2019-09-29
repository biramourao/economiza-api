package com.economiza.economizaapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.economiza.economizaapi.model.CategoriaGasto;
import com.economiza.economizaapi.repository.CategoriaGastoRepository;

import javassist.NotFoundException;

public class CategoriaGastoService {

@Autowired private CategoriaGastoRepository categoriaGastoRepository;
	
	public CategoriaGasto save(CategoriaGasto categoriaGasto) {		
		return categoriaGastoRepository.save(categoriaGasto);
	}
	
	public CategoriaGasto update(CategoriaGasto categoriaGasto) {
		return categoriaGastoRepository.save(categoriaGasto);
	}
	
	public CategoriaGasto getById(Long id) throws NotFoundException {
		Optional<CategoriaGasto> result = categoriaGastoRepository.findById(id);
		return result.orElseThrow(()-> new NotFoundException("Categoria não encontrada = " + id));
	}
	
	public List<CategoriaGasto> listAll() {
		List<CategoriaGasto> categoriasGasto = categoriaGastoRepository.findAll();
		return categoriasGasto;
	}
}
