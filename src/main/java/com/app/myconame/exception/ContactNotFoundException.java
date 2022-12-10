package com.app.myconame.exception;

public class ContactNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContactNotFoundException() {
		super();
	}
	
	public ContactNotFoundException(String message) {
		super(message);
	}
}
