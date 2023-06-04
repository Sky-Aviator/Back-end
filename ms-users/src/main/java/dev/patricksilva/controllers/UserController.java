package dev.patricksilva.controllers;

import java.util.List;
import java.util.Optional;

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

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	/**
	 * Dependency injection
	 */
	@Autowired
	private UserServiceImpl userServiceImpl;

	/**
	 * Method responsible for finding all users.
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<UserResponse>> findAllUsers() {
		ModelMapper mapper = new ModelMapper();
		List<UserDto> users = userServiceImpl.findAll();
		List<UserResponse> response = users.stream().map(u -> mapper.map(u, UserResponse.class)).toList();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Method responsible for finding user by Id ("{/id}").
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Optional<UserResponse>> findUserById(@PathVariable Long id) {
		Optional<UserDto> userDto = userServiceImpl.findById(id);
		if (!userDto.isPresent()) {
			return new ResponseEntity<>(Optional.empty(), HttpStatus.NOT_FOUND);
		} else {
			UserResponse user = new ModelMapper().map(userDto.get(), UserResponse.class);
			return new ResponseEntity<>(Optional.of(user), HttpStatus.OK);
		}
	}

	/**
	 * Method responsible for registering a new user in the database ("/register").
	 * 
	 * @param userRequest
	 * @return
	 */
	@PostMapping("/register")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
		ModelMapper mapper = new ModelMapper();
		UserDto userDto = mapper.map(userRequest, UserDto.class);
		userDto = userServiceImpl.addUser(userDto);

		return new ResponseEntity<>(mapper.map(userDto, UserResponse.class), HttpStatus.CREATED);
	}

	/**
	 * Method responsible for updating a new user by id in the database
	 * ("/register/{id}").
	 * 
	 * @param userRequest
	 * @param id
	 * @return Will return an updated user
	 */
	@PutMapping("/register/{id}")
	public ResponseEntity<UserResponse> putUser(@RequestBody UserRequest userRequest, @PathVariable Long id) {
		ModelMapper mapper = new ModelMapper();
		UserDto userDto = mapper.map(userRequest, UserDto.class);
		userDto = userServiceImpl.update(id, userDto);

		return new ResponseEntity<>(mapper.map(userDto, UserResponse.class), HttpStatus.OK);
	}

	/**
	 * Method responsible for updating part of a new user by id in the database
	 * ("/register/{id}").
	 * 
	 * @param userRequest
	 * @param id
	 * @return Will return an updated user
	 */
	@PatchMapping("/register/{id}")
	public ResponseEntity<UserResponse> partialUserUpdate(@RequestBody UserRequest userRequest, @PathVariable Long id) {
		ModelMapper mapper = new ModelMapper();
		UserDto userDto = mapper.map(userRequest, UserDto.class);
		userDto = userServiceImpl.update(id, userDto);

		return new ResponseEntity<>(mapper.map(userDto, UserResponse.class), HttpStatus.OK);
	}

	/**
	 * Method responsible for deleting a user by id in the database ("/{id}").
	 * 
	 * @param id
	 * @return Will delete a user by id.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
		Optional<UserDto> userDto = userServiceImpl.findById(id);
		if (!userDto.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			userServiceImpl.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}