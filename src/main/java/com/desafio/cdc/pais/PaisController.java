package com.desafio.cdc.pais;

import java.net.URI;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/paises")
public class PaisController {

	@PersistenceContext
	EntityManager entityManager;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> criar(@Valid @RequestBody PaisRequest request) {
		
		Pais pais = new Pais(request.getNome());
		
		entityManager.persist(pais);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(pais.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
