package dev.patricksilva.model.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class FlightSearchRepositoryImpl implements FlightSearchRepository {

	@Autowired
    private JdbcTemplate jdbc;

	@Override
    public List<Map<String, Object>> searchFlights(String cidadeOrigem, String cidadeDestino, String partidaPrevista) {
        String query = "SELECT codigo_tipo_linha as 'Tipo de Voo', voos as 'N. do Voo', companhia_aerea as Companhia, aeroporto_origem as 'Aeroporto de Origem', cidade_origem as 'Cidade de Origem', partida_prevista as 'Previsao de Partida', aeroporto_destino as 'Aeroporto Destino', cidade_destino as 'Cidade de Destino', chegada_prevista as 'Previsao de Chegada' "
                + "FROM `flight-search`.flight_search WHERE cidade_origem LIKE ? AND cidade_destino LIKE ? AND partida_prevista LIKE ? ORDER BY partida_prevista ASC";

        return jdbc.queryForList(query, "%" + cidadeOrigem + "%", "%" + cidadeDestino + "%", "%" + partidaPrevista + "%");
    }
}