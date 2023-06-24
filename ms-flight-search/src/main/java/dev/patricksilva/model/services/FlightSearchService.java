package dev.patricksilva.model.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.patricksilva.model.repositories.FlightSearchRepository;

@Service
public class FlightSearchService {

	@Autowired
    private FlightSearchRepository flightSearchRepository;

    public List<Map<String, Object>> searchFlights(String cidadeOrigem, String cidadeDestino, String partidaPrevista) {
    	return flightSearchRepository.searchFlights(cidadeOrigem, cidadeDestino, partidaPrevista);
    }
}