package dev.patricksilva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:/home/anon/Desktop/ms-booking.properties")
public class MsBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBookingApplication.class, args);
	}
}