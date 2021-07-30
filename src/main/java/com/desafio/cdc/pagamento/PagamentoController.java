package com.desafio.cdc.pagamento;

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
@RequestMapping("/pagamentos")
public class PagamentoController {
	
	@PersistenceContext
	EntityManager em;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> criar(@Valid @RequestBody PagamentoRequest request) {
		request.validaTotal(em);
		
		if(!isEstadoValido(request.getPaisId(), request.getEstadoId())) {
			return ResponseEntity.badRequest().body("Estado não cadastrado para o País informado.");
		}
		
		Pagamento pagamento = request.toModel(em);
		em.persist(pagamento);
		
		Set<ItemPagamento> itens = processaItemPagamento(request.getItens(), pagamento);
		
		pagamento.getItens().addAll(itens);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(pagamento.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@Transactional
	private Set<ItemPagamento> processaItemPagamento(Set<ItemPagamentoRequest> itensRequest, Pagamento pagamento) {
		Set<ItemPagamento> itensResponse = new HashSet<>();
		
		for( ItemPagamentoRequest item : itensRequest ) {
			ItemPagamento ip = item.toModel(em, pagamento);
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
		// O PagamentoRequest já valida a existência do Estado.
		Estado estado = em.find(Estado.class, estadoId);
		
		return estado.getPais().getId() == paisId;
	}

}
