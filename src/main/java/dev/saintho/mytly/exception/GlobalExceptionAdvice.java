package dev.saintho.mytly.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {
	@ExceptionHandler(MytlyException.class)
	public ResponseEntity<String> handleMytlyException(MytlyException exception) {

		return ResponseEntity
			.status(exception.getStatus())
			.body(exception.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException exception) {

		return ResponseEntity
			.internalServerError()
			.body(exception.getMessage());
	}
}
