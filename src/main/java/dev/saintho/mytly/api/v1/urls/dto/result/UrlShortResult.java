package dev.saintho.mytly.api.v1.urls.dto.result;

import dev.saintho.mytly.domain.entity.Url;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlShortResult {
	private String shortened;
	private String original;

	public static UrlShortResult from(Url url) {
		UrlShortResult result = new UrlShortResult();

		result.shortened = url.getShortened();
		result.original = url.getOriginal();

		return result;
	}
}
