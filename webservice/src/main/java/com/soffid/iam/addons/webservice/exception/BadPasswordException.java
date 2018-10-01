package com.soffid.iam.addons.webservice.exception;

@SuppressWarnings("serial")
public class BadPasswordException extends Exception {

	public BadPasswordException() {
	}

	public BadPasswordException(String message) {
		super(message);
	}

	public BadPasswordException(Throwable cause) {
		super(cause);
	}

	public BadPasswordException(String message, Throwable cause) {
		super(message, cause);
	}
}
