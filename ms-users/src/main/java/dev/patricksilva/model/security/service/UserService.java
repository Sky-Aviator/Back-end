package dev.patricksilva.model.security.service;

import java.util.Collections;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.patricksilva.model.entities.User;
import dev.patricksilva.model.repository.UserRepository;
import dev.patricksilva.model.security.encoder.Encoder;

@Service
public class UserService implements UserDetailsService {

	private UserRepository userRepository;
	private Encoder encoder;

	public UserService(UserRepository userRepository, @Lazy Encoder Encoder) {
		this.userRepository = userRepository;
		this.encoder = Encoder;

	}

	public void registerUser(User user) {
		User newUser = new User();
		newUser.setId(user.getId());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(newUser);

	}

	public User findUserById(String id) {
		return userRepository.findById(id).orElseThrow(() -> new NullPointerException("user not found"));
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new UsernameNotFoundException(email);

		return new org.springframework.security.core.userdetails.User(email, null, Collections.emptyList());

	}
}