package com.garage.exception;

public class UserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7620486575068089302L;

	public UserException() {
		super();
	}

	public UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserException(String message) {
		super(message);
	}

	public UserException(Throwable cause) {
		super(cause);
		System.out.println(cause.getMessage());
	}
}
