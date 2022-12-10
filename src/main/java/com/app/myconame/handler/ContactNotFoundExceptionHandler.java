package com.app.myconame.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.myconame.exception.ContactNotFoundException;

@RestControllerAdvice
public class ContactNotFoundExceptionHandler {

	@ExceptionHandler(ContactNotFoundException.class)
	public ResponseEntity<String> showCustomError(ContactNotFoundException cnfe){
		return new ResponseEntity<String>(cnfe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
