package dev.patricksilva.model.error;

public class ErrorMessage {
	private String title;
	private Integer status;
	private String message;

	/**
	 * Constructs an ErrorMessage with the specified title, status, and message.
	 *
	 * @param title   - The title of the error.
	 * @param status  - The status code of the error.
	 * @param message - The error message.
	 */
	public ErrorMessage(String title, Integer status, String message) {
		this.title = title;
		this.status = status;
		this.message = message;
	}

	/**
	 * Gets the title of the error.
	 *
	 * @return The title of the error.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the error.
	 *
	 * @param title - The title of the error.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the status code of the error.
	 *
	 * @return The status code of the error.
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Sets the status code of the error.
	 *
	 * @param status - The status code of the error.
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * Gets the error message.
	 *
	 * @return The error message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the error message.
	 *
	 * @param message - The error message.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}