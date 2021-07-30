package com.desafio.cdc.pagamento;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.desafio.cdc.constraintvalidators.CpfOrCnpj;
import com.desafio.cdc.constraintvalidators.ExistsId;
import com.desafio.cdc.livro.Livro;
import com.desafio.cdc.pais.Estado;
import com.desafio.cdc.pais.Pais;

public class PagamentoRequest {
	
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
	
	@Valid
	@NotNull
	@Size(min = 1)
	private Set<ItemPagamentoRequest> itens = new HashSet<>();
	
	public PagamentoRequest() {
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

	public Set<ItemPagamentoRequest> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPagamentoRequest> itens) {
		this.itens = itens;
	}
	
	public void validaTotal(EntityManager em) {
		BigDecimal totalCalculado = new BigDecimal(0.00);
		
		for (ItemPagamentoRequest item : itens) {
			Livro livro = em.find(Livro.class, item.getIdLivro());
			totalCalculado = totalCalculado.add(BigDecimal.valueOf(item.getQuantidade() * livro.getPreco()));
		}
		
		if(this.total.compareTo(totalCalculado) != 0) {
			throw new IllegalArgumentException("O valor total informado (" + this.total +") é diferente do valor total calculado (" + totalCalculado + ") da compra.");
		}
	}

	public Pagamento toModel(EntityManager em) {
		return new Pagamento(this.email, this.nome, this.sobrenome, this.documento,
				this.endereco, this.complemento, this.cidade, em.find(Pais.class, this.paisId),
				this.estadoId == null ? null : em.find(Estado.class, this.estadoId), this.telefone, this.cep,
				this.total);
	}
}
