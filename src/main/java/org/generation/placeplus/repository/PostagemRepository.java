package org.generation.placeplus.repository;

import java.util.List;

import org.generation.placeplus.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Integer>{

	public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);
	
	public List<Postagem> findAllByTemaContainingIgnoreCase(String tema);
}
