package com.desafio.cdc.compra;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.springframework.util.Assert;

import com.desafio.cdc.livro.Livro;

@Entity
public class CompraItem {
	
	@EmbeddedId
	private CompraItemId id = new CompraItemId();
	
	@NotNull
	@Positive
	private Integer quantidade;
	
	public CompraItem() {
	}

	public CompraItem(@NotNull Compra compra, @NotNull Livro livro, @NotNull @Positive Integer quantidade) {
		super();
		this.id.setCompra(compra);
		this.id.setLivro(livro);
		this.quantidade = quantidade;

		Assert.state(compra != null, "Compra é obrigatório!");
		Assert.state(livro != null, "Livro é obrigatório!");
		Assert.state(quantidade > 0, "Quantidade deve ser maior que zero!");
	}

	public Compra getCompra() {
		return this.id.getCompra();
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
