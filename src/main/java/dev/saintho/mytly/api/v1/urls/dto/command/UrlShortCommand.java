package dev.saintho.mytly.api.v1.urls.dto.command;

import java.time.LocalDateTime;

import dev.saintho.mytly.api.v1.urls.dto.request.ExpirationPeriod;
import dev.saintho.mytly.api.v1.urls.dto.request.UrlPostRequest;
import lombok.Getter;

@Getter
public class UrlShortCommand {
	private String original;
	private Boolean isExpirable;
	private ExpirationPeriod expirationPeriod;
	private LocalDateTime requestedAt;

	public static UrlShortCommand of(UrlPostRequest request, LocalDateTime requestedAt) {
		UrlShortCommand command = new UrlShortCommand();

		command.original = request.getOriginal();
		command.isExpirable = request.getIsExpirable();
		command.expirationPeriod = request.getExpirationPeriod();
		command.requestedAt = requestedAt;

		return command;
	}

	public LocalDateTime getExpireAt() {
		return expirationPeriod.getExpireAt(requestedAt);
	}
}
