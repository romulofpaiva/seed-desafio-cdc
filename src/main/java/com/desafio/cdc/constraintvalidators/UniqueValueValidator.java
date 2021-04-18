package com.desafio.cdc.constraintvalidators;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

	@PersistenceContext
	private EntityManager entityManager;

	private String domainAttribute;

	private Class<?> domainClass;

	@Override
	public void initialize(UniqueValue uniqueValue) {
		domainAttribute = uniqueValue.domainAttribute();
		domainClass = uniqueValue.domainClass();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = entityManager.createQuery("select 1 from " + domainClass.getName() + " where " + domainAttribute + " = ?1");

		query.setParameter(1, value);

		boolean isValid = query.getResultList().isEmpty();

		Assert.state(isValid,
				"Já existe um " + domainClass.getSimpleName() + " cadastrado com " + domainAttribute + " igual à " + value + ".");

		return isValid;
	}

}
