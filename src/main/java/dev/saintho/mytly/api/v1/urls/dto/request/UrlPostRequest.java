package dev.saintho.mytly.api.v1.urls.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UrlPostRequest {
	private String original;
	private Boolean isExpirable;
	private ExpirationPeriod expirationPeriod;
}
