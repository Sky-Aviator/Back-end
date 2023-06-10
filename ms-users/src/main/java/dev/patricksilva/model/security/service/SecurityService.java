package dev.patricksilva.model.security.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.patricksilva.model.entities.User;
import dev.patricksilva.model.repository.UserRepository;

@Service
public class SecurityService implements UserDetailsService {

	private UserRepository userRepository;

	public SecurityService(UserRepository userRepository) {
		this.userRepository = userRepository;

	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new UsernameNotFoundException(email);

		return new org.springframework.security.core.userdetails.User(email, null, Collections.emptyList());

	}
}