package com.desafio.cdc.autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AutorRequest {

	@NotBlank(message = "Nome obrigatório")
	private String nome;

	@NotBlank(message = "Email obrigatório")
	@Email(message = "Email em formato inválido")
	private String email;

	@NotBlank(message = "Descrição obrigatória")
	@Size(min = 1, max = 400)
	private String descricao;
	
	public AutorRequest(@NotBlank(message = "Nome obrigatório") String nome,
			@NotBlank(message = "Email obrigatório") @Email(message = "Email em formato inválido") String email,
			@NotBlank(message = "Descrição obrigatória") @Size(min = 1, max = 400) String descricao) {
		super();
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
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

}
