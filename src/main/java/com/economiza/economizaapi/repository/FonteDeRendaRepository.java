package com.economiza.economizaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.economiza.economizaapi.model.FonteDeRenda;

public interface FonteDeRendaRepository extends JpaRepository<FonteDeRenda, Long>{
	
	public List<FonteDeRenda> findByUsuarioCod(Long cod);

}
