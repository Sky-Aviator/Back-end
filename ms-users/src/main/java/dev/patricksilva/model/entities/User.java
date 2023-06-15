package dev.patricksilva.model.entities;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "users")
public class User {

	/*
	 * Using the @NotBlank and @Length annotations, this means that the data
	 * invalids will not be accepted when saving the entity in the database, even if
	 * validation on the request object has been passed.
	 */
	@Id
	private String id;

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

	@DBRef
	private Set<Role> roles = new HashSet<>();

	/**
	 * Explicit Constructor.
	 */
	public User() {
	}

	/**
	 * User Contructor.
	 * 
	 * @param username
	 * @param email
	 * @param password
	 */
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	/**
	 * Retrieves the user's ID.
	 *
	 * @return The user's ID.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the user's ID.
	 *
	 * @param id The user's ID.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retrieves the user's first name.
	 *
	 * @return The user's first name.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the user's first name.
	 *
	 * @param firstName The user's first name.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Retrieves the user's last name.
	 *
	 * @return The user's last name.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the user's last name.
	 *
	 * @param lastName The user's last name.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Retrieves the user's date.
	 *
	 * @return The user's date.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the user's date.
	 *
	 * @param date The user's date.
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Retrieves the user's sex.
	 *
	 * @return The user's sex.
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * Sets the user's sex.
	 *
	 * @param sex The user's sex.
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * Retrieves the user's phone number.
	 *
	 * @return The user's phone number.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the user's phone number.
	 *
	 * @param phone The user's phone number.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Retrieves the user's email.
	 *
	 * @return The user's email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the user's email.
	 *
	 * @param email The user's email.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retrieves the user's password.
	 *
	 * @return The user's password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the user's password.
	 *
	 * @param password The user's password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Retrieves the user's CPF.
	 *
	 * @return The user's CPF.
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Sets the user's CPF.
	 *
	 * @param cpf The user's CPF.
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * Retrieves the user's card.
	 *
	 * @return The user's card.
	 */
	public String getCard() {
		return card;
	}

	/**
	 * Sets the user's card.
	 *
	 * @param card The user's card.
	 */
	public void setCard(String card) {
		this.card = card;
	}

	/**
	 * Retrieves the user's card month.
	 *
	 * @return The user's card month.
	 */
	public String getCardMonth() {
		return cardMonth;
	}

	/**
	 * Sets the user's card month.
	 *
	 * @param cardMonth The user's card month.
	 */
	public void setCardMonth(String cardMonth) {
		this.cardMonth = cardMonth;
	}

	/**
	 * Retrieves the user's card year.
	 *
	 * @return The user's card year.
	 */
	public String getCardYear() {
		return cardYear;
	}

	/**
	 * Sets the user's card year.
	 *
	 * @param cardYear The user's card year.
	 */
	public void setCardYear(String cardYear) {
		this.cardYear = cardYear;
	}

	/**
	 * Retrieves the user's card CV.
	 *
	 * @return The user's card CV.
	 */
	public String getCardCv() {
		return cardCv;
	}

	/**
	 * Sets the user's card CV.
	 *
	 * @param cardCv The user's card CV.
	 */
	public void setCardCv(String cardCv) {
		this.cardCv = cardCv;
	}

	/**
	 * Retrieves the user's role.
	 * 
	 * @return the user's role.
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * Sets the user's role.
	 * 
	 * @param roles - The user's role.
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}