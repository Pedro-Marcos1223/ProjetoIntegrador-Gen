package org.generation.placeplus.controller;

import java.util.List;

import org.generation.placeplus.model.Comentarios;
import org.generation.placeplus.repository.ComentariosRepository;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/comentarios")
public class ComentariosController {
	

	@Autowired
	private ComentariosRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Comentarios>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/texto/{texto}")
	public ResponseEntity<List<Comentarios>> getByTexto(@PathVariable String texto){
		return ResponseEntity.ok(repository.findAllByTextoContainingIgnoreCase(texto));
	}
	
	@PostMapping
	public ResponseEntity<Comentarios> post(@RequestBody Comentarios comentarios){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(comentarios));
	}
	
	@PutMapping
	public ResponseEntity<Comentarios> put(@RequestBody Comentarios comentarios){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(comentarios));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		repository.deleteById(id);
	}


}
