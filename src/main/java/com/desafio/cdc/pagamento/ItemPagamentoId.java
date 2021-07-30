package com.desafio.cdc.pagamento;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.desafio.cdc.livro.Livro;

@Embeddable
public class ItemPagamentoId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne
	@JoinColumn(name="pagamento_id")
	private Pagamento pagamento;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="livro_id")
	private Livro livro;
	
	public ItemPagamentoId() {
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	@Override
	public int hashCode() {
		return Objects.hash(livro, pagamento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPagamentoId other = (ItemPagamentoId) obj;
		return Objects.equals(livro, other.livro) && Objects.equals(pagamento, other.pagamento);
	}

}
