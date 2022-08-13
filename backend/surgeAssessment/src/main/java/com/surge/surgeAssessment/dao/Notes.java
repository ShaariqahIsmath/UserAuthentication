package com.surge.surgeAssessment.dao;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document( collection ="notes")
public class Notes {

	@JsonSerialize(using= ToStringSerializer.class)
	@Id
	private ObjectId id;
	private String heading;
	private String description;


	

}
