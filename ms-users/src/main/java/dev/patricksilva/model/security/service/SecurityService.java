package dev.patricksilva.model.security.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.patricksilva.model.entities.User;
import dev.patricksilva.model.repository.UserRepository;

/**
 * Service class for security-related operations.
 * 
 * @author Patrick L. da Silva
 */
@Service
public class SecurityService implements UserDetailsService {

	private UserRepository userRepository;

	/**
	 * Constructs a SecurityService with the specified UserRepository.
	 *
	 * @param userRepository The UserRepository to be used.
	 */
	public SecurityService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Loads a user by the given email.
	 *
	 * @param email The email of the user to be loaded.
	 * @return The UserDetails of the loaded user.
	 * @throws UsernameNotFoundException If the user with the specified email is not
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}

		return new org.springframework.security.core.userdetails.User(email, null, Collections.emptyList());
	}
}
