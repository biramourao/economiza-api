package com.economiza.economizaapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.economiza.economizaapi.model.Gasto;

public interface GastoRepository extends JpaRepository<Gasto, Long>{

	public List<Gasto> findByUsuarioCod(Long cod);
	
	public List<Gasto> findByCartaoDeCreditoCod(Long cod);
	
	public List<Gasto> findByCategoriaGastoCodAndUsuarioCod(Long codCategoria, Long codUsuario);
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE Gasto SET dtPagamento = ?2 WHERE cod = ?1")
	public int efetuarPagamento(Long cod, Date dtPagamento);
}
