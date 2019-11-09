package com.economiza.economizaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.economiza.economizaapi.model.CategoriaGasto;

public interface CategoriaGastoRepository extends JpaRepository<CategoriaGasto, Long>{

	public List<CategoriaGasto> findByUsuarioCod(Long cod);
	@Transactional(readOnly = false)
	@Modifying
	public int deleteByUsuarioCod(Long cod);

}
