package dev.patricksilva.model.entities;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
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

	// Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getCardMonth() {
		return cardMonth;
	}

	public void setCardMonth(String cardMonth) {
		this.cardMonth = cardMonth;
	}

	public String getCardYear() {
		return cardYear;
	}

	public void setCardYear(String cardYear) {
		this.cardYear = cardYear;
	}

	public String getCardCv() {
		return cardCv;
	}

	public void setCardCv(String cardCv) {
		this.cardCv = cardCv;
	}
}