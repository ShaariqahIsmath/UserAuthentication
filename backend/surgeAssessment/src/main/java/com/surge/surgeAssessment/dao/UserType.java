package com.surge.surgeAssessment.dao;

public enum UserType {

	ADMIN("ADMIN"),
	STUDENT("STUDENT");

	private String userValue;

	UserType(String userValue) {
		this.userValue = userValue;
	}

	public String getUserValue() {
		return userValue;
	}
	
	
	
	

}
