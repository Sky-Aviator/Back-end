package dev.patricksilva.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.patricksilva.model.services.FlightSearchService;

@RestController
@RequestMapping("/api/v1/flight-search")
public class FlightSearchController {

	@Autowired
	private FlightSearchService flightSearchService;

	@GetMapping
	public String statusService(@Value("${local.server.port}") String port) {

		return String.format("Service running at port: %s", port);
	}

	@GetMapping("/hello")
	public String hello() {
		
		return "Hello ms-flight-search.";
	}

	// List of Map para pesquisar mais rapido que somente Uma Lista de String.
	@GetMapping("/flights")
	public ResponseEntity<List<Map<String, Object>>> searchFlights(@RequestParam("cidadeOrigem") String cidadeOrigem, @RequestParam("cidadeDestino") String cidadeDestino, @RequestParam("partidaPrevista") String partidaPrevista) {
		List<Map<String, Object>> flights = flightSearchService.searchFlights(cidadeOrigem, cidadeDestino, partidaPrevista);
		
		 if (flights.isEmpty()) {
		        return ResponseEntity.noContent().build();
		    } else {
		        return ResponseEntity.ok(flights);
		    }
	}
}