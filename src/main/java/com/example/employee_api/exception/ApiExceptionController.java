package com.example.employee_api.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionController {

	@ExceptionHandler(CustomIdNotFoundException.class)
	public ResponseEntity<?> handleCustomIdNotFoundException(CustomIdNotFoundException idNotFoundException,
			WebRequest request) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(
				new ApiException(idNotFoundException.getMessage(), httpStatus, LocalDate.now(),
						request.getDescription(false)),
				httpStatus);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception exception,
			WebRequest request) {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(
				new ApiException(exception.getMessage(), httpStatus, LocalDate.now(),
						request.getDescription(false)),
				httpStatus);
	}

}
