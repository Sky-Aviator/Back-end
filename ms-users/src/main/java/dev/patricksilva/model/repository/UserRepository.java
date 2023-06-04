package dev.patricksilva.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import dev.patricksilva.model.entities.User;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
}