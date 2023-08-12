package services

type FlightSearchResult struct {
	FlightsOneWay []map[string]interface{}
	FlightsReturn []map[string]interface{}
}

func NewFlightSearchResult(flightsOneWay, flightsReturn []map[string]interface{}) *FlightSearchResult {
	return &FlightSearchResult{
		FlightsOneWay: flightsOneWay,
		FlightsReturn: flightsReturn,
	}
}

func (result *FlightSearchResult) SetFlightsOneWay(flightsOneWay []map[string]interface{}) {
	result.FlightsOneWay = flightsOneWay
}

func (result *FlightSearchResult) SetFlightsReturn(flightsReturn []map[string]interface{}) {
	result.FlightsReturn = flightsReturn
}

func (result *FlightSearchResult) GetFlightsOneWay() []map[string]interface{} {
	return result.FlightsOneWay
}

func (result *FlightSearchResult) GetFlightsReturn() []map[string]interface{} {
	return result.FlightsReturn
}
