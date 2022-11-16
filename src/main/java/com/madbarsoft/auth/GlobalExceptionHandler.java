package com.madbarsoft.auth;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.madbarsoft.utility.MyCustomException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MyCustomException.class)
	public ResponseEntity<Object> MyCustomException() {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "No header found");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

}
