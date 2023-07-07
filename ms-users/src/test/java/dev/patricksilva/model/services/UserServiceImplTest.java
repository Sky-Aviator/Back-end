package dev.patricksilva.model.services;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dev.patricksilva.model.dtos.UserDto;
import dev.patricksilva.model.entities.Role;
import dev.patricksilva.model.entities.User;
import dev.patricksilva.model.enums.ERole;
import dev.patricksilva.model.exceptions.ResourceNotFoundException;
import dev.patricksilva.model.exceptions.RoleNotFoundException;
import dev.patricksilva.model.repository.RoleRepository;
import dev.patricksilva.model.repository.UserRepository;
import dev.patricksilva.model.security.encoders.Encoder;
import dev.patricksilva.model.security.jwt.payload.request.UserRequest;

class UserServiceImplTest {

	@Mock
	private Encoder encoder;

	@Mock
	private UserRepository userRepository;

	@Mock
	private RoleRepository roleRepository;

	@InjectMocks
	private UserServiceImpl userService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		when(userRepository.findAll()).thenReturn(users);

		List<UserDto> result = userService.findAll();

		assertEquals(2, result.size());
	}

	@Test
	void testFindById() {
		String id = "1";
		User user = new User();
		when(userRepository.findById(id)).thenReturn(Optional.of(user));

		UserDto result = userService.findById(id);

		assertNotNull(result);
	}

	@Test
	void testAddUser() {
		UserDto userDto = new UserDto();
		UserRequest userRequest = new UserRequest();

		when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(false);
		when(encoder.encode(anyString())).thenReturn("encodedValue");
		when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(new Role()));

		ResponseEntity<?> result = userService.addUser(userDto, userRequest);

		assertNotNull(result);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	void testDeleteUser() {
		String id = "1";
		when(userRepository.existsById(id)).thenReturn(true);

		assertDoesNotThrow(() -> userService.deleteUser(id));
		verify(userRepository, times(1)).deleteById(id);
	}

	@Test
	void testUpdateUser() {
		String id = "1";
		UserDto userDto = new UserDto();
		UserRequest userRequest = new UserRequest();
		when(userRepository.existsById(id)).thenReturn(true);
		when(encoder.encode(anyString())).thenReturn("encodedValue");
		when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(new Role()));

		UserDto result = userService.updateUser(id, userDto, userRequest);

		assertNotNull(result);
		assertEquals(id, result.getId());
	}

	@Test
	void testPartialUpdate() {
		String id = "1";
		UserDto userDto = new UserDto();
		userDto.setEmail("test@example.com");
		User existingUser = new User();
		existingUser.setEmail("old@example.com");
		when(userRepository.existsById(id)).thenReturn(true);
		when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));
		when(userRepository.save(existingUser)).thenReturn(existingUser);

		UserDto result = userService.partialUpdate(id, userDto);

		assertNotNull(result);
		assertEquals(userDto.getEmail(), result.getEmail());
	}

	@Test
    void testFindAll_WhenNoUsersExist() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        List<UserDto> result = userService.findAll();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

	@Test
	void testFindById_WhenUserDoesNotExist() {
		String id = "1";
		when(userRepository.findById(id)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> userService.findById(id));
	}

	@Test
	void testAddUser_WhenEmailAlreadyExists() {
		UserDto userDto = new UserDto();
		userDto.setEmail("existing@example.com");
		UserRequest userRequest = new UserRequest();

		when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(true);

		assertThrows(IllegalArgumentException.class, () -> userService.addUser(userDto, userRequest));
	}

	@Test
	void testDeleteUser_WhenUserDoesNotExist() {
		String id = "1";
		when(userRepository.existsById(id)).thenReturn(false);

		assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(id));
		verify(userRepository, never()).deleteById(id);
	}

	@Test
	void testMaskCard_WhenCardNumberIsLessThan12Digits() {
		String cardNumber = "123456";
		assertEquals(cardNumber, userService.maskCard(cardNumber));
	}

	@Test
	void testMaskCPF_WhenCPFIsLessThan11Digits() {
		String cpf = "123456";
		assertEquals(cpf, userService.maskCPF(cpf));
	}

	@Test
	void testMaskCv_WhenCVIsLessThan4Digits() {
		String cv = "123";
		assertEquals(cv, userService.maskCv(cv));
	}

	@Test
	void testUpdateUser_WhenUserDoesNotExist() {
		String id = "1";
		UserDto userDto = new UserDto();
		UserRequest userRequest = new UserRequest();
		when(userRepository.existsById(id)).thenReturn(false);

		assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(id, userDto, userRequest));
		verify(userRepository, never()).save(any());
	}

	@Test
	void testPartialUpdate_WhenUserDoesNotExist() {
		String id = "1";
		UserDto userDto = new UserDto();
		when(userRepository.existsById(id)).thenReturn(false);

		assertThrows(ResourceNotFoundException.class, () -> userService.partialUpdate(id, userDto));
		verify(userRepository, never()).save(any());
	}

	@Test
	void testMaskCard() {
		String cardNumber = "1234567890123456";
		String maskedCardNumber = "****-****-****-3456";
		assertEquals(maskedCardNumber, userService.maskCard(cardNumber));
	}

	@Test
	void testMaskCard_WhenCardNumberIsNull() {
		String cardNumber = null;
		assertNull(userService.maskCard(cardNumber));
	}

	@Test
	void testGetRolesFromRequest_WhenRolesAreNotEmpty() {
		UserRequest userRequest = new UserRequest();
		Set<String> roleNames = new HashSet<>();
		roleNames.add("ROLE_USER");
		roleNames.add("ROLE_ADMIN");
		roleNames.add("ROLE_MODERATOR");
		userRequest.setRoles(roleNames);

		Role role1 = new Role(ERole.ROLE_USER);
		Role role2 = new Role(ERole.ROLE_ADMIN);
		Role role3 = new Role(ERole.ROLE_MODERATOR);

		when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(role1));
		when(roleRepository.findByName(ERole.ROLE_ADMIN)).thenReturn(Optional.of(role2));
		when(roleRepository.findByName(ERole.ROLE_MODERATOR)).thenReturn(Optional.of(role3));

		Set<Role> roles = userService.getRolesFromRequest(userRequest);

		assertNotNull(roles);
		assertTrue(roles.contains(role1));
		assertEquals(1, roles.size());
	}

	@Test
	void testAddUser_WhenRoleNotFound() {
		UserDto userDto = new UserDto();
		userDto.setEmail("patricksilva.me@gmail.com");
		UserRequest userRequest = new UserRequest();
		userRequest.setRoles(Set.of("admin")); // Role not found in repository

		when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(false);
		when(roleRepository.findByName(ERole.ROLE_ADMIN)).thenReturn(Optional.empty()); // Role not found

		assertThrows(RoleNotFoundException.class, () -> userService.addUser(userDto, userRequest));
		verify(userRepository, never()).save(any());
	}

	@Test
	void testUpdateUser_WhenRoleNotFound() {
		String id = "1";
		UserDto userDto = new UserDto();
		UserRequest userRequest = new UserRequest();
		userRequest.setRoles(Set.of("admin")); // Role not found in repository

		when(userRepository.existsById(id)).thenReturn(true);
		when(roleRepository.findByName(ERole.ROLE_ADMIN)).thenReturn(Optional.empty()); // Role not found

		assertThrows(RoleNotFoundException.class, () -> userService.updateUser(id, userDto, userRequest));
		verify(userRepository, never()).save(any());
	}

	@Test
	void testGetRolesFromRequest_WhenRoleNotFound() {
		UserRequest userRequest = new UserRequest();
		Set<String> roleNames = new HashSet<>();
		roleNames.add("admin"); // Role not found in repository
		userRequest.setRoles(roleNames);

		when(roleRepository.findByName(ERole.ROLE_ADMIN)).thenReturn(Optional.empty()); // Role not found

		assertThrows(RoleNotFoundException.class, () -> userService.getRolesFromRequest(userRequest));
	}

	@Test
	void testGetRolesFromRequest_WhenRolesIsNull() {
		UserRequest userRequest = new UserRequest();
		userRequest.setRoles(null);

		Role userRole = new Role(ERole.ROLE_USER);
		when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(userRole));

		Set<Role> roles = userService.getRolesFromRequest(userRequest);

		assertNotNull(roles);
		assertTrue(roles.contains(userRole));
		assertEquals(1, roles.size());
	}

	@Test
	void testFindByEmail_WhenEmailDoesNotExist() {
		String email = "nonexistent@example.com";

		when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> userService.findByEmail(email));
	}

}