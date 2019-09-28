package com.economiza.economizaapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.economiza.economizaapi.model.CartaoDeCredito;

@Repository
public interface CartaoDeCreditoRepository extends JpaRepository<CartaoDeCredito, Long>{

	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE CartaoDeCredito SET saldo_restante = ?2 WHERE cod = ?1")
	public int alteraSaldoRestante(Long cod, double saldoRestante);
	
	
}

