package dev.saintho.mytly.api.v1.urls.dto.query;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlRedirectQuery {
	private String shortened;
	private Referer referer;
	private LocalDate redirectDate;

	public static UrlRedirectQuery of(String shortened, Referer referer, LocalDate redirectDate) {
		UrlRedirectQuery query = new UrlRedirectQuery();

		query.shortened = shortened;
		query.referer = referer;
		query.redirectDate = redirectDate;

		return query;
	}
}
