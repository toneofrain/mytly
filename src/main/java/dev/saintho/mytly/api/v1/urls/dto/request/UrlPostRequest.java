package dev.saintho.mytly.api.v1.urls.dto.request;

import dev.saintho.mytly.validation.annotation.OriginalUrlConstraint;
import lombok.Getter;

@Getter
public class UrlPostRequest {
	@OriginalUrlConstraint
	private String original;
	private Boolean isExpirable;
	private ExpirationPeriod expirationPeriod;
}
