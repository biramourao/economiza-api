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
	
	public List<Gasto> findByCategoriaGastoCod(Long codCategoria);
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE Gasto SET dtPagamento = ?2 WHERE cod = ?1")
	public int efetuarPagamento(Long cod, Date dtPagamento);
	
	@Transactional(readOnly = false)
	@Modifying
	public int deleteByUsuarioCod(Long cod);
	
	@Query("SELECT g FROM Gasto g WHERE usuario.cod = ?1 and vencimento BETWEEN TO_DATE(?2,'yyyy-MM-dd') AND TO_DATE(?3, 'yyyy-MM-dd')")
	public List<Gasto> findByUsuarioCodAndVencimento(Long cod, String inicio, String fim );

}