package com.desafio.cdc.compra;

import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import com.desafio.cdc.constraintvalidators.ExistsId;
import com.desafio.cdc.livro.Livro;

public class CompraItemRequest {
	
	@NotNull
	@ExistsId(domainClass = Livro.class, domainAttribute = "id")
	private Long idLivro;
	
	@NotNull
	@Positive
	private Integer quantidade;
	
	public CompraItemRequest() {
	}

	public Long getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(Long idLivro) {
		this.idLivro = idLivro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public CompraItem toModel(EntityManager em, Compra compra) {
		return new CompraItem(compra, em.find(Livro.class, this.idLivro), this.quantidade);
	}
}
