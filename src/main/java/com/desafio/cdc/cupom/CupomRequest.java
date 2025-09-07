package com.desafio.cdc.cupom;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import com.desafio.cdc.constraintvalidators.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class CupomRequest {

	@NotBlank
	@UniqueValue(domainClass = Cupom.class, domainAttribute = "codigo")
	private String codigo;
	
	@NotNull
	@Positive
	private Double percentual;
	
	@NotNull
	@Future
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate validade;
	
	public CupomRequest() {
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Double getPercentual() {
		return percentual;
	}

	public void setPercentual(Double percentual) {
		this.percentual = percentual;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}

	@Override
	public String toString() {
		return "CupomRequest [codigo=" + codigo + ", percentual=" + percentual + ", validade="
				+ validade + "]";
	}

	public Cupom toModel() {
		return new Cupom(codigo, percentual, validade);
	}
	
}
