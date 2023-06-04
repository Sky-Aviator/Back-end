package dev.patricksilva.model.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.patricksilva.model.dtos.UserDto;
import dev.patricksilva.model.entities.User;
import dev.patricksilva.model.exception.ResourceNotFoundException;
import dev.patricksilva.model.repository.UserRepository;

@Service
public class UserServiceImpl {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Find all users present in the database.
	 * 
	 * @return It will find all users in the database.
	 */
	public List<UserDto> findAll() {
		List<User> users = userRepository.findAll();

		return users.stream().map(user -> new ModelMapper().map(user, UserDto.class)).toList();
	}

	/**
	 * Find user by id.
	 * 
	 * @param id
	 * @return Will find user by id.
	 */
	public Optional<UserDto> findById(Long id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isEmpty())
			throw new ResourceNotFoundException("Id: " + id + " not found!");

		UserDto dto = new ModelMapper().map(user.get(), UserDto.class);
		return Optional.of(dto);
	}

	/**
	 * Add a new user to the database.
	 * 
	 * @param userDto the user data to add
	 * @return the added UserDto with assigned ID
	 */
	public UserDto addUser(UserDto userDto) {
		userDto.setId(null);
		ModelMapper mapper = new ModelMapper();
		User user = mapper.map(userDto, User.class);
		user = userRepository.save(user);
		userDto.setId(user.getId());

		return userDto;
	}

	/**
	 * Delete a user by ID.
	 * 
	 * @param id the ID of the user to delete
	 * @throws ResourceNotFoundException if the user does not exist
	 */
	public void delete(Long id) {
		if(!userRepository.existsById(id)) {
			throw new ResourceNotFoundException("Não pode deletar este Usuário com ID: " + id + ", pois o Usuário não existe!");
		}
		userRepository.deleteById(id);
	}

	/**
	 * Update a user by ID.
	 * 
	 * @param id : the ID of the user to update
	 * @param userDto : the updated user data
	 * @return the updated UserDto
	 * @throws ResourceNotFoundException if the user does not exist
	 */
	public UserDto update(Long id, UserDto userDto) {
		if (!userRepository.existsById(id)) {
	        throw new ResourceNotFoundException("Nao pode atualizar este Usuário de ID: " + id + ", pois este Usuário não existe!");
	    }
		userDto.setId(id);
		ModelMapper mapper = new ModelMapper();
		User user = mapper.map(userDto, User.class);
		userRepository.save(user);

		return userDto;
	}
}