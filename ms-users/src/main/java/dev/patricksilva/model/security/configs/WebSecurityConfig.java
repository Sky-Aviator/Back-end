package dev.patricksilva.model.security.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import dev.patricksilva.model.security.encoders.Encoder;
import dev.patricksilva.model.security.jwt.AuthEntryPointJwt;
import dev.patricksilva.model.security.jwt.AuthTokenFilter;
import dev.patricksilva.model.security.services.SecurityService;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

	@Autowired
    private SecurityService securityService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	@Bean
	AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

    /**
     * Configures the HTTP security settings.
     *
     * @param http The HttpSecurity instance to be configured.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs while configuring the HttpSecurity.
     */
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
    	http.cors().and().csrf().disable();
    	
    	http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	
    	http.authorizeHttpRequests()
    	.requestMatchers("/**")
    	.permitAll().anyRequest().authenticated();
    	
    	http.authenticationProvider(daoAuthenticationProvider());
    	http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    	
    	return http.build();
    }

    /**
     * Configures the DaoAuthenticationProvider.
     *
     * @return The configured DaoAuthenticationProvider.
     */
	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(encoder());
		daoAuthenticationProvider.setUserDetailsService(securityService);
		return daoAuthenticationProvider;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

    /**
     * Creates an instance of the Encoder.
     *
     * @return The Encoder instance.
     */
    @Bean
    protected Encoder encoder() {
        return new Encoder();
    }
}