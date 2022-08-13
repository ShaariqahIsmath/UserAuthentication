package com.surge.surgeAssessment.repository;

import com.surge.surgeAssessment.dao.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsersRepository extends MongoRepository<User, ObjectId>{
	Optional<User> findByEmail(String email);
}
