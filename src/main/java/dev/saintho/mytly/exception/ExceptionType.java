package dev.saintho.mytly.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {
	URL_NOT_FOUND(NOT_FOUND, "URL_NOT_FOUND"),
	URL_NOT_AVAILABLE(CONFLICT, "URL_NOT_AVAILABLE"),

	URL_STATISTIC_NOT_AVAILABLE(CONFLICT, "URL_STATISTIC_NOT_AVAILALBLE");

	private final HttpStatus status;
	private final String message;
}
