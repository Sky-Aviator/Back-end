package dev.patricksilva.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.patricksilva.model.dtos.UserDto;
import dev.patricksilva.model.services.UserServiceImpl;
import dev.patricksilva.view.UserRequest;
import dev.patricksilva.view.UserResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	// Dependency Injection
	@Autowired
	private UserServiceImpl userServiceImpl;

	/**
	 * Method responsible for retrieving all users.
	 * 
	 * @return ResponseEntity<List<UserResponse>> - An HTTP response containing the
	 *         list of users found.
	 */
	@GetMapping
	public ResponseEntity<List<UserResponse>> findAllUsers() {
		ModelMapper mapper = new ModelMapper();
		List<UserDto> users = userServiceImpl.findAll();
		List<UserResponse> response = users.stream().map(u -> mapper.map(u, UserResponse.class)).toList();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Method responsible for finding a user by their ID ("/{id}").
	 * 
	 * @param id - The ID of the user to be retrieved.
	 * @return ResponseEntity<UserResponse> - An HTTP response containing the user
	 *         found, or a "Not Found" status if no user is found.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> findUserById(@Valid @PathVariable String id) {
		UserDto userDto = userServiceImpl.findById(id);
		if (userDto != null) {
			ModelMapper mapper = new ModelMapper();
			UserResponse user = mapper.map(userDto, UserResponse.class);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Method responsible for registering a new user in the database ("/register").
	 * 
	 * @param userRequest - The user data to be registered.
	 * 
	 * @return ResponseEntity<UserResponse> - An HTTP response containing the
	 *         created user and a "Created" status.
	 */
	@PostMapping("/register")
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
		ModelMapper mapper = new ModelMapper();
		UserDto userDto = mapper.map(userRequest, UserDto.class);
		userDto = userServiceImpl.addUser(userDto);
		
		return new ResponseEntity<>(mapper.map(userDto, UserResponse.class), HttpStatus.CREATED);
	}

	/**
	 * Method responsible for updating a user by their ID in the database
	 * ("/register/{id}").
	 * 
	 * @param userRequest - The updated user data.
	 * 
	 * @param id          - The ID of the user to be updated.
	 * 
	 * @return ResponseEntity<UserResponse> - An HTTP response containing the
	 *         updated user and an "OK" status.
	 */
	@PutMapping("/register/{id}")
	public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserRequest userRequest, @PathVariable String id) {
		ModelMapper mapper = new ModelMapper();
		UserDto userDto = mapper.map(userRequest, UserDto.class);
		userDto = userServiceImpl.updateUser(id, userDto);
		
		return new ResponseEntity<>(mapper.map(userDto, UserResponse.class), HttpStatus.OK);
	}

	/**
	 * Method responsible for updating part of a user by their ID in the database
	 * ("/register/{id}").
	 * 
	 * @param id      - The ID of the user to be updated.
	 * @param userDto - The updated user data.
	 * @return ResponseEntity<UserDto> - An HTTP response containing the updated
	 *         user and an "OK" status.
	 */
	@PatchMapping("/register/{id}")
	public ResponseEntity<UserDto> partialUpdateUser(@PathVariable("id") String id, @RequestBody @Valid UserDto userDto) {
		UserDto updatedUser = userServiceImpl.partialUpdate(id, userDto);
		
		return ResponseEntity.ok(updatedUser);
	}

	/**
	 * Method responsible for deleting a user by their ID in the database ("/{id}").
	 * 
	 * @param id - The ID of the user to be deleted.
	 * 
	 * @return ResponseEntity<Void> - An HTTP response with a "No Content" status if
	 *         the user is deleted successfully, or a "Not Found" status if the user
	 *         is not found.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@Valid @PathVariable String id) {
		UserDto userDto = userServiceImpl.findById(id);
		if (userDto != null) {
			userServiceImpl.deleteUser(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}