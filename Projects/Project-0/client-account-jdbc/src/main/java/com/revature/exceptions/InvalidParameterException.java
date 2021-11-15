package com.revature.exceptions;

public class InvalidParameterException extends Exception {
	
	public InvalidParameterException() {
		super();
	}
	
	public InvalidParameterException(String message, Throwable cause, boolean enableSuppresion, boolean writableStackTrace) {
		super(message, cause, enableSuppresion, writableStackTrace);
	}

	public InvalidParameterException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InvalidParameterException(String message) {
		super(message);
	}
	
	public InvalidParameterException(Throwable cause) {
		super(cause);
	}

}
