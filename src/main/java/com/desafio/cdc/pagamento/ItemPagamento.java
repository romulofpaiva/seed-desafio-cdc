package com.desafio.cdc.pagamento;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import com.desafio.cdc.livro.Livro;

@Entity
public class ItemPagamento {
	
	@EmbeddedId
	private ItemPagamentoId id = new ItemPagamentoId();
	
	@NotNull
	@Positive
	private Integer quantidade;
	
	public ItemPagamento() {
	}

	public ItemPagamento(@NotNull Pagamento pagamento, @NotNull Livro livro, @NotNull @Positive Integer quantidade) {
		super();
		this.id.setPagamento(pagamento);
		this.id.setLivro(livro);
		this.quantidade = quantidade;

		Assert.state(pagamento != null, "Pagamento é obrigatório!");
		Assert.state(livro != null, "Livro é obrigatório!");
		Assert.state(quantidade > 0, "Quantidade deve ser maior que zero!");
	}

	public Pagamento getPagamento() {
		return this.id.getPagamento();
	}

	public Livro getLivro() {
		return this.id.getLivro();
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
