package dev.patricksilva.model.security.encoder;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Encoder implements PasswordEncoder {
	
	@Override
	public String encode(CharSequence plainTextPassword) {
		if (plainTextPassword != null) {
			return BCrypt.hashpw(plainTextPassword.toString(), BCrypt.gensalt(8));
		}
		return (String) plainTextPassword;
	}

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

	@Override
	public boolean matches(CharSequence plainTextPassword, String passwordInDatabase) {
		return BCrypt.checkpw(plainTextPassword.toString(), passwordInDatabase);
	}
}