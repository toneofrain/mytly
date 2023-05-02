package dev.saintho.mytly.web.v1.dto.query;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlRedirectQuery {
	private String shortened;
	private Referer referer;

	public static UrlRedirectQuery of(String shortened, Referer referer) {
		UrlRedirectQuery query = new UrlRedirectQuery();

		query.shortened = shortened;
		query.referer = referer;

		return query;
	}
}
