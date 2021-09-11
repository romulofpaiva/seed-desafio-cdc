package com.desafio.cdc.cupom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cupons")
public class CupomController {
	
	@PersistenceContext
	EntityManager em;
	
	@PostMapping
	@Transactional
	public String criar(@Valid @RequestBody CupomRequest request) {
		
		Cupom cupom = request.toModel();
		em.persist(cupom);
		
		return "cupom gerado";
	}

}
