package com.surge.surgeAssessment.exception;

public enum ErrorList {

	NO_RESULTS_FOUND(404, "No Results Found");
	
	private int code;
	private String description;

	private ErrorList(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
