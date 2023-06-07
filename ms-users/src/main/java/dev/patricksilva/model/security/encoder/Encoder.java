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

	@Override
	public boolean matches(CharSequence plainTextPassword, String passwordInDatabase) {
		return BCrypt.checkpw(plainTextPassword.toString(), passwordInDatabase);
	}
}