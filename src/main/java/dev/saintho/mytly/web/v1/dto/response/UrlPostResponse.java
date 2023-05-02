package dev.saintho.mytly.web.v1.dto.response;

import dev.saintho.mytly.domain.entity.Url;
import lombok.Getter;

@Getter
public class UrlPostResponse {
	private String original;
	private String shortened;

	public static UrlPostResponse from(Url url) {
		UrlPostResponse response = new UrlPostResponse();

		response.original = url.getOriginal();
		response.shortened = url.getShortened();

		return response;
	}
}
