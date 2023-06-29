package dev.patricksilva.model.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookingMessageSender {
	private final RabbitTemplate rabbitTemplate;

	public BookingMessageSender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendBookingMessage() {
		rabbitTemplate.convertAndSend("booking_queue");
	}
}