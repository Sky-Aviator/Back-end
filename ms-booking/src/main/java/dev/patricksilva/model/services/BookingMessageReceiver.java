package dev.patricksilva.model.services;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import com.google.gson.Gson;

import dev.patricksilva.model.entities.Booking;
import dev.patricksilva.model.entities.BookingReturn;
import dev.patricksilva.model.exceptions.BookingProcessingException;
import dev.patricksilva.model.repositories.BookingRepository;
import dev.patricksilva.model.repositories.BookingRepositoryReturn;

@Service
public class BookingMessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(BookingMessageReceiver.class);

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BookingRepositoryReturn bookingRepositoryReturn;

	/**
	 * Receives a booking message from the "booking_queue" RabbitMQ queue and
	 * processes it. The method deserializes the message using Gson and extracts the
	 * flight information from it. If the flight information is available and not
	 * empty, it creates a new Booking object and saves it to the repository. If any
	 * exception occurs during the processing, a BookingProcessingException is
	 * thrown.
	 *
	 * @param message The booking message received from the RabbitMQ queue.
	 * @throws BookingProcessingException If an error occurs while processing the
	 *                                    booking message.
	 */
	@RabbitListener(queues = "booking_queue")
	private void receiveBookingTicket(@Payload String message, @RequestHeader("Authorization") String authorizationHeader) {
		try {
			Gson gson = new Gson();
			FlightSearchResult flightSearchResult = gson.fromJson(message, FlightSearchResult.class);

			logger.info("Passagem Armazenada:");
			logger.info("Flights One Way: " + flightSearchResult.getFlightsOneWay().get(0));

			List<Map<String, Object>> flightsOneWay = flightSearchResult.getFlightsOneWay();
			List<Map<String, Object>> flightsReturn = flightSearchResult.getFlightsReturn();

			if (flightSearchResult.getFlightsOneWay() != null && !flightsOneWay.isEmpty()) {
				Map<String, Object> firstFlight = flightsOneWay.get(0);
				Booking booking = createBookingFromFlightMap(firstFlight, authorizationHeader);

				bookingRepository.save(booking);

				if (flightSearchResult.getFlightsReturn() != null && !flightsReturn.isEmpty()) {
					logger.info("Flights Return: " + flightSearchResult.getFlightsReturn().get(0));
					
					Map<String, Object> firstReturn = flightsReturn.get(0);
					BookingReturn bookingReturn = createBookingFromFlightMapReturn(firstReturn, authorizationHeader);
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
	 * Creates a Booking object from the flight information extracted from the
	 * flight map.
	 *
	 * @param flight The flight map containing the flight information.
	 * @return The created Booking object.
	 */
	private Booking createBookingFromFlightMap(Map<String, Object> flight, String authorizationHeader) {
		Booking booking = new Booking();
		booking.setTypeOfFlight(flight.get("Tipo de Voo").toString());
		booking.setFlightNumber(flight.get("N. do Voo").toString());
		booking.setAirlineName(flight.get("Companhia").toString());
		booking.setAirportOrigin(flight.get("Aeroporto de Origem").toString());
		booking.setFromLocation(flight.get("Cidade de Origem").toString());
		booking.setDepartureTime(flight.get("Previsao de Partida").toString());
		booking.setAirportDestiny(flight.get("Aeroporto Destino").toString());
		booking.setToLocation(flight.get("Cidade de Destino").toString());
		booking.setArrivalTime(flight.get("Previsao de Chegada").toString());

		return booking;
	}

	/**
	 * Creates a BookingReturn object from the flight information extracted from the
	 * flight map.
	 *
	 * @param flight The flight map containing the flight information.
	 * @return The created BookingReturn object.
	 */
	private BookingReturn createBookingFromFlightMapReturn(Map<String, Object> flight, String authorizationHeader) {
		BookingReturn bookingReturn = new BookingReturn();
		bookingReturn.setTypeOfFlight(flight.get("Tipo de Voo").toString());
		bookingReturn.setFlightNumber(flight.get("N. do Voo").toString());
		bookingReturn.setAirlineName(flight.get("Companhia").toString());
		bookingReturn.setAirportOrigin(flight.get("Aeroporto de Origem").toString());
		bookingReturn.setFromLocation(flight.get("Cidade de Origem").toString());
		bookingReturn.setDepartureTime(flight.get("Previsao de Partida").toString());
		bookingReturn.setAirportDestiny(flight.get("Aeroporto Destino").toString());
		bookingReturn.setToLocation(flight.get("Cidade de Destino").toString());
		bookingReturn.setArrivalTime(flight.get("Previsao de Chegada").toString());
	
		return bookingReturn;
	}
}