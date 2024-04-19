package com.aula.demo.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class CustomHandlerException {

	@ExceptionHandler({EntityNotFoundException.class})
	public ResponseEntity<?> notFound(){
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<?> error400(MethodArgumentNotValidException error){
		return ResponseEntity.badRequest().body(error.getFieldErrors().stream().map(e -> new TratarFieldError(e)));
	}
	
	public record TratarFieldError(String fieldName, String messageError) {
		public TratarFieldError(FieldError error){
			this(error.getField(), error.getDefaultMessage());
		}
	}
}
