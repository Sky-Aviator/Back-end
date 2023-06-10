package dev.patricksilva.view;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Class representing a user request.
 *
 * @author Patrick L. da Silva
 */
public class UserRequest {

	@NotBlank
	@Length(min = 2, max = 250)
	private String firstName;

	@NotBlank
	@Length(min = 2, max = 250)
	private String lastName;

	@NotBlank
	@Length(min = 11, max = 14)
	private String cpf;

	@Length(min = 12, max = 64)
	private String card;

	@Length(min = 2, max = 8)
	private String cardMonth;

	@Length(min = 2, max = 8)
	private String cardYear;

	@Length(min = 2, max = 8)
	private String cardCv;

	@NotBlank
	@Length(min = 8, max = 64)
	private String date;

	@NotBlank
	@Length(min = 9, max = 64)
	private String sex;

	@NotBlank
	@Length(min = 9, max = 64)
	private String phone;

	@NotBlank
	@Length(min = 12, max = 250)
	@Email
	private String email;

	@NotBlank
	@Length(min = 12, max = 250)
	private String password;

	// Getters and Setters

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
}