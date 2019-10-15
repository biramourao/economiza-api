package com.economiza.economizaapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.economiza.economizaapi.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query("SELECT u FROM Usuario u WHERE email=?1 AND senha=?2")
	public Optional<Usuario> login(String email, String senha);
}
