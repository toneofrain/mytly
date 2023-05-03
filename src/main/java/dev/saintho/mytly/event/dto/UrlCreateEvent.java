package dev.saintho.mytly.event.dto;

import dev.saintho.mytly.domain.entity.Url;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UrlCreateEvent {
	private final Url url;
}
