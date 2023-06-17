package dev.patricksilva.model.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import dev.patricksilva.model.security.services.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${sky-aviator.app.jwtSecret}")
	private String jwtSecret;

	@Value("${sky-aviator.app.jwtExpirationMs}")
	private long jwtExpirationMs;

	/**
	 * Generates a JWT token based on the user's authentication.
	 *
	 * @param authentication The authentication object.
	 * @return The generated JWT token.
	 */
	public String generateJwtToken(Authentication authentication) {
	    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
	    
	    Claims claims = Jwts.claims().setSubject(userPrincipal.getEmail());

	    // Adicione o ID do usuário como campo personalizado
	    claims.put("id", userPrincipal.getId()); 
	    claims.put("firstName", userPrincipal.getFirstName());
	    claims.put("lastName", userPrincipal.getLastName());
	    claims.put("cpf", userPrincipal.getCpf());
	    claims.put("phone", userPrincipal.getPhone());
	    claims.put("card", userPrincipal.getCard());
	    claims.put("cardMonth", userPrincipal.getCardMonth());
	    claims.put("cardYear", userPrincipal.getCardYear());
	    
	    Date now = new Date();
	    Date expiration = new Date(now.getTime() + jwtExpirationMs);

	    return Jwts.builder()
	            .setClaims(claims)
	            .setIssuedAt(now)
	            .setExpiration(expiration)
	            .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS512)
	            .compact();
	}

	/**
     * Retrieves the email from the JWT token.
     *
     * @param token The JWT token.
     * @return The email extracted from the token.
     */
	public String getEmailFromJwtToken(String token) {
		return Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(token).getBody().getSubject();
	}

	/**
	 * Validates the JWT token.
	 *
	 * @param authToken The JWT token to validate.
	 * @return True if the token is valid, false otherwise.
	 */
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Assinatura JWT inválida: {}", e.getMessage());
		}
		catch (MalformedJwtException e) {
			logger.error("JWT token inválido: {}", e.getMessage());
		}
		catch (ExpiredJwtException e) {
			logger.error("JWT token está expirado: {}", e.getMessage());
		} 
		catch (UnsupportedJwtException e) {
			logger.error("JWT token não suportado: {}", e.getMessage());
		}
		catch (IllegalArgumentException e) {
			logger.error("JWT claims string está vazia: {}", e.getMessage());
		}

		return false;
	}
}