package dev.patricksilva.model.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.patricksilva.model.dtos.UserDto;
import dev.patricksilva.model.repository.UserRepository;

/**
 * Service class for security-related operations.
 * 
 * @author Patrick L. da Silva
 */
@Service
public class SecurityService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Loads a user by the given email.
	 *
	 * @param email The email of the user to be loaded.
	 * @return The UserDetails of the loaded user.
	 * @throws UsernameNotFoundException If the user with the specified email is not
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDto userDto = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));

		return UserDetailsImpl.build(userDto);
	}
}