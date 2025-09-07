package com.desafio.cdc.compra;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import org.springframework.util.StringUtils;

import com.desafio.cdc.constraintvalidators.CpfOrCnpj;
import com.desafio.cdc.constraintvalidators.ExistsId;
import com.desafio.cdc.cupom.Cupom;
import com.desafio.cdc.livro.Livro;
import com.desafio.cdc.pais.Estado;
import com.desafio.cdc.pais.Pais;

public class CompraRequest {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String nome;

	@NotBlank
	private String sobrenome;

	@NotBlank
	@CpfOrCnpj
	private String documento;

	@NotBlank
	private String endereco;

	@NotBlank
	private String complemento;

	@NotBlank
	private String cidade;

	@NotNull
	@ExistsId(domainClass = Pais.class, domainAttribute = "id")
	private Long paisId;

	@ExistsId(domainClass = Estado.class, domainAttribute = "id")
	private Long estadoId;

	@NotBlank
	private String telefone;

	@NotBlank
	private String cep;

	@NotNull
	@Positive
	private BigDecimal total;

	@ExistsId(domainClass = Cupom.class, domainAttribute = "codigo")
	private String cupom;

	@Valid
	@NotNull
	@Size(min = 1)
	private Set<CompraItemRequest> itens = new HashSet<>();

	public CompraRequest() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Long getPaisId() {
		return paisId;
	}

	public void setPaisId(Long paisId) {
		this.paisId = paisId;
	}

	public Long getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getCupom() {
		return cupom;
	}

	public void setCupom(String cupom) {
		this.cupom = cupom;
	}

	public Set<CompraItemRequest> getItens() {
		return itens;
	}

	public void setItens(Set<CompraItemRequest> itens) {
		this.itens = itens;
	}

	public void validaTotal(EntityManager em) {
		BigDecimal totalCalculado = new BigDecimal(0.00);

		for (CompraItemRequest item : itens) {
			Livro livro = em.find(Livro.class, item.getIdLivro());
			totalCalculado = totalCalculado.add(BigDecimal.valueOf(item.getQuantidade() * livro.getPreco()));
		}

		if (this.total.compareTo(totalCalculado) != 0) {
			throw new IllegalArgumentException("O valor total informado (" + this.total
					+ ") é diferente do valor total calculado (" + totalCalculado + ") da compra.");
		}
	}

	public Compra toModel(EntityManager em) {

		Pais pais = em.find(Pais.class, this.paisId);

		Compra novaCompra = new Compra(this.email, this.nome, this.sobrenome, this.documento, this.endereco,
				this.complemento, this.cidade, pais, this.telefone, this.cep, this.total);

		if (StringUtils.hasText(this.cupom)) {
			Query queryCupom = em.createQuery("SELECT c FROM Cupom c WHERE codigo = ?1", Cupom.class);
			queryCupom.setParameter(1, cupom);

			Cupom cupomDesconto = (Cupom) queryCupom.getResultList().get(0);

			Query queryCompra = em.createQuery("SELECT c FROM " + Compra.class.getName() + " c WHERE cupom_id = ?1");
			queryCompra.setParameter(1, cupomDesconto.getId());

			if (!queryCompra.getResultList().isEmpty()) {
				throw new IllegalArgumentException("Cupom já utilizado.");
			}

			if (cupomDesconto != null) {
				novaCompra.aplicaCupom(cupomDesconto);
			}
		}

		if (this.estadoId != null) {
			Estado estado = em.find(Estado.class, this.estadoId);
			if (estado != null) {
				novaCompra.setEstado(estado);
			}
		}

		return novaCompra;
	}
}
