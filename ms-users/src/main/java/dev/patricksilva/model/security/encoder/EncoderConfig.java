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
/**
 * Configuration class for encoder and security provider setup.
 */
public class EncoderConfig {

    private SecurityService securityService;

    /**
     * Constructs an EncoderConfig with the specified security service.
     *
     * @param securityService The security service to be used.
     */
    public EncoderConfig(SecurityService securityService) {
        this.securityService = securityService;
    }

    /**
     * Configures the HTTP security settings.
     *
     * @param http The HttpSecurity instance to be configured.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs while configuring the HttpSecurity.
     */
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests()
                .requestMatchers("api/v1/users/**")
                .permitAll().and()
                .authenticationProvider(daoAuthenticationProvider());

        return http.build();
    }

    /**
     * Configures the DaoAuthenticationProvider.
     *
     * @return The configured DaoAuthenticationProvider.
     */
    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(encoder());
        daoAuthenticationProvider.setUserDetailsService(securityService);
        return daoAuthenticationProvider;
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