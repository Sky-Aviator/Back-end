package dev.patricksilva.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.patricksilva.model.entities.User;

public interface UserRepository extends MongoRepository<User, String>{
	boolean existsByEmail(String email);
}