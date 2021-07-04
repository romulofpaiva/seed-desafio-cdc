package com.desafio.cdc.livro;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.desafio.cdc.livro.LivroController.LivroForList;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@GetMapping
	public String listar() throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		TypedQuery<Livro> typedQuery = entityManager.createQuery("SELECT l FROM Livro l", Livro.class);
		
		List<LivroForList> livros =  
				typedQuery.getResultList()
				.stream()
				.map( l -> new LivroForList(l.getId(), l.getTitulo()) ).collect(Collectors.toList());
				
		return mapper.writeValueAsString(livros);
	}

	class LivroForList {
		Long id;
		String titulo;
		
		LivroForList() {
		}
		
		LivroForList(Long id, String titulo) {
			this.id = id;
			this.titulo = titulo;
		}
		
		public Long getId() {
			return id;
		}
		
		public String getTitulo() {
			return titulo;
		}
	}
}