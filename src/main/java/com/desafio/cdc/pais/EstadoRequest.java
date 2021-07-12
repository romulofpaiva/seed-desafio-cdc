package com.desafio.cdc.pais;

import com.desafio.cdc.constraintvalidators.ExistsId;
import com.desafio.cdc.constraintvalidators.UniqueValue;

public class EstadoRequest {
	
	@UniqueValue(domainClass = Estado.class, domainAttribute = "nome")
	private String nome;
	
	@ExistsId(domainClass = Pais.class, domainAttribute = "id")
	private Long paisId;
	
		public EstadoRequest() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getPaisId() {
		return paisId;
	}

	public void setPaisId(Long paisId) {
		this.paisId = paisId;
	}
}
