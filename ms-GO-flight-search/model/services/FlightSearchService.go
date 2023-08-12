package services

import (
	"ms-GO-flight-search/model/repositories"
)

type FlightSearchService struct {
	FlightSearchRepository repositories.FlightSearchRepository
}

func NewFlightSearchService(repo repositories.FlightSearchRepository) *FlightSearchService {
	return &FlightSearchService{
		FlightSearchRepository: repo,
	}
}

func (service *FlightSearchService) SearchFlights(cidadeOrigem, cidadeDestino, partidaPrevista string) ([]map[string]interface{}, error) {
	return service.FlightSearchRepository.SearchFlights(cidadeOrigem, cidadeDestino, partidaPrevista)
}
