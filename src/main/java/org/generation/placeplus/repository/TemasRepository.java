package org.generation.placeplus.repository;

import java.util.List;

import org.generation.placeplus.model.Temas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemasRepository extends JpaRepository<Temas, Long> {

	public List<Temas> findAllByNomeTemaContainingIgnoreCase(String nomeTema);
}
