package com.surge.surgeAssessment.dto;

import lombok.Data;

@Data
public class UserDto {

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String dateOfBirth;
	private String mobileNumber;
	private boolean status; // if new its true
	private String accountType;
	


}
