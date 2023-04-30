package dev.saintho.mytly.exception;

import org.springframework.http.HttpStatus;

public class MytlyException extends RuntimeException {
	private final ExceptionType type;

	public MytlyException(ExceptionType type) {
		super(type.getMessage());
		this.type = type;
	}

	public MytlyException(ExceptionType type, String message) {
		super(message);
		this.type = type;
	}

	public HttpStatus getStatus() {
		return type.getStatus();
	}
}
