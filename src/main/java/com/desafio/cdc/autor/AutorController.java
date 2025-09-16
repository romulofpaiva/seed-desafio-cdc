package com.desafio.cdc.autor;

import java.net.URI;
import java.time.Instant;

import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<AutorResponse> criar(@Valid @RequestBody AutorRequest request) {
		Autor autor = request.toModel( );
		entityManager.persist( autor );
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(autor.getId()).toUri();

		// Retorna o status 201 com o cabeçalho Location e o corpo da resposta
		return ResponseEntity.created(uri).body(new AutorResponse(autor));
	}

	@GetMapping("/{id}")
	public ResponseEntity<AutorResponse> consultar(@PathVariable Long id) {
		Autor autor = entityManager.find( Autor.class, id);

		if(autor == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(new AutorResponse(autor));		
	}

}
