package dev.patricksilva.model.security.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UserDetailsImplTest {

	@Test
	void testEquals() {
		UserDetailsImpl user1 = new UserDetailsImpl("1", "John", "Doe", "johndoe@example.com", "password", null,
				"1234567890123456", "12345678901", "1234567890", "12", "2023");
		UserDetailsImpl user2 = new UserDetailsImpl("1", "Jane", "Smith", "janesmith@example.com", "password", null,
				"1234567890123456", "12345678901", "1234567890", "12", "2023");
		UserDetailsImpl user3 = new UserDetailsImpl("2", "John", "Doe", "johndoe@example.com", "password", null,
				"1234567890123456", "12345678901", "1234567890", "12", "2023");

		// Mesmo ID, deveriam ser considerados iguais
		assertTrue(user1.equals(user2));

		// IDs diferentes, não deveriam ser considerados iguais
		assertFalse(user1.equals(user3));
	}

}