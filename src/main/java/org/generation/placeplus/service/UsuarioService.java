package org.generation.placeplus.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.placeplus.model.Usuario;
import org.generation.placeplus.model.UsuarioLogin;
import org.generation.placeplus.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		if(repository.findByUsuario(usuario.getUsuario()).isPresent()) 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
		usuario.setSenha(criptografarSenha(usuario.getSenha()));
		return Optional.of(repository.save(usuario));
	}

	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());

		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());
				user.get().setId(usuario.get().getId());
				user.get().setFoto(usuario.get().getFoto());
				user.get().setTipo(usuario.get().getTipo());
				user.get().setBio(usuario.get().getBio());
				user.get().setSenha(usuario.get().getSenha());
				return user;

			}
		}
		return null;

	}
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario) {

		if (repository.findById(usuario.getId()).isPresent()) {
			Optional<Usuario> buscaUsuario = repository.findByUsuario(usuario.getUsuario());

			if (buscaUsuario.isPresent()) {				
				if (buscaUsuario.get().getId() != usuario.getId())
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
			}
			
			usuario.setSenha(criptografarSenha(usuario.getSenha()));

			return Optional.of(repository.save(usuario));
		} 
			
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);		
	}
	
	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(senha);

		return senhaEncoder;
	}
	
	private String generatorBasicToken(String email, String password) {
		String structure = email + ":" + password;
		byte[] structureBase64 = Base64.encodeBase64(structure.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(structureBase64);
	}
	
	
	
}
