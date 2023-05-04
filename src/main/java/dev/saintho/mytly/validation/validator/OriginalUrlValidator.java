package dev.saintho.mytly.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import dev.saintho.mytly.validation.annotation.OriginalUrlConstraint;

public class OriginalUrlValidator implements ConstraintValidator<OriginalUrlConstraint, String> {
	private final String httpPrefix = "http://";
	private final String httpsPrefix = "https://";
	@Override
	public void initialize(OriginalUrlConstraint constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value.isBlank()) {
			return false;
		}

		return value.startsWith(httpPrefix) || value.startsWith(httpsPrefix);
	}
}
