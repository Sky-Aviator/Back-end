package dev.patricksilva.model.dtos;

/**
 * Data Transfer Object (DTO) representing a user.
 * 
 * @author Patrick L. da Silva
 */
public class UserDto {
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
	private String password;

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
	 * Gets the CPF (Brazilian individual taxpayer registry) of the user.
	 *
	 * @return The CPF of the user.
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Sets the CPF (Brazilian individual taxpayer registry) of the user.
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
	 * Gets the card month of the user.
	 *
	 * @return The card month of the user.
	 */
	public String getCardMonth() {
		return cardMonth;
	}

	/**
	 * Sets the card month of the user.
	 *
	 * @param cardMonth The card month of the user.
	 */
	public void setCardMonth(String cardMonth) {
		this.cardMonth = cardMonth;
	}

	/**
	 * Gets the card year of the user.
	 *
	 * @return The card year of the user.
	 */
	public String getCardYear() {
		return cardYear;
	}

	/**
	 * Sets the card year of the user.
	 *
	 * @param cardYear The card year of the user.
	 */
	public void setCardYear(String cardYear) {
		this.cardYear = cardYear;
	}

	/**
	 * Gets the card CVV (Card Verification Value) of the user.
	 *
	 * @return The card CVV of the user.
	 */
	public String getCardCv() {
		return cardCv;
	}

	/**
	 * Sets the card CVV (Card Verification Value) of the user.
	 *
	 * @param cardCv The card CVV of the user.
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

	/**
	 * Gets the password of the user.
	 *
	 * @return The password of the user.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the user.
	 *
	 * @param password The password of the user.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}