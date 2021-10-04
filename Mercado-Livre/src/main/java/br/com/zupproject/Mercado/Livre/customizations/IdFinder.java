package br.com.zupproject.Mercado.Livre.customizations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
	@Constraint(validatedBy = {IdFinderValidator.class})
	@Target({ ElementType.FIELD})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface IdFinder {

		String message() default "O id informado não foi encontrado";
		Class<?>[] groups() default {};
		Class<? extends Payload>[] payload() default {};
		String fieldName();
		Class<?> domainClass();
	}

