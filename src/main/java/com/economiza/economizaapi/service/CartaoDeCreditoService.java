package com.economiza.economizaapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economiza.economizaapi.model.CartaoDeCredito;
import com.economiza.economizaapi.repository.CartaoDeCreditoRepository;

import javassist.NotFoundException;

@Service
public class CartaoDeCreditoService {

	@Autowired
	private CartaoDeCreditoRepository cartaoDeCreditoRepository;

	
	public CartaoDeCredito save(CartaoDeCredito cartaoDeCredito) {
		
		return cartaoDeCreditoRepository.save(cartaoDeCredito);
		
	}
	
	
	public CartaoDeCredito update(CartaoDeCredito cartaoDeCredito) {
		return cartaoDeCreditoRepository.save(cartaoDeCredito);	
	}
	
	
	public CartaoDeCredito getById(Long id) throws NotFoundException {
		Optional<CartaoDeCredito> result = cartaoDeCreditoRepository.findById(1L);
		
		return result.orElseThrow(()-> new NotFoundException("Cartão de Crédito não encontrado = " + id));
	}
	
	public List<CartaoDeCredito> listAll() {
		return cartaoDeCreditoRepository.findAll();
	}
	
	
	public void deleteById(Long id) {
		cartaoDeCreditoRepository.deleteById(id);		
	}
	
	
	public int AlteraSaldoRestante(Long id, double valor) {
		return cartaoDeCreditoRepository.alteraSaldoRestante(id, valor);
	}
	
	public List<CartaoDeCredito> FindByUsuarioCod(Long cod) {
		return cartaoDeCreditoRepository.findByUsuarioCod(cod);
		
	}
	
}
