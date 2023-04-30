package dev.saintho.mytly.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {
	;
	private final HttpStatus status;
	private String message;
}
