package dev.saintho.mytly.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleVMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		StringBuilder sb = new StringBuilder();
		sb.append("Validation Failed")
			.append("\n")
			.append("-".repeat(18))
			.append("\n");

		BindingResult result = exception.getBindingResult();

		for (FieldError fieldError : result.getFieldErrors()) {
			sb.append(String.format("[%s](%s)]: ", fieldError.getField(), fieldError.getRejectedValue()))
				.append(fieldError.getDefaultMessage())
				.append("\n");
		}

		return ResponseEntity
			.badRequest()
			.body(sb.toString());
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException exception) {

		return ResponseEntity
			.internalServerError()
			.body(exception.getMessage());
	}
}
