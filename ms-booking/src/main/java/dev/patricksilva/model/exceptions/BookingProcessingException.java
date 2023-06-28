package dev.patricksilva.model.exceptions;

public class BookingProcessingException extends RuntimeException {
	private static final long serialVersionUID = 20L;

	public BookingProcessingException(String message) {
		super(message);
	}

	public BookingProcessingException(String message, Throwable cause) {
		super(message, cause);
	}
}