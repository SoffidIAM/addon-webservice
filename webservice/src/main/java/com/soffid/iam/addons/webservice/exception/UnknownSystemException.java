package com.soffid.iam.addons.webservice.exception;

@SuppressWarnings("serial")
public class UnknownSystemException extends Exception {

	public UnknownSystemException() {
	}

	public UnknownSystemException(String message) {
		super(message);
	}

	public UnknownSystemException(Throwable cause) {
		super(cause);
	}

	public UnknownSystemException(String message, Throwable cause) {
		super(message, cause);
	}
}
