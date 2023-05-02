package dev.saintho.mytly.web.v1.dto.command;

import dev.saintho.mytly.web.v1.dto.request.ExpirationPeriod;
import dev.saintho.mytly.web.v1.dto.request.UrlPostRequest;
import lombok.Getter;

@Getter
public class UrlShortCommand {
	private String original;
	private Boolean isExpirable;
	private ExpirationPeriod expirationPeriod;

	public static UrlShortCommand from(UrlPostRequest request) {
		UrlShortCommand command = new UrlShortCommand();

		command.original = request.getOriginal();
		command.isExpirable = request.getIsExpirable();
		command.expirationPeriod = request.getExpirationPeriod();

		return command;
	}
}
