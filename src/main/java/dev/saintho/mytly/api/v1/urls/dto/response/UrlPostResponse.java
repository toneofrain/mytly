package dev.saintho.mytly.api.v1.urls.dto.response;

import dev.saintho.mytly.api.v1.urls.dto.result.UrlShortResult;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class UrlPostResponse {
	@Schema(example = "https://thoughtful-arch-8c2.notion.site/_URL-c7f57136e2744ff8872eaf973069bdfe")
	private String originalUrl;
	@Schema(example = "https://saintho.dev/1Re0AZ")
	private String shortenedUrl;

	public static UrlPostResponse of(UrlShortResult result, String path) {
		UrlPostResponse response = new UrlPostResponse();

		response.originalUrl = result.getOriginal();
		response.shortenedUrl = path + result.getShortened();

		return response;
	}
}
