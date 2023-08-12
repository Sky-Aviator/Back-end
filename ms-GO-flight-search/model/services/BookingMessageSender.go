package services

import (
	"github.com/streadway/amqp"
)

type BookingMessageSender struct {
	rabbitChannel *amqp.Channel
}

func NewBookingMessageSender(rabbitChannel *amqp.Channel) *BookingMessageSender {
	return &BookingMessageSender{
		rabbitChannel: rabbitChannel,
	}
}

func (sender *BookingMessageSender) SendBookingMessage() error {
	err := sender.rabbitChannel.Publish(
		"",              // exchange
		"booking_queue", // routing key
		false,           // mandatory
		false,           // immediate
		amqp.Publishing{
			ContentType: "text/plain",
			Body:        []byte("Booking message content"),
		},
	)
	return err
}
