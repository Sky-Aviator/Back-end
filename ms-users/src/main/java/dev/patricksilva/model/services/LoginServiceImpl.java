package dev.patricksilva.model.services;

import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import dev.patricksilva.model.enums.InformationType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class LoginServiceImpl implements LoginService {

	 /**
     * Retrieves user information from the JWT token.
     *
     * @param headers The HTTP headers containing the authorization token.
     * @param secret The secret key used to sign the JWT token.
     * @param type The type of information requested.
     * @return The requested user information.
     */
	@Override
	public String getUserInformationByToken(HttpHeaders headers, String secret, InformationType type) {
		String token = headers.getFirst("Authorization"); // Obter o token nos headers
		if(token == null) {
			return null;
		}
		
		String jwt = token.replace("Bearer ", ""); // Remover o prefixo "Bearer"
		if(jwt == null) {
			return null;
		}

		Claims claims = Jwts.parserBuilder().setSigningKey(secret.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(jwt).getBody();

		switch (type) {
		
		case ID:
			return "id: " + claims.get("id", String.class);
		case CARD:
			return "card: " + claims.get("card", String.class);
		case CPF:
			return "cpf: " + claims.get("cpf", String.class);
		case CARD_EXPIRATION:
			String cardExpiration = claims.get("cardMonth", String.class) + "/" + claims.get("cardYear", String.class);
			return "expiration: " + cardExpiration;
		case FULL_NAME:
			String fullName = claims.get("firstName", String.class) + " " + claims.get("lastName", String.class);
			return fullName;
		case PHONE:
			return claims.get("phone", String.class);
		default:
			throw new IllegalArgumentException("Tipo de informação inválido");
		}
	}
}