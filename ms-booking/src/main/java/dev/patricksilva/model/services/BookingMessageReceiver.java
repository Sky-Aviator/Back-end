package dev.patricksilva.model.services;

import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import dev.patricksilva.model.entities.Booking;
import dev.patricksilva.model.exceptions.BookingProcessingException;
import dev.patricksilva.model.repositories.BookingRepository;

@Service
public class BookingMessageReceiver {

	@Autowired
	private BookingRepository bookingRepository;

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
		Gson gson = new Gson();
		FlightSearchResult flightSearchResult = gson.fromJson(message, FlightSearchResult.class);

		System.out.println("Passagem Armazenada:");
		System.out.println("Flights One Way: " + flightSearchResult.getFlightsOneWay());
		try {
			if (!flightSearchResult.getFlightsOneWay().isEmpty()) {
				List<Map<String, Object>> flightsOneWay = flightSearchResult.getFlightsOneWay();
				Map<String, Object> firstFlight = flightsOneWay.get(0);

				Booking booking = new Booking();
				booking.setTypeOfFlight((String) firstFlight.get("Tipo de Voo"));
				booking.setFlightNumber((String) firstFlight.get("N. de Voo"));
				booking.setAirlineName((String) firstFlight.get("Companhia"));
				booking.setAirportOrigin((String) firstFlight.get("Aeroporto de Origem"));
				booking.setFromLocation((String) firstFlight.get("Cidade de Origem"));
				booking.setDepartureTime((String) firstFlight.get("Previsao de Partida"));
				booking.setAirportDestiny((String) firstFlight.get("Aeroporto Destino"));
				booking.setToLocation((String) firstFlight.get("Cidade de Destino"));
				booking.setArrivalTime((String) firstFlight.get("Previsao de Chegada"));
				bookingRepository.save(booking);
			}
		} catch (Exception e) {
			e.getStackTrace();
			throw new BookingProcessingException("Não foi possivel realizar o cadastro desta passagem."
					+ " Por favor, entre em contato com o administrador do sistema!");
		}
	}
}