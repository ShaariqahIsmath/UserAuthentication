package com.surge.surgeAssessment.repository;

import com.surge.surgeAssessment.dao.Notes;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotesRepository extends MongoRepository<Notes, ObjectId>{

}
