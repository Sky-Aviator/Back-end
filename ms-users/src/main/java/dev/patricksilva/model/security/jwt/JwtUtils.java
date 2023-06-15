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

	// generateJwtToken alimentado somente com o id, abaixo:
	public String generateJwtToken(Authentication authentication) {
	    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
	    
	    Claims claims = Jwts.claims().setSubject(userPrincipal.getEmail());

	    // Adicione o ID do usuário como campo personalizado
	    claims.put("id", userPrincipal.getId()); 
	    
	    Date now = new Date();
	    Date expiration = new Date(now.getTime() + jwtExpirationMs);

	    return Jwts.builder()
	            .setClaims(claims)
	            .setIssuedAt(now)
	            .setExpiration(expiration)
	            .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS512)
	            .compact();
	}

	public String getEmailFromJwtToken(String token) {
		return Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(token).getBody().getSubject();
	}

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