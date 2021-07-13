package com.desafio.cdc.pagamento;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
		TypedQuery<Estado> tqEstado = em.createQuery(
				"SELECT e FROM " + Estado.class.getName() + " e WHERE pais_id = " + request.getPaisId(), Estado.class);
		
		List<Estado> estadosList = tqEstado.getResultList();
		
		if(!estadosList.isEmpty()) {
			if(request.getEstadoId() == 0) {
				return ResponseEntity.badRequest().body("Estado não informado.");
			}
			else if(em.find(Estado.class, request.getEstadoId()) == null) {
				return ResponseEntity.badRequest().body("Estado não cadastrado.");
			}
		}
		
		return ResponseEntity.created(null).build();
	}

}
