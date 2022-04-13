package org.generation.placeplus.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.placeplus.model.Temas;
import org.generation.placeplus.repository.TemasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemasController {

	@Autowired
	private TemasRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Temas>> getAllTemas(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Temas> getByIdTema(@PathVariable Long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nomeTema/{nomeTema}")
	public ResponseEntity<List<Temas>> getByNomeTema (@PathVariable String nomeTema){
		return ResponseEntity.ok(repository.findAllByNomeTemaContainingIgnoreCase(nomeTema));
	}
	
	@PostMapping
	public ResponseEntity<Temas> postTema (@Valid @RequestBody Temas temas){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(temas));
	}
	
	@PutMapping 
	public ResponseEntity<Temas> putTema (@Valid @RequestBody Temas temas){
		return ResponseEntity.ok(repository.save(temas));
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Long id) {
		repository.deleteById(id);
	}
}
