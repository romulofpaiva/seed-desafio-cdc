package com.desafio.cdc.compra;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import com.desafio.cdc.livro.Livro;

@Embeddable
public class CompraItemId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne
	@JoinColumn(name="compra_id")
	private Compra compra;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="livro_id")
	private Livro livro;
	
	public CompraItemId() {
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	@Override
	public int hashCode() {
		return Objects.hash(livro, compra);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompraItemId other = (CompraItemId) obj;
		return Objects.equals(livro, other.livro) && Objects.equals(compra, other.compra);
	}

}
