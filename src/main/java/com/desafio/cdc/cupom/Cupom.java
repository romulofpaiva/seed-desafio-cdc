package com.desafio.cdc.cupom;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Cupom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String codigo;
	
	@NotNull
	@Positive
	private Double percentual;
	
	@NotNull
	@Future
	private LocalDate validade;

	public Cupom() {
	}
	
	public Cupom(@NotBlank String codigo, @NotNull @Positive Double percentual, @NotNull @Future LocalDate validade) {
		super();
		this.codigo = codigo;
		this.percentual = percentual;
		this.validade = validade;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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

	public boolean isValid() {
		return validade.isAfter(LocalDate.now());
	}

}
