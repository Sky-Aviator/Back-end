package dev.patricksilva.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/booking")
public class bookingController {
	
	/**
	 * Returns a message containing the port at which the service is running. O(1)
	 *
	 * @param port The port at which the service is running.
	 * @return A message containing the port at which the service is running.
	 */
	@Operation(summary = "Fornece a porta que o serviço está operando.", description = "Fornece a porta que o microserviço está operando.")
	@GetMapping("/status")
	public String statusService(@Value("${local.server.port}") String port) {

		return String.format("Service running at port: %s", port);
	}
}