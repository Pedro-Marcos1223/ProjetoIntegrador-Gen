package org.generation.placeplus.controller;

import java.util.List;

import org.generation.placeplus.model.Usuario;
import org.generation.placeplus.repository.UsuarioRepository;
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
@RequestMapping("/usuario")
@CrossOrigin("*")

public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	// getAllUsuarios
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll() {

		return ResponseEntity.ok(repository.findAll());
	}

	// getByIdUsuario
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable (value = "id") Integer id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	// getByDescricaoUsuario
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Usuario>> getByName(@PathVariable (value = "nome") String nome) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	
	@PostMapping
	public ResponseEntity<Usuario> post(@RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
	}
	
	@PutMapping
	public ResponseEntity<Usuario> put(@RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuario));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		repository.deleteById(id);
	}
}