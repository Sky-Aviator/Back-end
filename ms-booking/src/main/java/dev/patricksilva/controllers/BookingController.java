package dev.patricksilva.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class BookingController {

	/**
	 * Returns a message containing the port at which the service is running. O(1)
	 *
	 * @param port The port at which the service is running.
	 * @return A message containing the port at which the service is running.
	 */
	@Operation(summary = "Fornece a porta que o serviço está operando.", description = "Fornece a porta que o microserviço está operando.")
	@GetMapping("/api/v1/booking/status")
	public String statusService(@Value("${local.server.port}") String port) {

		return String.format("Service running at port: %s", port);
	}
	
	@Operation(summary = "Recupera o nome fornecido no serviço de 'ms-users' atraves do Jwt Token.", description = "Recupera o nome fornecido no serviço de 'ms-users' atraves do Jwt Token.")
	@GetMapping("/api/v1/booking/user-information")
	public ResponseEntity<String> getUserInformation(@RequestHeader("Authorization") String authorizationHeader) {
	    String url = "http://localhost:9093/current-user/FULL_NAME";

	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Authorization", authorizationHeader);

	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);

	    if (response.getStatusCode() == HttpStatus.OK) {
	        String userInformation = response.getBody();
	        return ResponseEntity.ok(userInformation);
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
}