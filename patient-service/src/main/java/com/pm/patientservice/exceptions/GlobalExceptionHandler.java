package com.pm.patientservice.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler
	public ResponseEntity<Map<String, String>> handlerValidationException(
			MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<String, String>();
		
		ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));			
		
		return ResponseEntity.badRequest().body(errors);
	}
	
	@ExceptionHandler
	public ResponseEntity<Map<String, String>> handlerEmailAlreadyExistsException(EmailAlreadyExistsException ex){
		Map<String, String> errors = new HashMap<String, String>();
		
		log.warn("Email address already exists {}", ex.getMessage());
		
		errors.put("message", "Email already exists");
		
		return ResponseEntity.badRequest().body(errors);
	}
}
