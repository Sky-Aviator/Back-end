package dev.patricksilva.model.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FlightSearchRepositoryImpl implements FlightSearchRepository {

	@Autowired
    private JdbcTemplate jdbc;

	/**
     * Searches for flights based on the specified criteria. O(log n)
     *
     * @param cidadeOrigem      The origin city of the flight.
     * @param cidadeDestino     The destination city of the flight.
     * @param partidaPrevista   The expected departure date of the flight.
     * @return A list of flights matching the specified criteria.
     */
	@Override
    public List<Map<String, Object>> searchFlights(String cidadeOrigem, String cidadeDestino, String partidaPrevista) {
        String query = "SELECT codigo_tipo_linha as 'Tipo de Voo', voos as 'N. do Voo', companhia_aerea as Companhia, aeroporto_origem as 'Aeroporto de Origem', cidade_origem as 'Cidade de Origem', partida_prevista as 'Previsao de Partida', aeroporto_destino as 'Aeroporto Destino', cidade_destino as 'Cidade de Destino', chegada_prevista as 'Previsao de Chegada' "
                + "FROM `flight-search`.flight_search WHERE cidade_origem LIKE ? AND cidade_destino LIKE ? AND partida_prevista LIKE ? ORDER BY partida_prevista ASC";
        
        return jdbc.queryForList(query, "%" + cidadeOrigem + "%", "%" + cidadeDestino + "%", "%" + partidaPrevista + "%");
    }
}