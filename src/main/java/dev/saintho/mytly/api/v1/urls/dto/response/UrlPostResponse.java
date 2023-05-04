package dev.saintho.mytly.api.v1.urls.dto.response;

import dev.saintho.mytly.api.v1.urls.dto.result.UrlShortResult;
import lombok.Getter;

@Getter
public class UrlPostResponse {
	private String originalUrl;
	private String shortenedUrl;

	public static UrlPostResponse of(UrlShortResult result, String path) {
		UrlPostResponse response = new UrlPostResponse();

		response.originalUrl = result.getOriginal();
		response.shortenedUrl = path + result.getShortened();

		return response;
	}
}
