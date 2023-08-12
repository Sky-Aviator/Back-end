package repositories

type FlightSearchRepository interface {
	SearchFlights(cidadeOrigem, cidadeDestino, partidaPrevista string) ([]map[string]interface{}, error)
}
