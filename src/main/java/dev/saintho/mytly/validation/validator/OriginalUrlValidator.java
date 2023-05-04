package dev.saintho.mytly.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import dev.saintho.mytly.validation.annotation.ValidOriginalUrl;

public class OriginalUrlValidator implements ConstraintValidator<ValidOriginalUrl, String> {
	private final String httpPrefix = "http://";
	private final String httpsPrefix = "https://";
	@Override
	public void initialize(ValidOriginalUrl constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value.isEmpty()) {
			return false;
		}

		if (value.length() > 255) {
			return false;
		}

		return value.startsWith(httpPrefix) || value.startsWith(httpsPrefix);
	}
}
