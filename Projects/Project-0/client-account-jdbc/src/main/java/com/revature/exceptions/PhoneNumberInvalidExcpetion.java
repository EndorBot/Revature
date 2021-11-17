package com.revature.exceptions;

public class PhoneNumberInvalidExcpetion extends Exception {
	
	public PhoneNumberInvalidExcpetion() {
		super();
	}

	public PhoneNumberInvalidExcpetion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public PhoneNumberInvalidExcpetion(String message, Throwable cause) {
		super(message,cause);
	}
	
	public PhoneNumberInvalidExcpetion(String message) {
		super(message);
	}
	
	public PhoneNumberInvalidExcpetion(Throwable cause) {
		super(cause);
	}

}
