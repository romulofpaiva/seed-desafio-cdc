package com.desafio.cdc.livro;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import com.desafio.cdc.autor.Autor;
import com.desafio.cdc.categoria.Categoria;
import com.desafio.cdc.constraintvalidators.ExistsId;
import com.desafio.cdc.constraintvalidators.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class LivroRequest {

	@NotBlank
	@UniqueValue(domainClass = Livro.class, domainAttribute = "titulo")
	private String titulo;

	@NotBlank
	@Length(min = 1, max = 500)
	private String resumo;

	@NotBlank
	private String sumario;

	@NotNull
	@Min(20)
	private Double preco;

	@NotNull
	@Min(100)
	private Integer numeroPaginas;

	@NotBlank
	@UniqueValue(domainClass = Livro.class, domainAttribute = "isbn")
	private String isbn;

	@NotNull
	@Future
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate dataPublicacao;

	@NotNull
	@ExistsId(domainClass = Categoria.class, domainAttribute = "id")
	private Long categoriaId;

	@NotNull
	@ExistsId(domainClass = Autor.class, domainAttribute = "id")
	private Long autorId;

	public LivroRequest(@NotBlank String titulo, @NotBlank @Length(min = 1, max = 500) String resumo,
			@NotBlank String sumario, @NotNull @Min(20) Double preco, @NotNull @Min(100) Integer numeroPaginas,
			@NotBlank String isbn, @NotNull Long categoriaId, @NotNull Long autorId) {
		super();
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.numeroPaginas = numeroPaginas;
		this.isbn = isbn;
		this.categoriaId = categoriaId;
		this.autorId = autorId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getSumario() {
		return sumario;
	}

	public void setSumario(String sumario) {
		this.sumario = sumario;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;

		Assert.state(dataPublicacao.isAfter(LocalDate.now()),
				"A Data de Publicação deve ser maior que a data atual.");
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Long getAutorId() {
		return autorId;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}

	public Livro toModel(EntityManager em) {
		Categoria categoria = em.find(Categoria.class, categoriaId);
		Assert.state(categoria != null, "A Categoria informada não está cadastrada.");

		Autor autor = em.find(Autor.class, autorId);
		Assert.state(autor != null, "O Autor informado não está cadastrado.");

		return new Livro(titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao, categoria, autor);
	}

}
