package dev.patricksilva.model.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserTest {

	@Test
	public void testGetId() {
		String id = "3213123e2d9192293231";
		User user = new User();
		user.setId(id);

		assertEquals(id, user.getId());
	}

	@Test
	public void testGetFirstName() {
		String firstName = "Patrick";
		User user = new User();
		user.setFirstName(firstName);

		assertEquals(firstName, user.getFirstName());
	}

	@Test
	public void testGetLastName() {
		String lastName = "Silva";
		User user = new User();
		user.setLastName(lastName);

		assertEquals(lastName, user.getLastName());
	}

	@Test
	public void testGetCpf() {
		String cpf = "12345678901";
		User user = new User();
		user.setCpf(cpf);

		assertEquals(cpf, user.getCpf());
	}

	@Test
	public void testGetCard() {
		String card = "1234567890123456";
		User user = new User();
		user.setCard(card);

		assertEquals(card, user.getCard());
	}

	@Test
	public void testGetCardMonth() {
		String cardMonth = "12";
		User user = new User();
		user.setCardMonth(cardMonth);

		assertEquals(cardMonth, user.getCardMonth());
	}

	@Test
	public void testGetCardYear() {
		String cardYear = "2026";
		User user = new User();
		user.setCardYear(cardYear);

		assertEquals(cardYear, user.getCardYear());
	}

	@Test
	public void testGetCardCv() {
		String cardCv = "123";
		User user = new User();
		user.setCardCv(cardCv);

		assertEquals(cardCv, user.getCardCv());
	}

	@Test
	public void testGetPhone() {
		String phone = "1234567890";
		User user = new User();
		user.setPhone(phone);

		assertEquals(phone, user.getPhone());
	}

	@Test
	public void testGetEmail() {
		String email = "patricksilva.me@gmail.com";
		User user = new User();
		user.setEmail(email);

		assertEquals(email, user.getEmail());
	}

	@Test
	public void testGetPassword() {
		String password = "sdioaj9s87as8d&(A*SD&*ASD¨*@#Hdi909q2dk";
		User user = new User();
		user.setPassword(password);

		assertEquals(password, user.getPassword());
	}

	@Test
	public void testGetPhoto() {
		Photo photo = new Photo();
		User user = new User();
		user.setPhoto(photo);

		assertEquals(photo, user.getPhoto());
	}

	@Test
	public void testSetFirstName() {
		String firstName = "Patrick";
		User user = new User();
		user.setFirstName(firstName);

		assertEquals(firstName, user.getFirstName());
	}

	@Test
	public void testSetEmail() {
		String email = "patricksilva.me@gmail.com";
		User user = new User();
		user.setEmail(email);

		assertEquals(email, user.getEmail());
	}

	@Test
	public void testSetPassword() {
		String password = "sdioaj9s87as8d&(A*SD&*ASD¨*@#Hdi909q2dk";
		User user = new User();
		user.setPassword(password);

		assertEquals(password, user.getPassword());
	}

	@Test
	public void testSetToken() {
		String token = "abcd1234";
		User user = new User();
		user.setToken(token);

		assertEquals(token, user.getToken());
	}

	@Test
	public void testGetFullName() {
		String firstName = "Patrick";
		String lastName = "Silva";
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);

		assertEquals(firstName + " " + lastName, user.getFirstName() + " " + user.getLastName());
	}

	@Test
	public void testSetLastName() {
		String lastName = "Silva";
		User user = new User();
		user.setLastName(lastName);

		assertEquals(lastName, user.getLastName());
	}

	@Test
	public void testSetCpf() {
		String cpf = "12345678900";
		User user = new User();
		user.setCpf(cpf);

		assertEquals(cpf, user.getCpf());
	}

	@Test
	public void testSetCard() {
		String card = "1234-5678-9101-2268";
		User user = new User();
		user.setCard(card);

		assertEquals(card, user.getCard());
	}

	@Test
	public void testSetCardMonth() {
		String cardMonth = "09";
		User user = new User();
		user.setCardMonth(cardMonth);

		assertEquals(cardMonth, user.getCardMonth());
	}

	@Test
	public void testSetCardYear() {
		String cardYear = "26";
		User user = new User();
		user.setCardYear(cardYear);

		assertEquals(cardYear, user.getCardYear());
	}

	@Test
	public void testSetCardCv() {
		String cardCv = "257";
		User user = new User();
		user.setCardCv(cardCv);

		assertEquals(cardCv, user.getCardCv());
	}

	@Test
	public void testSetPhone() {
		String phone = "021999999999";
		User user = new User();
		user.setPhone(phone);

		assertEquals(phone, user.getPhone());
	}

	@Test
	public void testSetPhoto() {
		Photo photo = new Photo();
		User user = new User();
		user.setPhoto(photo);

		assertEquals(photo, user.getPhoto());
	}
}