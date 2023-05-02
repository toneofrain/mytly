package dev.saintho.mytly.api.v1.urls.dto.command;

import lombok.Getter;

@Getter
public class UrlDeleteByShortenedCommand {
	private String shortened;

	public static UrlDeleteByShortenedCommand from(String shortened) {
		UrlDeleteByShortenedCommand command = new UrlDeleteByShortenedCommand();

		command.shortened = shortened;

		return command;
	}
}
