package dev.patricksilva.model.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.patricksilva.model.entities.User;

public interface UserRepository extends MongoRepository<User, String>{
	Boolean existsByEmail(String email);
	Optional<User> findByEmail(String email);
}