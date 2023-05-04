package dev.saintho.mytly.api.v1.urls.dto.command;

import java.time.LocalDateTime;

import dev.saintho.mytly.api.v1.urls.dto.request.UrlPostRequest;
import lombok.Getter;

@Getter
public class UrlShortCommand {
	private String original;
	private Boolean isExpirable;
	private LocalDateTime requestedAt;
	private LocalDateTime expireAt;

	public static UrlShortCommand of(UrlPostRequest request, LocalDateTime requestedAt) {
		UrlShortCommand command = new UrlShortCommand();

		command.original = request.getOriginal();
		command.isExpirable = request.getIsExpirable();
		command.requestedAt = requestedAt;
		command.expireAt = request.getExpirationPeriod().getExpireAt(requestedAt);

		return command;
	}
}
