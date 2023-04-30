package dev.saintho.mytly.dto.command;

import lombok.Getter;

@Getter
public class UrlDeleteCommand {
	private String shortened;

	public static UrlDeleteCommand from(String shortened) {
		UrlDeleteCommand command = new UrlDeleteCommand();

		command.shortened = shortened;

		return command;
	}
}
