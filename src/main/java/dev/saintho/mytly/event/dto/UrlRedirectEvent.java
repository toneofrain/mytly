package dev.saintho.mytly.event.dto;

import java.time.LocalDateTime;

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
	private LocalDateTime redirectDateTime;

	public static UrlRedirectEvent of(Url url, Referer referer, LocalDateTime redirectDateTime) {
		UrlRedirectEvent event = new UrlRedirectEvent();

		event.url = url;
		event.referer = referer;
		event.redirectDateTime = redirectDateTime;

		return event;
	}
}
