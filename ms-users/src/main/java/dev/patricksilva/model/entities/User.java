package dev.patricksilva.model.entities;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Length(min = 2, max = 250)
	@Column(nullable = false, length = 250)
	private String firstName;

	@NotBlank
	@Length(min = 2, max = 250)
	@Column(nullable = false, length = 250)
	private String lastName;

	@NotBlank
	@Length(min = 12, max = 64)
	@Column(nullable = false, length = 64, unique = true)
	private String cpf;

	@Length(min = 12, max = 64)
	@Column(length = 64, unique = true)
	private String card;

	@NotBlank
	@Column(nullable = false, length = 64)
	private String date;

	@NotBlank
	@Length(min = 9, max = 64)
	@Column(nullable = false, length = 64)
	private String sex;

	@NotBlank
	@Length(min = 9, max = 64)
	@Column(nullable = false, length = 64, unique = true)
	private String phone;

	@NotBlank
	@Length(min = 12, max = 250)
	@Email
	@Column(nullable = false, length = 250, unique = true)
	private String email;

	@NotBlank
	@Length(min = 12, max = 250)
	@Column(nullable = false, length = 250)
	private String password;

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
}