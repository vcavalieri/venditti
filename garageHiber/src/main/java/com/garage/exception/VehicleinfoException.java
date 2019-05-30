package com.garage.exception;

public class VehicleinfoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8398497122346888072L;

	public VehicleinfoException() {
		super();
	}

	public VehicleinfoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public VehicleinfoException(String message, Throwable cause) {
		super(message, cause);
	}

	public VehicleinfoException(String message) {
		super(message);
	}

	public VehicleinfoException(Throwable cause) {
		super(cause);
		System.out.println(cause.getMessage());
	}

}
