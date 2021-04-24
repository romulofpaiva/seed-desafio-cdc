package com.desafio.cdc.livro;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/livros")
public class LivroController {
	
	@PersistenceContext
	EntityManager entityManager;

	@PostMapping
	@Transactional
	public ResponseEntity<?> criar(@Valid @RequestBody LivroRequest request) {
		
		Livro livro = request.toModel(entityManager);
		entityManager.persist(livro);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
