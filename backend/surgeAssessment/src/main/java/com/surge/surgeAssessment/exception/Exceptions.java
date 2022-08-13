package com.surge.surgeAssessment.exception;

public class Exceptions extends Exception {

	private static final long serialVersionUID = 1L;
	private final ErrorList errorCode;
	private final String message;

	public Exceptions(ErrorList errorCode) {
		this.errorCode = errorCode;
		this.message = errorCode.getDescription();
	}

	public Exceptions(ErrorList errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public ErrorList getErrorCode() {
		return errorCode;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
