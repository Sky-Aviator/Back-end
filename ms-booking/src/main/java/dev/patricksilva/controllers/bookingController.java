package dev.patricksilva.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class bookingController {

	@GetMapping
	public String hello() {
		return "Hello, ms-booking";
	}
	
}
