package com.economiza.economizaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.economiza.economizaapi.model.CategoriaGasto;

public interface CategoriaGastoRepository extends JpaRepository<CategoriaGasto, Long>{

	public List<CategoriaGasto> findByUsuarioCod(Long cod);

}
