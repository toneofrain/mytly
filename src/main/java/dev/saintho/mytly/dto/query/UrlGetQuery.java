package dev.saintho.mytly.dto.query;

import lombok.Getter;

@Getter
public class UrlGetQuery {
	private String shortened;

	public static UrlGetQuery from(String shortened) {
		UrlGetQuery query = new UrlGetQuery();

		query.shortened = shortened;

		return query;
	}
}
