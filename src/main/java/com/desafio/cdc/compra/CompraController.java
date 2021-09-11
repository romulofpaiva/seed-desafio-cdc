package com.desafio.cdc.compra;

import java.math.BigDecimal;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.desafio.cdc.pais.Estado;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@RestController
@RequestMapping("/compras")
public class CompraController {

	@PersistenceContext
	EntityManager em;

	@PostMapping
	@Transactional
	public ResponseEntity<?> criar(@Valid @RequestBody CompraRequest request) {
		request.validaTotal(em);

		if (!isEstadoValido(request.getPaisId(), request.getEstadoId())) {
			return ResponseEntity.badRequest().body("Estado não cadastrado para o País informado.");
		}

		Compra compra = request.toModel(em);
		em.persist(compra);

		Set<CompraItem> itens = processaCompraItem(request.getItens(), compra);

		compra.getItens().addAll(itens);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(compra.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@Transactional
	private Set<CompraItem> processaCompraItem(Set<CompraItemRequest> itensRequest, Compra compra) {
		Set<CompraItem> itensResponse = new HashSet<>();

		for (CompraItemRequest item : itensRequest) {
			CompraItem ip = item.toModel(em, compra);
			em.persist(ip);
			itensResponse.add(ip);
		}

		return itensResponse;
	}

	private boolean isEstadoValido(Long paisId, Long estadoId) {

		// País sem Estado
		if (estadoId == null) {
			return true;
		}

		// Valida se o Estado pertence ao País informado.
		// A CompraRequest já valida a existência do Estado.
		Estado estado = em.find(Estado.class, estadoId);

		return estado.getPais().getId() == paisId;
	}

	@GetMapping("/{id}")
	public Object listarCompra(@PathVariable(name = "id") Long id) {
		Compra compra = em.find(Compra.class, id);

		ListaCompraComDesconto listaCompra = new ListaCompraComDesconto(compra);

		return listaCompra;
	}

	@JsonSerialize
	class ListaCompraComDesconto {
		private boolean cupom;
		private BigDecimal valorCompra;

		public ListaCompraComDesconto(Compra compra) {
			if(compra.getCupom() != null) {
				this.cupom = true;
				this.valorCompra = compra.getTotal().multiply(BigDecimal.valueOf(1 - compra.getCupom().getPercentual()/100));
			}
		}
		
		public boolean getCupom() {
			return cupom;
		}
		
		public BigDecimal getValorCompra() {
			return valorCompra;
		}
		
	}

}
