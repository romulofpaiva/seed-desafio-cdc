package com.desafio.cdc.pais;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		
		return ResponseEntity.created(null).build();
	}

}
