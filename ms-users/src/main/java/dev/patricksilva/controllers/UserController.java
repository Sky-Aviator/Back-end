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
import dev.patricksilva.model.entities.User;
import dev.patricksilva.model.exception.ResourceNotFoundException;
import dev.patricksilva.model.services.UserServiceImpl;
import dev.patricksilva.view.UserRequest;
import dev.patricksilva.view.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "MS-USERS", description="Endpoints management.")
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
	@Operation(summary = "Recupera todos os Usuário no sistema.", 
	description = "Recupera todos os usuários no sistema. A resposta é o objeto usuário com: id, firstName, lastName, cpf, card, cardMonth, cardYear, cardCv, date, sex, phone, email.")
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
	@Operation(summary = "Recupera um Usuário por ID.", 
	description = "Recupera um usuário passando como especificação o seu ID. A resposta é o objeto usuário com: id, firstName, lastName, cpf, card, cardMonth, cardYear, cardCv, date, sex, phone, email.")
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
	 * Retrieves the CPF (Brazilian Individual Registry) of a user by their
	 * ID.
	 *
	 * @param id - The ID of the user.
	 * @return String - The masked CPF number of the user.
	 * @throws ResourceNotFoundException if the user does not exist.
	 */
	@Operation(summary = "Recupera um cpf por ID do Usuário.", 
	description = "Recupera um cpf passando como especificação o ID de seu portador. A resposta é o cpf daquele usuário com máscara: 'cpf' : '***-***-***-XX'.")
	@GetMapping("/{id}/cpf")
	public String getUserCpf(@PathVariable String id) {
		UserDto userDto = userServiceImpl.findById(id);

		if (userDto != null) {
			return userDto.getCpf();
		}
		throw new ResourceNotFoundException("Usuário com ID: " + id + " não foi encontrado.");
	}
	
	/**
	 * Retrieves the card number of a user by their ID.
	 *
	 * @param id - The ID of the user.
	 * @return String - The masked card number of the user.
	 * @throws ResourceNotFoundException if the user does not exist.
	 */
	@Operation(summary = "Recupera um cartão por ID do Usuário.", 
	description = "Recupera um cartão passando como especificação o ID de seu portador. A resposta é o cartão daquele usuário com máscara: 'card' : '****-****-****-XXXX'.")
	@GetMapping("/{id}/card")
	public String getUserCard(@PathVariable String id) {
		UserDto userDto = userServiceImpl.findById(id);

		if (userDto != null) {
			return userDto.getCard();
		}
		throw new ResourceNotFoundException("Usuário com ID: " + id + " não foi encontrado.");
	}
	
	/**
	 * Retrieves the card expiration date of a user by their ID.
	 *
	 * @param id - The ID of the user.
	 * @return String - The concatenated card expiration month and year of the user.
	 * @throws ResourceNotFoundException if the user does not exist.
	 */
	@Operation(summary = "Recupera a data de expiração de um cartão por ID do Usuário.", 
	description = "Recupera a data de expiração de um cartão passando como especificação o ID de seu portador. A resposta é a data de expiração do cartão daquele usuário: 'cardMonth + cardYear' : 'XX/YY'.")
	@GetMapping("/{id}/cardExpiration")
	public String getUserCardExpiration(@PathVariable String id) {
		UserDto userDto = userServiceImpl.findById(id);

		if (userDto != null) {
			return userDto.getCardMonth() + "/" + userDto.getCardYear();
		}
		throw new ResourceNotFoundException("Usuário com ID: " + id + " não foi encontrado.");
	}

	/**
	 * Method responsible for registering a new user in the database ("/register").
	 * 
	 * @param userRequest - The user data to be registered.
	 * 
	 * @return ResponseEntity<UserResponse> - An HTTP response containing the
	 *         created user and a "Created" status.
	 */
	@Operation(summary = "Registra um novo Usuário.", 
	description = "Faz o cadastro de um novo usuário no sistema.")
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
	@Operation(summary = "Atualiza um Usuário por seu ID.", 
	description = "Atualiza o cadastro de um Usuário por seu ID. A resposta são os campos escolhidos atualizados.: 'firstName':'João'.")
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
	@Operation(summary = "Atualiza pacialmente um Usuário por seu ID.", 
	description = "Atualiza parcialmente o cadastro de um Usuário por seu ID. A resposta são os campos escolhidos atualizados.: 'firstName':'João'.")
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
	@Operation(summary = "Deleta um Usuário por seu ID.", 
	description = "Deleta de forma permanente o cadastro de um Usuário por seu ID. A resposta é: 'No Content'.")
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