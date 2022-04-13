package org.generation.placeplus.repository;

import java.util.List;

import org.generation.placeplus.model.Comentarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentariosRepository extends JpaRepository<Comentarios, Integer> {
	public List<Comentarios> findAllByTextoContainingIgnoreCase(String texto);
	
}
