package com.hospitalsystem.globalException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandalGlobalException {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {

		Map<String, String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);
		});

		return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> dataIntegrityViolationException(DataIntegrityViolationException ex) {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("message", "this username / email / mobile already exists");
		return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("messagge", "Enter Field Value");
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> illegalArgumentException(IllegalArgumentException ex) {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("message", "Enter Valid ValueI");
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TransactionSystemException.class)
	public ResponseEntity<?> transactionSystemException(TransactionSystemException ex){
		Map<String,String> map=new LinkedHashMap<>();
		map.put("message", "Enter Valid Field Details");
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> noSuchElementException(NoSuchElementException ex){
		Map<String,String> map=new LinkedHashMap<>();
		map.put("message", "Data Not Found");
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}
}
