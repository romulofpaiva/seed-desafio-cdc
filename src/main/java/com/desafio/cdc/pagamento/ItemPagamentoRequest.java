package com.desafio.cdc.pagamento;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.desafio.cdc.constraintvalidators.ExistsId;
import com.desafio.cdc.livro.Livro;

public class ItemPagamentoRequest {
	
	@NotNull
	@ExistsId(domainClass = Livro.class, domainAttribute = "id")
	private Long idLivro;
	
	@NotNull
	@Positive
	private Integer quantidade;
	
	public ItemPagamentoRequest() {
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
	
	public ItemPagamento toModel(EntityManager em, Pagamento pagamento) {
		return new ItemPagamento(pagamento, em.find(Livro.class, this.idLivro), this.quantidade);
	}
}
