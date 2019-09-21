package com.economiza.economizaapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.economiza.economizaapi.model.CartaoDeCredito;

@Repository
public interface CartaoDeCreditoRepository extends JpaRepository<CartaoDeCredito, Long>{

	@Query("UPDATE cartao_de_credito SET saldo_restante = ?2 WHERE cod = ?1")
	public Optional<CartaoDeCredito> calcularSaldoRestante(Long cod, double saldoRestante);
}
