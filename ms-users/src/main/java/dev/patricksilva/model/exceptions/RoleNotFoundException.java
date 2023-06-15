package dev.patricksilva.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -2856448994104603442L;

	public RoleNotFoundException(String message) {
		super(message);
	}
}