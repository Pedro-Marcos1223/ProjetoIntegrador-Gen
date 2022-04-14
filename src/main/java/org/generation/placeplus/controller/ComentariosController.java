package org.generation.placeplus.controller;

import java.util.List;

import org.generation.placeplus.model.Comentarios;
import org.generation.placeplus.repository.ComentariosRepository;
import org.generation.placeplus.repository.PostagemRepository;
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
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/comentarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ComentariosController {

	@Autowired
	private ComentariosRepository repository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PostagemRepository postagemRepository;

	@GetMapping
	public ResponseEntity<List<Comentarios>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Comentarios> getById(@PathVariable int id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/texto/{texto}")
	public ResponseEntity<List<Comentarios>> getByTexto(@PathVariable String texto) {
		return ResponseEntity.ok(repository.findAllByTextoContainingIgnoreCase(texto));
	}

	@PostMapping
	public ResponseEntity<Comentarios> post(@RequestBody Comentarios comentarios) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(comentarios));
	}

	@PutMapping
	public ResponseEntity<Comentarios> put(@RequestBody Comentarios comentarios) {
		if (usuarioRepository.existsById(comentarios.getUsuario().getId())
				&& postagemRepository.existsById(comentarios.getPostagem().getIdPostagem())) {
			return ResponseEntity.status(HttpStatus.OK).body(repository.save(comentarios));
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id de usuario ou postagem inexistente");

	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		repository.deleteById(id);
	}

}
