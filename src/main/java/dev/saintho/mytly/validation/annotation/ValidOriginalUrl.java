package dev.saintho.mytly.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import dev.saintho.mytly.validation.validator.OriginalUrlValidator;

/***
 * 요청받은 원본 URL이 유효한지 검증한다.
 * 1. null이 아니며 길이가 0이어서는 안 된다.
 * 2. 길이가 255를 초과해서는 안 된다.
 * 3. http 접두사("http://") 혹은 https 접두사("https://")를 가져야 한다.
 * 추가 - URL 형태(protocol-domain-path)인지 검증
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OriginalUrlValidator.class)
public @interface ValidOriginalUrl {
	String message() default "Original Url must not be empty, be a stirng having http(s) prefix within 255 length.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
