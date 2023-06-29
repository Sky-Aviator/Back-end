package dev.patricksilva.model.services;

import java.util.List;
import java.util.Map;

public class FlightSearchResult {
	private List<Map<String, Object>> flightsOneWay;
	private List<Map<String, Object>> flightsReturn;

	public FlightSearchResult(List<Map<String, Object>> flightsOneWay, List<Map<String, Object>> flightsReturn) {
		this.flightsOneWay = flightsOneWay;
		this.flightsReturn = flightsReturn;
	}

	public List<Map<String, Object>> getFlightsOneWay() {
		return flightsOneWay;
	}

	public void setFlightsOneWay(List<Map<String, Object>> flightsOneWay) {
		this.flightsOneWay = flightsOneWay;
	}

	public List<Map<String, Object>> getFlightsReturn() {
		return flightsReturn;
	}

	public void setFlightsReturn(List<Map<String, Object>> flightsReturn) {
		this.flightsReturn = flightsReturn;
	}
}