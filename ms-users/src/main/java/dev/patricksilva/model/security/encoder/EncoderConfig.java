package dev.patricksilva.model.security.encoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import dev.patricksilva.model.security.service.SecurityService;

@Configuration
@EnableWebSecurity
public class EncoderConfig {

	private SecurityService securityService;

	public EncoderConfig(SecurityService securityService) {
		this.securityService = securityService;
	}

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeHttpRequests()
		.requestMatchers("api/v1/users/**")
		.permitAll().and()
		.authenticationProvider(daoAuthenticationProvider());

		return http.build();
	}

	@Bean
	protected DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(encoder());
		daoAuthenticationProvider.setUserDetailsService(securityService);
		return daoAuthenticationProvider;
	}

	@Bean
	protected Encoder encoder() {
		return new Encoder();
	}
}