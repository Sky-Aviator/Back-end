package dev.patricksilva.model.repositories;

import java.util.List;
import java.util.Map;

public interface FlightSearchRepository {
    List<Map<String, Object>> searchFlights(String cidadeOrigem, String cidadeDestino, String partidaPrevista);
}