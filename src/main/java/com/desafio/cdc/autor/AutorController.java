package com.desafio.cdc.autor;

import java.net.URI;
import java.time.Instant;

import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/autores")
public class AutorController {

	@Autowired
	private EntityManager entityManager;

	@PostMapping
	@Transactional
	public ResponseEntity<?> criar(@Valid @RequestBody AutorRequest request) {
		Autor autor = new Autor(request.getNome(), request.getEmail(), request.getDescricao(), Instant.now());
		entityManager.persist(autor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(autor.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

}
