package dev.patricksilva.model.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.patricksilva.model.entities.Role;
import dev.patricksilva.model.enums.ERole;

public interface RoleRepository extends MongoRepository<Role, String> {
	Optional<Role> findByName(ERole name);
}