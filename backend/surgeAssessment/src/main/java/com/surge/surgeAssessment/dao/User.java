package com.surge.surgeAssessment.dao;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "users")
public class User {

	@JsonSerialize(using= ToStringSerializer.class)
	@Id
	private ObjectId id;
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String dateOfBirth;
	private String mobileNumber;
	private boolean status; // if new its true
	private String accountType;
	


}
