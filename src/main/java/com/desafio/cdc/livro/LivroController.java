package com.desafio.cdc.livro;

import java.net.URI;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping("/livros")
public class LivroController {
	
	@PersistenceContext
	EntityManager entityManager;

	@PostMapping
	@Transactional
	public Object criar(@Valid @RequestBody LivroRequest request) {
		
		Livro livro = request.toModel(entityManager);
		entityManager.persist(livro);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public Object listarLivros() throws JsonProcessingException {
		
		TypedQuery<Livro> typedQuery = entityManager.createQuery("SELECT l FROM Livro l", Livro.class);

		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("filter", SimpleBeanPropertyFilter.filterOutAllExcept("id", "titulo"));
		
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.writer(filters).writeValueAsString(typedQuery.getResultList());
	}
	
	@GetMapping("/{id}")
	public Object listarUmLivro(@PathVariable Long id) throws JsonProcessingException {
		
		Livro livro = entityManager.find(Livro.class, id);
		
		if(livro == null) {
			return ResponseEntity.notFound().build();
		}
		
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("filter", SimpleBeanPropertyFilter.serializeAll());
		
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.writer(filters).writeValueAsString(livro);
	}
}