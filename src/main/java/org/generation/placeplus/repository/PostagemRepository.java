package org.generation.placeplus.repository;

import java.util.List;

import org.generation.placeplus.model.Postagem;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository {

	public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);
	
	public List<Postagem> findAllByTemaContainingIgnoreCase(String tema);
}
