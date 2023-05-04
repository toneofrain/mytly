package dev.saintho.mytly.api.v1.urls.dto.query;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlRedirectQuery {
	private String shortened;
	private Referer referer;
	private LocalDateTime redirectDateTime;

	public static UrlRedirectQuery of(String shortened, Referer referer, LocalDateTime redirectDateTime) {
		UrlRedirectQuery query = new UrlRedirectQuery();

		query.shortened = shortened;
		query.referer = referer;
		query.redirectDateTime = redirectDateTime;

		return query;
	}
}
