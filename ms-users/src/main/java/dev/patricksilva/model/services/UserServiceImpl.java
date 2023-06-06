package dev.patricksilva.model.services;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.patricksilva.model.dtos.UserDto;
import dev.patricksilva.model.entities.User;
import dev.patricksilva.model.exception.ResourceNotFoundException;
import dev.patricksilva.model.repository.UserRepository;
import jakarta.validation.Valid;

@Service
public class UserServiceImpl {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Retrieves all users present in the database.
	 * 
	 * @return List<UserDto> - A list containing all users in the database.
	 */
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
	public UserDto findById(@Valid String id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id: " + id + " não encontrado!"));
		return new ModelMapper().map(user, UserDto.class);
	}

	/**
	 * Adds a new user to the database.
	 * 
	 * @param userDto - The user data to add.
	 * @return UserDto - The added user with assigned ID.
	 * @throws IllegalArgumentException if the user already exists in the system.
	 */
	public UserDto addUser(@Valid UserDto userDto) {
		if (userRepository.existsByEmail(userDto.getEmail())) {
			throw new IllegalArgumentException("O Usuário já existe no sistema!");
		}
		ModelMapper mapper = new ModelMapper();
		User user = mapper.map(userDto, User.class);
		user = userRepository.save(user);
		userDto.setId(user.getId());
		
		return userDto;
	}

	/**
	 * Deletes a user by their ID.
	 * 
	 * @param id - The ID of the user to delete.
	 * @throws ResourceNotFoundException if the user does not exist.
	 */
	public void deleteUser(@Valid String id) {
		if (!userRepository.existsById(id)) {
			throw new ResourceNotFoundException("Não pode deletar este Usuário com ID: " + id + ", pois o Usuário não existe!");
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
	public UserDto updateUser(@Valid String id, UserDto userDto) {
		if (!userRepository.existsById(id)) {
			throw new ResourceNotFoundException("Nao pode atualizar este Usuário de ID: " + id + ", pois este Usuário não existe!");
		}
		userDto.setId(id);
		ModelMapper mapper = new ModelMapper();
		User user = mapper.map(userDto, User.class);
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
	public UserDto partialUpdate(@Valid String id, UserDto userDto) {
		if (!userRepository.existsById(id)) {
			throw new ResourceNotFoundException("Não é possível atualizar o usuário com ID: " + id + ", porque o usuário não existe!");
		}
		User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id: " + id + " não encontrado!"));
		// Copiar apenas as propriedades não nulas de userDto para existingUser.
		BeanUtils.copyProperties(userDto, existingUser, getNullPropertyNames(userDto));
		User updatedUser = userRepository.save(existingUser);
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(updatedUser, UserDto.class);
	}

	/**
	 * Retrieves the names of null properties from an object.
	 * @param source - The source object.
	 * @return String[] - An array of names of null properties.
	 */
	private String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		PropertyDescriptor[] descriptors = src.getPropertyDescriptors();
		List<String> nullProperties = Arrays.stream(descriptors)
				.filter(descriptor -> src.getPropertyValue(descriptor.getName()) == null)
				.map(PropertyDescriptor::getName).toList();

		return nullProperties.toArray(new String[0]);
	}
}