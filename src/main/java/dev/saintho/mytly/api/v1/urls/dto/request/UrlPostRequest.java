package dev.saintho.mytly.api.v1.urls.dto.request;

import javax.validation.constraints.NotNull;

import dev.saintho.mytly.validation.annotation.OriginalUrlConstraint;
import lombok.Getter;

@Getter
public class UrlPostRequest {
	@OriginalUrlConstraint
	private String original;
	@NotNull(message = "IsExpirable must not be null.")
	private Boolean isExpirable;
	@NotNull(message = "ExpirationPeriods must be one of [ONE_MONTH, THREE_MONTHS, SIX_MONTHS, TWELVE_MONTHS]")
	private ExpirationPeriod expirationPeriod;
}
