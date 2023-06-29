package dev.patricksilva.model.services;

import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import dev.patricksilva.model.entities.Booking;
import dev.patricksilva.model.entities.BookingReturn;
import dev.patricksilva.model.exceptions.BookingProcessingException;
import dev.patricksilva.model.repositories.BookingRepository;
import dev.patricksilva.model.repositories.BookingRepositoryReturn;

@Service
public class BookingMessageReceiver {

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private BookingRepositoryReturn bookingRepositoryReturn;
	
	/**
	 * Receives a booking message from the "booking_queue" RabbitMQ queue and processes it.
	 * The method deserializes the message using Gson and extracts the flight information from it.
	 * If the flight information is available and not empty, it creates a new Booking object and saves it to the repository.
	 * If any exception occurs during the processing, a BookingProcessingException is thrown.
	 *
	 * @param message The booking message received from the RabbitMQ queue.
	 * @throws BookingProcessingException If an error occurs while processing the booking message.
	 */
	@RabbitListener(queues = "booking_queue")
	public void receiveBookingTicket(@Payload String message) {
		try {
			Gson gson = new Gson();
			FlightSearchResult flightSearchResult = gson.fromJson(message, FlightSearchResult.class);

			System.out.println("Passagem Armazenada:");
			System.out.println("Flights One Way: " + flightSearchResult.getFlightsOneWay().get(0));

			List<Map<String, Object>> flightsOneWay = flightSearchResult.getFlightsOneWay();
			List<Map<String, Object>> flightsReturn = flightSearchResult.getFlightsReturn();

			if (flightSearchResult.getFlightsOneWay() != null && !flightsOneWay.isEmpty()) {
				Map<String, Object> firstFlight = flightsOneWay.get(0);
				Booking booking = createBookingFromFlightMap(firstFlight);

				bookingRepository.save(booking);

				if (flightSearchResult.getFlightsReturn() != null && !flightsReturn.isEmpty()) {
					System.out.println("Flights Return: " + flightSearchResult.getFlightsReturn().get(0));
					Map<String, Object> firstReturn = flightsReturn.get(0);
					BookingReturn bookingReturn = createBookingFromFlightMapReturn(firstReturn);
					bookingReturn.setGoingBooking(booking);

					bookingRepositoryReturn.save(bookingReturn);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BookingProcessingException("Não foi possível realizar o cadastro desta passagem."
					+ " Por favor, entre em contato com o administrador do sistema!");
		}
	}

	/**
	 * Creates a Booking object from the flight information extracted from the flight map.
	 *
	 * @param flight The flight map containing the flight information.
	 * @return The created Booking object.
	 */
	private Booking createBookingFromFlightMap(Map<String, Object> flight) {
		Booking booking = new Booking();
		booking.setTypeOfFlight((String) flight.get("Tipo de Voo"));
		booking.setFlightNumber((String) flight.get("N. do Voo"));
		booking.setAirlineName((String) flight.get("Companhia"));
		booking.setAirportOrigin((String) flight.get("Aeroporto de Origem"));
		booking.setFromLocation((String) flight.get("Cidade de Origem"));
		booking.setDepartureTime((String) flight.get("Previsao de Partida"));
		booking.setAirportDestiny((String) flight.get("Aeroporto Destino"));
		booking.setToLocation((String) flight.get("Cidade de Destino"));
		booking.setArrivalTime((String) flight.get("Previsao de Chegada"));
		return booking;
	}
	
	/**
	 * Creates a BookingReturn object from the flight information extracted from the flight map.
	 *
	 * @param flight The flight map containing the flight information.
	 * @return The created BookingReturn object.
	 */
	private BookingReturn createBookingFromFlightMapReturn(Map<String, Object> flight) {
		BookingReturn bookingReturn = new BookingReturn();
		bookingReturn.setTypeOfFlight((String) flight.get("Tipo de Voo"));
		bookingReturn.setFlightNumber((String) flight.get("N. do Voo"));
		bookingReturn.setAirlineName((String) flight.get("Companhia"));
		bookingReturn.setAirportOrigin((String) flight.get("Aeroporto de Origem"));
		bookingReturn.setFromLocation((String) flight.get("Cidade de Origem"));
		bookingReturn.setDepartureTime((String) flight.get("Previsao de Partida"));
		bookingReturn.setAirportDestiny((String) flight.get("Aeroporto Destino"));
		bookingReturn.setToLocation((String) flight.get("Cidade de Destino"));
		bookingReturn.setArrivalTime((String) flight.get("Previsao de Chegada"));
		return bookingReturn;
	}
}