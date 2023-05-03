package dev.saintho.mytly.event.dto;

import java.time.LocalDate;

import dev.saintho.mytly.api.v1.urls.dto.query.Referer;
import dev.saintho.mytly.domain.entity.Url;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlRedirectEvent {
	private Url url;
	private Referer referer;
	private LocalDate redirectDate;

	public static UrlRedirectEvent of(Url url, Referer referer, LocalDate redirectDate) {
		UrlRedirectEvent event = new UrlRedirectEvent();

		event.url = url;
		event.referer = referer;
		event.redirectDate = redirectDate;

		return event;
	}
}
