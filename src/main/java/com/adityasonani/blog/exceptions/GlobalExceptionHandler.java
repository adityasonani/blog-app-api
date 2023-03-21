package com.adityasonani.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.adityasonani.blog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException e) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(e.getMessage(), false), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
		Map<String, String> response = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((err) -> {
			String field = ((FieldError) err).getField();
			response.put(field, err.getDefaultMessage());
		});
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponse> illegalArgumentExceptionHandler(IllegalArgumentException e) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(e.getMessage(), false), HttpStatus.NOT_FOUND);
	}
}
