package dev.patricksilva.model.security.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import dev.patricksilva.model.dtos.UserDto;
import dev.patricksilva.model.repository.UserRepository;

class SecurityServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private SecurityService securityService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testLoadUserByUsername_WhenUserExists() {
		String email = "test@example.com";
		UserDto userDto = new UserDto();
		userDto.setEmail(email);

		when(userRepository.findByEmail(email)).thenReturn(Optional.of(userDto));

		UserDetails userDetails = securityService.loadUserByUsername(email);

		assertNotNull(userDetails);
		assertEquals(email, userDetails.getUsername());
		// Add assertions for other user details if needed
	}

	@Test
	void testLoadUserByUsername_WhenUserDoesNotExist() {
		String email = "nonexistent@example.com";

		when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

		assertThrows(UsernameNotFoundException.class, () -> securityService.loadUserByUsername(email));
	}

}