package com.desafio.cdc.compra;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

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

import com.desafio.cdc.pais.Estado;

@RestController
@RequestMapping("/compras")
public class CompraController {
	
	@PersistenceContext
	EntityManager em;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> criar(@Valid @RequestBody CompraRequest request) {
		request.validaTotal(em);
		
		if(!isEstadoValido(request.getPaisId(), request.getEstadoId())) {
			return ResponseEntity.badRequest().body("Estado não cadastrado para o País informado.");
		}
		
		Compra compra = request.toModel(em);
		em.persist(compra);
		
		Set<CompraItem> itens = processaCompraItem(request.getItens(), compra);
		
		compra.getItens().addAll(itens);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(compra.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@Transactional
	private Set<CompraItem> processaCompraItem(Set<CompraItemRequest> itensRequest, Compra compra) {
		Set<CompraItem> itensResponse = new HashSet<>();
		
		for( CompraItemRequest item : itensRequest ) {
			CompraItem ip = item.toModel(em, compra);
			em.persist(ip);
			itensResponse.add(ip);
		}
				
		return itensResponse;
	}

	private boolean isEstadoValido(Long paisId, Long estadoId) {
		
		// País sem Estado
		if(estadoId == null) {
			return true;
		}
		
		// Valida se o Estado pertence ao País informado.
		// A CompraRequest já valida a existência do Estado.
		Estado estado = em.find(Estado.class, estadoId);
		
		return estado.getPais().getId() == paisId;
	}

}
