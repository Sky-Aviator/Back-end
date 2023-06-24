package dev.patricksilva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:/home/anon/Desktop/ms-flight-search.properties")
public class MsFlightSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsFlightSearchApplication.class, args);
	}
}