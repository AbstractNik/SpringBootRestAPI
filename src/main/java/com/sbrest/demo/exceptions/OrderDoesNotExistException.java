package com.sbrest.demo.exceptions;

public class OrderDoesNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public OrderDoesNotExistException(String message) {
		super(message);
	}
	
	

}
