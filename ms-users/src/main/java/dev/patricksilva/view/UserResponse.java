package dev.patricksilva.view;

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
	private String password;

//	public String maskCPF(String cpf) {
//	    if (cpf != null && cpf.length() >= 11) {
//	        String cpfDigits = cpf.substring(0, cpf.length() - 2);
//	        String cpfLastDigits = cpf.substring(cpf.length() - 2);
//
//	        // Aplica a máscara aos dígitos criptografados
//	        String maskedCPFDigits = cpfDigits.replaceAll("\\d", "*");
//
//	        // Monta a representação final do CPF com a máscara
//	        return maskedCPFDigits + cpfLastDigits;
//	    }
//	    return cpf;
//	}

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