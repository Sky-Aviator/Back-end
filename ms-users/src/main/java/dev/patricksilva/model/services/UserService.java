package dev.patricksilva.model.services;

import java.util.List;

import dev.patricksilva.model.dtos.UserDto;

/**
 * Interface for user-related operations and services.
 * 
 * @author Patrick L. da Silva
 */
public interface UserService {

	/**
	 * Adds a new user to the system.
	 *
	 * @param userDto the user data to add
	 * @return the added user with assigned ID
	 */
	UserDto addUser(UserDto userDto);

	/**
	 * Retrieves a user by ID.
	 *
	 * @param id the ID of the user to retrieve
	 * @return the found user, or null if not found
	 */
	UserDto findById(String id);

	/**
	 * Retrieves all users.
	 *
	 * @return a list of all users
	 */
	List<UserDto> findAll();

	/**
	 * Deletes a user by ID.
	 *
	 * @param id the ID of the user to delete
	 */
	void deleteUser(String id);

	/**
	 * Updates a user by ID with the provided user data.
	 *
	 * @param id      the ID of the user to update
	 * @param userDto the updated user data
	 * @return the updated user
	 */
	UserDto updateUser(String id, UserDto userDto);

	/**
	 * Performs a partial update on a user by ID with the provided user data.
	 *
	 * @param id      the ID of the user to update
	 * @param userDto the partial user data to update
	 * @return the updated user
	 */
	UserDto partialUpdate(String id, UserDto userDto);

	/**
	 * Masks the CPF (Brazilian ID) for display purposes.
	 *
	 * @param cpf the CPF to mask
	 * @return the masked CPF
	 */
	String maskCPF(String cpf);

	/**
	 * Masks the card number for display purposes.
	 *
	 * @param card the card number to mask
	 * @return the masked card number
	 */
	String maskCard(String card);

	/**
	 * Masks the card CV (Card Verification) for display purposes.
	 *
	 * @param cardCv the card CV to mask
	 * @return the masked card CV
	 */
	String maskCv(String cardCv);
}
