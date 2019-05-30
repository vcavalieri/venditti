package com.garage.exception;

public class VehicleException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7163084798319615933L;

	public VehicleException() {
		super();
	}

	public VehicleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public VehicleException(String message, Throwable cause) {
		super(message, cause);
	}

	public VehicleException(String message) {
		super(message);
	}

	public VehicleException(Throwable cause) {
		super(cause);
		System.out.println(cause.getMessage());
	}

}
