package com.desafio.cdc.constraintvalidators;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {

	@PersistenceContext
	private EntityManager entityManager;

	private String domainAttribute;

	private Class<?> domainClass;

	@Override
	public void initialize(ExistsId existsid) {
		domainAttribute = existsid.domainAttribute();
		domainClass = existsid.domainClass();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(value == null) {
			return true;
		}
		
		Query query = entityManager
				.createQuery("select 1 from " + domainClass.getName() + " where " + domainAttribute + " = ?1");

		query.setParameter(1, value);

		boolean exists = !query.getResultList().isEmpty();

		Assert.state(exists == true, "Não existe um(a) " + domainClass.getSimpleName() + " cadastrado(a) com " + domainAttribute
				+ " igual à " + value + ".");

		return exists;
	}

}
