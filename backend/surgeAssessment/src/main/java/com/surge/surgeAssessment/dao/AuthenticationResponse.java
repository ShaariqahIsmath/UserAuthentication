package com.surge.surgeAssessment.dao;

public class AuthenticationResponse {
	
	private String response;
	private boolean success;
	private int statusCode;
	private Object data;
	
	

	public AuthenticationResponse() {
		super();
	}


	public AuthenticationResponse(String response) {
		this.response = response;
	}
	

	public AuthenticationResponse(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.response = message;
	}



	public AuthenticationResponse(String response, boolean success, int statusCode, String message, Object data) {
		super();
		this.response = response;
		this.success = success;
		this.statusCode = statusCode;
		this.data = data;
	}


	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	

}
