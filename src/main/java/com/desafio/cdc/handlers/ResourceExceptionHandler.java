package com.desafio.cdc.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
		ValidationError error = new ValidationError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), "Parâmetros inválidos");

		exception.getBindingResult().getFieldErrors()
				.forEach(item -> error.addError(item.getField(), item.getDefaultMessage()));

		return ResponseEntity.status(error.getStatus()).body(error);
	}

}
