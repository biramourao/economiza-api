package com.economiza.economizaapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.economiza.economizaapi.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	public Optional<Usuario> getUsuarioByEmailAndSenha(String email, String senha);
}
