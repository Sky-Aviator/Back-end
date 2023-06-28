package dev.patricksilva.model.services;

import org.springframework.stereotype.Component;

@Component
public class FlightQuery {
	private String cidadeOrigem;
	
	private String cidadeDestino;
	
	private String partidaPrevista;
	
	private String partidaPrevista2;

	private String typeOfFlight;

	private String flightNumber;

	private String airlineName;

	private String passengerName;

	private String numberSeat;

	private String flightClass;

	private String airportOrigin;

	private String airportDestiny;

	private Double flightDuration;

	private Double amount;

	public String getTypeOfFlight() {
		return typeOfFlight;
	}

	public void setTypeOfFlight(String typeOfFlight) {
		this.typeOfFlight = typeOfFlight;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getNumberSeat() {
		return numberSeat;
	}

	public void setNumberSeat(String numberSeat) {
		this.numberSeat = numberSeat;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public String getAirportOrigin() {
		return airportOrigin;
	}

	public void setAirportOrigin(String airportOrigin) {
		this.airportOrigin = airportOrigin;
	}

	public String getAirportDestiny() {
		return airportDestiny;
	}

	public void setAirportDestiny(String airportDestiny) {
		this.airportDestiny = airportDestiny;
	}

	public Double getFlightDuration() {
		return flightDuration;
	}

	public void setFlightDuration(Double flightDuration) {
		this.flightDuration = flightDuration;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCidadeOrigem() {
		return cidadeOrigem;
	}

	public void setCidadeOrigem(String cidadeOrigem) {
		this.cidadeOrigem = cidadeOrigem;
	}

	public String getCidadeDestino() {
		return cidadeDestino;
	}

	public void setCidadeDestino(String cidadeDestino) {
		this.cidadeDestino = cidadeDestino;
	}

	public String getPartidaPrevista() {
		return partidaPrevista;
	}

	public void setPartidaPrevista(String partidaPrevista) {
		this.partidaPrevista = partidaPrevista;
	}

	public String getPartidaPrevista2() {
		return partidaPrevista2;
	}

	public void setPartidaPrevista2(String partidaPrevista2) {
		this.partidaPrevista2 = partidaPrevista2;
	}
}