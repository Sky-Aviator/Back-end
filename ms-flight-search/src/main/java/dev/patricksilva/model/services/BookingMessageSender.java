package dev.patricksilva.model.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingMessageSender {
	private final RabbitTemplate rabbitTemplate;

	@Autowired
	public BookingMessageSender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendBookingMessage() {
		rabbitTemplate.convertAndSend("booking_queue");
	}
}