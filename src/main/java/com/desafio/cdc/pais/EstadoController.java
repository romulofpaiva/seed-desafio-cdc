package com.desafio.cdc.pais;

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
@RequestMapping("/paises/estados")
public class EstadoController {
	
	@PersistenceContext
	EntityManager em;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> criar(@Valid @RequestBody EstadoRequest request ) {
		Pais pais = em.find(Pais.class, request.getPaisId());
		
		Estado estado = new Estado( request.getNome(), pais );
		em.persist(estado);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(estado.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

}
