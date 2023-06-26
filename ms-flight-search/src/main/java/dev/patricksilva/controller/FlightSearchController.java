package dev.patricksilva.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.patricksilva.model.services.FlightSearchResult;
import dev.patricksilva.model.services.FlightSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "MS-FLIGHT-SEARCH", description="Endpoints Management.")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/flight-search")
public class FlightSearchController {

	@Autowired
	private FlightSearchService flightSearchService;

	/**
	 * Returns a message containing the port at which the service is running. O(1)
	 *
	 * @param port The port at which the service is running.
	 * @return A message containing the port at which the service is running.
	 */
	@Operation(summary = "Fornece a porta que o serviço está operando.", description = "Fornece a porta que o microserviço está operando.")
	@GetMapping
	public String statusService(@Value("${local.server.port}") String port) {

		return String.format("Service running at port: %s", port);
	}

	// List of Map para pesquisar mais rapido que somente Uma Lista de String.
	/**
	 * Performs a search for one-way flights. O(k)
	 *
	 * @param cidadeOrigem    The origin city of the trip.
	 * @param cidadeDestino   The destination city of the trip.
	 * @param partidaPrevista The expected departure date.
	 * @return A response containing the list of corresponding one-way flights.
	 */
	@Operation(summary = "Realiza a pesquisa de passagem de ida única(One Way).", description = "Realiza a pesquisa de passagem aerea de ida única (One Way), ao local desejado.")
	@GetMapping("/flights/oneway")
	public ResponseEntity<List<Map<String, Object>>> searchFlightsOneWay(
			@RequestParam("from") String cidadeOrigem, 
			@RequestParam("to") String cidadeDestino, 
			@RequestParam("going") String partidaPrevista) {
		List<Map<String, Object>> flightsOneWay = flightSearchService.searchFlights(cidadeOrigem, cidadeDestino, partidaPrevista);
		
		return flightsOneWay.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(flightsOneWay);
	}
	
	/**
	 * Performs a search for roundtrip flights. O(k) 
	 *
	 * @param cidadeOrigem      The origin city of the trip.
	 * @param cidadeDestino     The destination city of the trip.
	 * @param partidaPrevista   The expected departure date.
	 * @param partidaPrevista2  The expected return date.
	 * @return A response containing the search results for roundtrip flights.
	 */
	@Operation(summary = "Realiza a pesquisa de passagem de ida e volta (Roundtrip).", description = "Realiza a pesquisa de passagem aerea de ida e volta (Roundtrip), ao local desejado.")
	@GetMapping("/flights/roundtrip")
	public ResponseEntity<FlightSearchResult> searchFlightsRoundtrip(
			@RequestParam("from") String cidadeOrigem, 
			@RequestParam("to") String cidadeDestino, 
			@RequestParam("going") String partidaPrevista, 
			@RequestParam("return") String partidaPrevista2) {
		List<Map<String, Object>> flightsOneWay = flightSearchService.searchFlights(cidadeOrigem, cidadeDestino, partidaPrevista);
	    List<Map<String, Object>> flightsReturn = flightSearchService.searchFlights(cidadeDestino, cidadeOrigem, partidaPrevista2);
	    
		FlightSearchResult result = (!flightsReturn.isEmpty() && !flightsOneWay.isEmpty()) ? new FlightSearchResult(flightsOneWay, flightsReturn) : null;
		return (result != null) ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
	}
}