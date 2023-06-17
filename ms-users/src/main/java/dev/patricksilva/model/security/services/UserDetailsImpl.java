package dev.patricksilva.model.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dev.patricksilva.model.dtos.UserDto;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String id;

	private String firstName;

	private String lastName;

	private String email;

	private String card;

	private String cpf;

	private String phone;

	private String cardMonth;

	private String cardYear;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	/**
	 * UserDetails constructor.
	 * 
	 * @param id
	 * @param email
	 * @param password
	 * @param authorities
	 */
	public UserDetailsImpl(String id, String firstName, String lastName, String email, String password,
			Collection<? extends GrantedAuthority> authorities, String card, String cpf, String phone, String cardMonth, String cardYear) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.card = card;
		this.cpf = cpf;
		this.phone = phone;
		this.cardMonth = cardMonth;
		this.cardYear = cardYear;
	}

	public static UserDetailsImpl build(UserDto userDto) {
		List<GrantedAuthority> authorities = userDto.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

		return new UserDetailsImpl(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(),
				userDto.getPassword(), authorities, userDto.getCard(), userDto.getCpf(), userDto.getPhone(),
				userDto.getCardMonth(), userDto.getCardYear());
	}

	// Getters and Setters

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		UserDetailsImpl user = (UserDetailsImpl) obj;

		return Objects.equals(id, user.id);
	}
}