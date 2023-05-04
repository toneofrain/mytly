package dev.saintho.mytly.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import dev.saintho.mytly.validation.validator.OriginalUrlValidator;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OriginalUrlValidator.class)
public @interface ValidOriginalUrl {
	String message() default "Original Url must not be empty, be a stirng having http(s) prefix within 255 length.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
