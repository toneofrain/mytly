package dev.saintho.mytly.dto.command;

import dev.saintho.mytly.dto.request.ExpirationPeriod;
import dev.saintho.mytly.dto.request.UrlPostRequest;
import lombok.Getter;

@Getter
public class UrlCreateCommand {
	private String original;
	private Boolean isExpirable;
	private ExpirationPeriod expirationPeriod;

	public static UrlCreateCommand from(UrlPostRequest request) {
		UrlCreateCommand command = new UrlCreateCommand();

		command.original = request.getOriginal();
		command.isExpirable = request.getIsExpirable();
		command.expirationPeriod = request.getExpirationPeriod();

		return command;
	}
}
