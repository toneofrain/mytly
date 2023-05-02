package dev.saintho.mytly.dto.response;

import java.net.URI;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlRedirectResponse {
	private String redirectUrl;

	public static UrlRedirectResponse from(URI redirectUrl) {
		UrlRedirectResponse response = new UrlRedirectResponse();

		response.redirectUrl = redirectUrl.toString();

		return response;
	}
}
