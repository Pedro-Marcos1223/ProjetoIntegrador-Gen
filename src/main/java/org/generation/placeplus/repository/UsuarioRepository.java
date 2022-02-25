package org.generation.placeplus.repository;

import java.util.List;

import org.generation.placeplus.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	public List<Usuario> findAllByNomeContainingIgnoreCase(String nome);
}