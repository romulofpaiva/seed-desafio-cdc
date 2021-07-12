package com.desafio.cdc.pais;

import com.desafio.cdc.constraintvalidators.UniqueValue;

public class PaisRequest {
	
	@UniqueValue(domainClass = Pais.class, domainAttribute = "nome")
	private String nome;
	
	public PaisRequest() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
}
