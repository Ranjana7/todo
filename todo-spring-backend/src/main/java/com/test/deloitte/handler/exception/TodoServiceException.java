package com.test.deloitte.handler.exception;

public class TodoServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TodoServiceException(String message) {
		super(message);
	}

}
