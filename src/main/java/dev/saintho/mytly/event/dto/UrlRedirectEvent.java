package dev.saintho.mytly.event.dto;

import dev.saintho.mytly.api.v1.urls.dto.query.Referer;
import dev.saintho.mytly.domain.entity.Url;
import lombok.Getter;

@Getter
public class UrlRedirectEvent {
	private Url url;
	private Referer referer;
}
