package dev.patricksilva.model.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.patricksilva.model.dtos.UserDto;
import dev.patricksilva.model.entities.Role;
import dev.patricksilva.model.entities.User;
import dev.patricksilva.model.enums.ERole;
import dev.patricksilva.model.exceptions.ResourceNotFoundException;
import dev.patricksilva.model.exceptions.RoleNotFoundException;
import dev.patricksilva.model.repository.RoleRepository;
import dev.patricksilva.model.repository.UserRepository;
import dev.patricksilva.model.security.encoders.Encoder;
import dev.patricksilva.model.security.jwt.payload.request.LoginRequest;
import dev.patricksilva.model.security.jwt.payload.request.UserRequest;
import dev.patricksilva.model.security.jwt.payload.response.MessageResponse;
import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService{
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private static final String NON_EXISTENT_ID="Esse Id não existe, não foi possível encontrar o usuário com esse Id.";
	
	private static final String EXISTENTING_USER="O Usuário já existe no sistema!";
	
	@Autowired
	private Encoder encoder;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	/**
	 * Retrieves all users present in the database.
	 * 
	 * @return List<UserDto> - A list containing all users in the database.
	 */
	@Override
	public List<UserDto> findAll() {
		List<User> users = userRepository.findAll();
		return users.stream().map(user -> new ModelMapper().map(user, UserDto.class)).toList();
	}

	/**
	 * Retrieves a user by their ID.
	 * 
	 * @param id - The ID of the user.
	 * @return UserDto - The user with the specified ID.
	 * @throws ResourceNotFoundException if the user does not exist.
	 */
	@Override
	public UserDto findById(@Valid String id) {
	    User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id: " + id + "{} não encontrado!"));
	    // Apply the CPF and Card mask in the corresponding fields
	    user.setCpf(maskCPF(user.getCpf()));
	    user.setCard(maskCard(user.getCard()));
	    user.setCardCv(maskCv(user.getCardCv()));
	    
	    return new ModelMapper().map(user, UserDto.class);
	}

	/**
	 * Add an user to the database and encode the sensitive fields.
	 * 
	 * @param userDto - The user data to add.
	 * @return UserDto - The added user with assigned ID.
	 * @throws IllegalArgumentException if the user already exists in the system.
	 */
	@Override
	public ResponseEntity<?> addUser(@Valid UserDto userDto, UserRequest userRequest) {
		if (userRepository.existsByEmail(userDto.getEmail())) {
			logger.warn(EXISTENTING_USER);
		}
		ModelMapper mapper = new ModelMapper();
		User user = mapper.map(userDto, User.class);

		getRolesFromRequest(userRequest);

		user.setPassword(encoder.encode(user.getPassword()));
		user.setCpf(encoder.encodeCPF(user.getCpf()));
		user.setCard(encoder.encodeCard(user.getCard()));
		user.setCardCv(encoder.encode(user.getCardCv()));
		user.setRoles(getRolesFromRequest(userRequest));
				
		userRepository.save(user);
		
		userDto.setId(user.getId());

		return ResponseEntity.ok(new MessageResponse("Usuário registrado com sucesso!"));
	}	

	/**
	 * Deletes a user by their ID.
	 * 
	 * @param id - The ID of the user to delete.
	 * @throws ResourceNotFoundException if the user does not exist.
	 */
	@Override
	public void deleteUser(@Valid String id) {
		if (!userRepository.existsById(id)) {
			logger.warn(NON_EXISTENT_ID);
		}
		userRepository.deleteById(id);
	}

	/**
	 * Update a user by ID.
	 * 
	 * @param id      the ID of the user to update
	 * @param userDto the updated user data
	 * @return the updated UserDto
	 * @throws ResourceNotFoundException if the user does not exist
	 */
	@Override
	public UserDto updateUser(@Valid String id, UserDto userDto, UserRequest userRequest) {
		if (!userRepository.existsById(id)) {
			logger.warn(NON_EXISTENT_ID);
		}
		userDto.setId(id);
		ModelMapper mapper = new ModelMapper();
		User user = mapper.map(userDto, User.class);
		
		getRolesFromRequest(userRequest);
		
		user.setPassword(encoder.encode(user.getPassword()));
		user.setCpf(encoder.encodeCPF(user.getCpf()));
		user.setCard(encoder.encodeCard(user.getCard()));
		user.setCardCv(encoder.encode(user.getCardCv()));
		user.setRoles(getRolesFromRequest(userRequest));
	
		userRepository.save(user);
		
		return userDto;
	}
	
	/**
	 * Partially update a user by ID.
	 *
	 * @param id      the ID of the user to update
	 * @param userDto the updated user data
	 * @return the updated UserDto
	 * @throws ResourceNotFoundException if the user does not exist
	 */
	@Override
	public UserDto partialUpdate(@Valid String id, UserDto userDto) {
	    if (!userRepository.existsById(id)) {
	        logger.warn(NON_EXISTENT_ID);
	    }
	    User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id: " + id + " não encontrado!"));
		if (userDto.getEmail() != null) {
			existingUser.setEmail(userDto.getEmail());
		}
	    User updatedUser = userRepository.save(existingUser);
	    ModelMapper mapper = new ModelMapper();

	    return mapper.map(updatedUser, UserDto.class);
	}
	
	/**
	 * Masks the given credit card number.
	 *
	 * @param card The credit card number to be masked.
	 * @return The masked credit card number.
	 */
	@Override
	public String maskCard(String card) {
	    if (card != null && card.length() >= 12) {
	    	// Get the last four digits of the card
	        String cardLastDigits = card.substring(card.length() - 4);

	        // Removes all non-numeric characters from the card
	        String cardDigits = card.replaceAll("\\D", "");

	        // Apply mask to encoded digits
	        String maskedCardDigits = cardDigits.replaceAll(".", "*");

	        // Format the mask
	        StringBuilder maskedCard = new StringBuilder(maskedCardDigits);
	        maskedCard.insert(4, "-");
	        maskedCard.insert(9, "-");
	        maskedCard.insert(14, "-");
	        maskedCard.delete(15, 60);
	        maskedCard.append(cardLastDigits);

	        return maskedCard.toString();
	    }

	    return card;
	}
	
	/**
	 * Masks the given CPF (Brazilian Individual Registry) number.
	 *
	 * @param cpf The CPF number to be masked.
	 * @return The masked CPF number.
	 */
	@Override
	public String maskCPF(String cpf) {
		if (cpf != null && cpf.length() >= 11) {
			// Get the last two digits of the CPF
			String cpfLastDigits = cpf.substring(cpf.length() - 2);

			// Remove all non-numeric characters from the CPF
			String cpfDigits = cpf.replaceAll("\\D", "");

			// Apply the mask to the encoded digits
			String maskedCPFDigits = cpfDigits.replaceAll(".", "*");

			// Build the final representation of the CPF with the mask
			StringBuilder maskedCPF = new StringBuilder(maskedCPFDigits);
			maskedCPF.insert(3, "-");
			maskedCPF.insert(7, "-");
			maskedCPF.insert(11, "-");
			maskedCPF.delete(12, 60);
			maskedCPF.append(cpfLastDigits);

			return maskedCPF.toString();
		}

		return cpf;
	}

	/**
	 * Masks the given CV (Card Verification) number.
	 *
	 * @param cardCv The CV number to be masked.
	 * @return The masked CV number.
	 */
	@Override
	public String maskCv(String cardCv) {
		if (cardCv != null && cardCv.length() >= 4) {
			// Apply the mask to the CV digits
			String maskedCardCvDigits = cardCv.replaceAll(".", "*");

			// Build the final representation of the CV with the mask
			StringBuilder maskedCardCv = new StringBuilder(maskedCardCvDigits);
			maskedCardCv.delete(4, 60);

			return maskedCardCv.toString();
		}

		return cardCv;
	}

	/**
	 * Adds a role for user.
	 * 
	 * @param userRequest
	 * @return User's roles.
	 */
	public Set<Role> getRolesFromRequest(UserRequest userRequest) {
		Set<String> strRoles = userRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RoleNotFoundException("Erro: Função " + ERole.ROLE_USER + "não encontrada."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RoleNotFoundException("Erro: Função " + ERole.ROLE_ADMIN + "não encontrada."));
					roles.add(adminRole);
					break;

				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR).orElseThrow(() -> new RoleNotFoundException("Erro: Função " + ERole.ROLE_MODERATOR + "não encontrada."));
					roles.add(modRole);
					break;

				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RoleNotFoundException("Erro: Função " + ERole.ROLE_USER + "não encontrada."));
					roles.add(userRole);
				}
			});
		}

		return roles;
	}

	/**
	 * Finds a user by their email address and returns the corresponding UserDto object.
	 *
	 * @param email The email address of the user to be found.
	 * @return A UserDto object representing the user with the given email.
	 * @throws ResourceNotFoundException if no user is found with the provided email.
	 */
	@Override
	public UserDto findByEmail(String email) {
	    UserDto user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("E-mail: " + email + " não encontrado!"));
	    user.setCpf(maskCPF(user.getCpf()));
	    user.setCard(maskCard(user.getCard()));
	    user.setCardCv(maskCv(user.getCardCv()));
	    
	    return new ModelMapper().map(user, UserDto.class);
	}
	
	/**
	 * Method to check id's.
	 * 
	 * @param id
	 */
	public String checkId(String id) {
		if (id.isEmpty() || id.isBlank()) {
			return null;
		}
		return id;
	}

	/**
	 * Method to check if email of login request is valid.
	 * 
	 * @param loginRequest
	 */
	public LoginRequest checkLoginEmailRequest(LoginRequest loginRequest) {
		if (loginRequest.getEmail().isEmpty() || loginRequest.getEmail().isBlank()) {
			return null;
		}
		return loginRequest;
	}
	
	/**
	 * Method to check if email of user request is valid.
	 * 
	 * @param userRequest
	 */
	public UserRequest checkUserEmailRequest(UserRequest userRequest) {
		if (userRequest.getEmail().isEmpty() || userRequest.getEmail().isBlank()) {
			return null;
		}
		return userRequest;
	}
	
	/**
	 * Method to check if userDto's email is valid.
	 * 
	 * @param userDto
	 */
	public UserDto checkUserDtoEmail(UserDto userDto) {
		if(userDto.getEmail().isBlank() || userDto.getEmail().isEmpty()) {
			return null;
		}
		return userDto;
	}
}