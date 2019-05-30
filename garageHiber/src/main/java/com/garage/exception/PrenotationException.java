package com.garage.exception;

public class PrenotationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6267677719872986089L;

	public PrenotationException() {
		super();
	}

	public PrenotationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PrenotationException(String message, Throwable cause) {
		super(message, cause);
	}

	public PrenotationException(String message) {
		super(message);
	}

	public PrenotationException(Throwable cause) {
		super(cause);
		System.out.println(cause.getMessage());
	}

}
