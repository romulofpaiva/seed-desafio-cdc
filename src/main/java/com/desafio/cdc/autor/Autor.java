package com.desafio.cdc.autor;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String descricao;

	@NotNull
	private Instant criadoEm;
	
	public Autor() {
	}

	public Autor(@NotBlank String nome, @NotBlank @Email String email, @NotBlank String descricao,
			@NotBlank Instant criadoEm) {
		super();
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
		this.criadoEm = criadoEm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Instant getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Instant criadoEm) {
		this.criadoEm = criadoEm;
	}
	
}
