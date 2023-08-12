package repositories

import (
	"database/sql"
)

type FlightSearchRepositoryImpl struct {
	DB *sql.DB
}

func NewFlightSearchRepositoryImpl(db *sql.DB) *FlightSearchRepositoryImpl {
	return &FlightSearchRepositoryImpl{
		DB: db,
	}
}

func (repo *FlightSearchRepositoryImpl) SearchFlights(cidadeOrigem, cidadeDestino, partidaPrevista string) ([]map[string]interface{}, error) {
	query := "SELECT codigo_tipo_linha as 'Tipo de Voo', voos as 'N. do Voo', companhia_aerea as Companhia, aeroporto_origem as 'Aeroporto de Origem', cidade_origem as 'Cidade de Origem', partida_prevista as 'Previsao de Partida', aeroporto_destino as 'Aeroporto Destino', cidade_destino as 'Cidade de Destino', chegada_prevista as 'Previsao de Chegada' " +
		"FROM `flight-search`.flight_search WHERE cidade_origem LIKE ? AND cidade_destino LIKE ? AND partida_prevista LIKE ? ORDER BY partida_prevista ASC"

	rows, err := repo.DB.Query(query, "%"+cidadeOrigem+"%", "%"+cidadeDestino+"%", "%"+partidaPrevista+"%")
	if err != nil {
		return nil, err
	}
	defer rows.Close()

	results := []map[string]interface{}{}
	columns, _ := rows.Columns()
	for rows.Next() {
		values := make([]interface{}, len(columns))
		columnPointers := make([]interface{}, len(columns))
		for i := range columns {
			columnPointers[i] = &values[i]
		}

		if err := rows.Scan(columnPointers...); err != nil {
			return nil, err
		}

		rowData := make(map[string]interface{})
		for i, column := range columns {
			val := values[i]
			if val != nil {
				rowData[column] = val
			} else {
				rowData[column] = nil
			}
		}
		results = append(results, rowData)
	}

	return results, nil
}
