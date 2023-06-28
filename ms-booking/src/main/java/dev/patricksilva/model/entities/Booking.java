package dev.patricksilva.model.entities;

import java.util.UUID;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Booking_Going")
public class Booking {

	@Id
	@Column(name = "ticket_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketNumber;

	@Basic
	@Column(name = "type_of_flight", length = 18)
	private String typeOfFlight;

	@Basic
	@Column(name = "flight_number", length = 18)
	private String flightNumber;

	@Basic
	@Column(name = "airline_name", length = 64)
	private String airlineName;

	@Basic
	@Column(name = "passenger_name", length = 64)
	private String passengerName;

	@Basic
	@Column(name = "number_seat", length = 64)
	private String numberSeat;

	@Basic
	@Column(name = "flight_class", length = 64)
	private String flightClass;

	@Basic
	@Column(name = "airport_origin", length = 64)
	private String airportOrigin;

	@Basic
	@Column(name = "from_location", length = 250)
	private String fromLocation;

	@Basic
	@Column(name = "to_location", length = 250)
	private String toLocation;

	@Basic
	@Column(name = "airport_destiny", length = 64)
	private String airportDestiny;

	@Basic
	@Column(name = "departure_time", length = 250)
	private String departureTime;

	@Basic
	@Column(name = "arrival_time", length = 250)
	private String arrivalTime;

	@Basic
	@Column(name = "airport_gate_origin", length = 64)
	private String airportGateOrigin;

	@Basic
	@Column(name = "airport_gate_destiny", length = 64)
	private String airportGateDestiny;

	@Basic
	@Column(name = "flight_duration", length = 64)
	private Double flightDuration;

	@Basic
	@Column(name = "amount", length = 64)
	private Double amount;

	public Booking() {
	// Gerar UUID para os campos
		this.airportGateOrigin = generateRandomUUID();
		this.airportGateDestiny = generateRandomUUID();
		this.numberSeat = generateRandomUUID();
		this.flightClass = "First Class";
	}

	// Generate Random UUID 
	private String generateRandomUUID() {
		UUID uuid = UUID.randomUUID();

		return uuid.toString().substring(0, 4);
	}

	// Getters and Setters

	public Long getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(Long ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

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

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public String getAirportDestiny() {
		return airportDestiny;
	}

	public void setAirportDestiny(String airportDestiny) {
		this.airportDestiny = airportDestiny;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getAirportGateOrigin() {
		return airportGateOrigin;
	}

	public void setAirportGateOrigin(String airportGateOrigin) {
		this.airportGateOrigin = airportGateOrigin;
	}

	public String getAirportGateDestiny() {
		return airportGateDestiny;
	}

	public void setAirportGateDestiny(String airportGateDestiny) {
		this.airportGateDestiny = airportGateDestiny;
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
}