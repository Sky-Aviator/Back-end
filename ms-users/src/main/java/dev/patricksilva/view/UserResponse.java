package dev.patricksilva.view;

/**
 * Class representing a user response.
 * 
 * @author Patrick L. da Silva
 */
public class UserResponse {
	private String id;
	private String firstName;
	private String lastName;
	private String cpf;
	private String card;
	private String cardMonth;
	private String cardYear;
	private String cardCv;
	private String date;
	private String sex;
	private String phone;
	private String email;

	// Getters and Setters

	/**
	 * Gets the ID of the user.
	 *
	 * @return The ID of the user.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the ID of the user.
	 *
	 * @param id The ID of the user.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the first name of the user.
	 *
	 * @return The first name of the user.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the user.
	 *
	 * @param firstName The first name of the user.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name of the user.
	 *
	 * @return The last name of the user.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the user.
	 *
	 * @param lastName The last name of the user.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the CPF (Brazilian ID) of the user.
	 *
	 * @return The CPF of the user.
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Sets the CPF (Brazilian ID) of the user.
	 *
	 * @param cpf The CPF of the user.
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * Gets the card number of the user.
	 *
	 * @return The card number of the user.
	 */
	public String getCard() {
		return card;
	}

	/**
	 * Sets the card number of the user.
	 *
	 * @param card The card number of the user.
	 */
	public void setCard(String card) {
		this.card = card;
	}

	/**
	 * Gets the expiration month of the user's card.
	 *
	 * @return The card expiration month.
	 */
	public String getCardMonth() {
		return cardMonth;
	}

	/**
	 * Sets the expiration month of the user's card.
	 *
	 * @param cardMonth The card expiration month.
	 */
	public void setCardMonth(String cardMonth) {
		this.cardMonth = cardMonth;
	}

	/**
	 * Gets the expiration year of the user's card.
	 *
	 * @return The card expiration year.
	 */
	public String getCardYear() {
		return cardYear;
	}

	/**
	 * Sets the expiration year of the user's card.
	 *
	 * @param cardYear The card expiration year.
	 */
	public void setCardYear(String cardYear) {
		this.cardYear = cardYear;
	}

	/**
	 * Gets the CV (Card Verification) code of the user's card.
	 *
	 * @return The card CV code.
	 */
	public String getCardCv() {
		return cardCv;
	}

	/**
	 * Sets the CV (Card Verification) code of the user's card.
	 *
	 * @param cardCv The card CV code.
	 */
	public void setCardCv(String cardCv) {
		this.cardCv = cardCv;
	}

	/**
	 * Gets the date of the user.
	 *
	 * @return The date of the user.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date of the user.
	 *
	 * @param date The date of the user.
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Gets the sex of the user.
	 *
	 * @return The sex of the user.
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * Sets the sex of the user.
	 *
	 * @param sex The sex of the user.
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * Gets the phone number of the user.
	 *
	 * @return The phone number of the user.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone number of the user.
	 *
	 * @param phone The phone number of the user.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the email of the user.
	 *
	 * @return The email of the user.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the user.
	 *
	 * @param email The email of the user.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}