package com.economiza.economizaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.economiza.economizaapi.model.FonteDeRenda;

public interface FonteDeRendaRepository extends JpaRepository<FonteDeRenda, Long>{
	
	public List<FonteDeRenda> findByUsuarioCod(Long cod);
	@Transactional(readOnly = false)
	@Modifying
	public int deleteByUsuarioCod(Long cod);

}
