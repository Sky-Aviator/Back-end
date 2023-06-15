package dev.patricksilva.model.security.encoders;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Encoder implements PasswordEncoder {

	/**
	 * Encodes the provided plain text password using BCrypt hashing.
	 *
	 * @param plainTextPassword the plain text password to encode
	 * @return the encoded password
	 */
	@Override
	public String encode(CharSequence plainTextPassword) {
		if (plainTextPassword != null) {
			return BCrypt.hashpw(plainTextPassword.toString(), BCrypt.gensalt(12));
		}
		return (String) plainTextPassword;
	}

	/**
	 * Encodes the provided plain text CPF (Brazilian ID) using BCrypt hashing.
	 *
	 * @param plainTextCPF the plain text CPF to encode
	 * @return the encoded CPF
	 */
	public String encodeCPF(CharSequence plainTextCPF) {
		if (plainTextCPF != null) {
			String cpf = plainTextCPF.toString();
			String cpfDigits = cpf.substring(0, cpf.length() - 2);
			String cpfLastDigits = cpf.substring(cpf.length() - 2);

			String hashedCPFDigits = BCrypt.hashpw(cpfDigits, BCrypt.gensalt(8));
			return hashedCPFDigits + cpfLastDigits;
		}
		return (String) plainTextCPF;
	}

	/**
	 * Encodes the provided plain text card number using BCrypt hashing.
	 *
	 * @param plainTextCard the plain text card number to encode
	 * @return the encoded card number
	 */
	public String encodeCard(CharSequence plainTextCard) {
		if (plainTextCard != null) {
			String card = plainTextCard.toString();
			String cardDigits = card.substring(0, card.length() - 4);
			String cardLastDigits = card.substring(card.length() - 4);

			String hashedCardDigits = BCrypt.hashpw(cardDigits, BCrypt.gensalt(8));
			return hashedCardDigits + cardLastDigits;
		}
		return (String) plainTextCard;
	}

	/**
	 * Checks if the provided plain text password matches the encoded password
	 * stored in the database.
	 *
	 * @param plainTextPassword  the plain text password to check
	 * @param passwordInDatabase the encoded password stored in the database
	 * @return true if the passwords match, false otherwise
	 */
	@Override
	public boolean matches(CharSequence plainTextPassword, String passwordInDatabase) {
		return BCrypt.checkpw(plainTextPassword.toString(), passwordInDatabase);
	}
}