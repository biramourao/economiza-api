package com.economiza.economizaapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economiza.economizaapi.exception.NotFoundException;
import com.economiza.economizaapi.model.CategoriaGasto;
import com.economiza.economizaapi.repository.CategoriaGastoRepository;


@Service
public class CategoriaGastoService {

@Autowired private CategoriaGastoRepository categoriaGastoRepository;
	
	public CategoriaGasto save(CategoriaGasto categoriaGasto) {		
		return categoriaGastoRepository.save(categoriaGasto);
	}
	
	public CategoriaGasto update(CategoriaGasto categoriaGasto) {
		return categoriaGastoRepository.save(categoriaGasto);
	}
	
	public CategoriaGasto getById(Long id){
		Optional<CategoriaGasto> result = categoriaGastoRepository.findById(id);
		return result.orElseThrow(()-> new NotFoundException("Categoria com id " + id + ", não encontrado!"));
	}
	
	public List<CategoriaGasto> listAll() {
		List<CategoriaGasto> categoriasGasto = categoriaGastoRepository.findAll();
		return categoriasGasto;
	}
	
}
