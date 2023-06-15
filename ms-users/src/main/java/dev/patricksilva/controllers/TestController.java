package dev.patricksilva.controllers;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
public class TestController {

	@Autowired
	private Environment env;

	@GetMapping("current-user/getId")
	public ResponseEntity<String> getToken(@RequestHeader HttpHeaders headers) {
	    String user = getUserIdByToken(headers, env.getProperty("sky-aviator.app.jwtSecret"));

	    return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	public String getUserIdByToken(HttpHeaders headers, String secret) {
	    String token = headers.getFirst("Authorization"); // Obter o token nos headers
	    String jwt = token.replace("Bearer ", ""); // Remover o prefixo "Bearer"

	    Claims claims = Jwts.parserBuilder()
	            .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
	            .build()
	            .parseClaimsJws(jwt)
	            .getBody();

	    return "id: " +  claims.get("id", String.class);
	}
}