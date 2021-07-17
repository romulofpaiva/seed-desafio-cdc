package com.desafio.cdc.pagamento;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cdc.pais.Estado;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
	
	@PersistenceContext
	EntityManager em;
	
	@PostMapping
	public ResponseEntity<?> criar(@Valid @RequestBody PagamentoRequest request) {
		if(!isEstadoValido(request.getPaisId(), request.getEstadoId())) {
			return ResponseEntity.badRequest().body("Estado não cadastrado para o País informado.");
		}
		
		return ResponseEntity.created(null).build();
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
