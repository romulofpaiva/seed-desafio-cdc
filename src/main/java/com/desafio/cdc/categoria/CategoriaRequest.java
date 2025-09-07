package com.desafio.cdc.categoria;

import jakarta.validation.constraints.NotBlank;

import com.desafio.cdc.constraintvalidators.UniqueValue;

public class CategoriaRequest {
	
	@UniqueValue(domainClass = Categoria.class, domainAttribute = "nome")
	@NotBlank
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
