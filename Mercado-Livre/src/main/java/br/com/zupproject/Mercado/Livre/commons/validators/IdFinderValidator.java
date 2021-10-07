package br.com.zupproject.Mercado.Livre.commons.validators;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class IdFinderValidator implements ConstraintValidator<IdFinder, Object> {

	private String domainAttribute;
	private Class<?> klass;
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void initialize(IdFinder params) {
		domainAttribute = params.fieldName();
		klass = params.domainClass();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		if(Objects.isNull(value)) {
			return true;
		}
		
		Query query = manager.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + "=:value");
		query.setParameter("value", value);
		
		List<?> list = query.getResultList();
		Assert.isTrue(list.size() <=1, "Não foi encontrado o valor para o atributo desejado: " + value);
		
		return !list.isEmpty();
	}

}
