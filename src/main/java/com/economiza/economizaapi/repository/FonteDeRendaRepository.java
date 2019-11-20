package com.economiza.economizaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.economiza.economizaapi.model.FonteDeRenda;
import com.economiza.economizaapi.model.Gasto;

public interface FonteDeRendaRepository extends JpaRepository<FonteDeRenda, Long>{
	
	public List<FonteDeRenda> findByUsuarioCod(Long cod);
	@Transactional(readOnly = false)
	@Modifying
	public int deleteByUsuarioCod(Long cod);
	
	@Query("SELECT f FROM FonteDeRenda f WHERE usuario.cod = ?1 and dtValidade BETWEEN TO_DATE(?2,'yyyy-MM-dd') AND TO_DATE(?3, 'yyyy-MM-dd')")
	public List<FonteDeRenda> findByUsuarioCodAndDtValidade(Long cod, String inicio, String fim );

}
