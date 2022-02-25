package org.generation.placeplus.controller;

import java.util.List;

import org.generation.placeplus.model.Usuario;
import org.generation.placeplus.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")

public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;
	
	//getAllUsuarios
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll(){
		
		return ResponseEntity.ok(repository.findAll());
	}	

	//getByIdUsuario
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getByIdUsuario(@PathVariable(value = "idUsuario") Integer idUsuario){
		
		return repository.findById(idUsuario).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	//getByDescricaoUsuario
	@GetMapping("/nome/{name}")
	public ResponseEntity<List<Usuario>> getByName(@PathVariable (value ="name") String name){
	
	return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(name));
		
		
	}
	
}
	
