package dev.patricksilva.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import dev.patricksilva.model.enums.InformationType;
import dev.patricksilva.model.services.LoginService;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "MS-USERS - LOGIN", description = "Endpoints Management.")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoginController {

    @Autowired
    private Environment env;

    @Autowired
    private LoginService loginService;

    /**
     * Retrieves user board information from the API.
     * This endpoint requires the user to have the 'ROLE_USER' role to access it.
     *
     * @return A ResponseEntity containing a String with the message "User board" and an HTTP status of 200 (OK).
     */
    @GetMapping("/api/v1/userboard")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> userBoard(){
    	return ResponseEntity.ok("User board");
    }
    
    /**
     * Get the User's information based on the provided type.
     * 
     * @param headers
     * @return The User's information.
     */
    @GetMapping("current-user/{type}")
    public ResponseEntity<String> getUserInformationByToken(@RequestHeader HttpHeaders headers, @PathVariable("type") InformationType type) {
        String user = loginService.getUserInformationByToken(headers, env.getProperty("sky-aviator.app.jwtSecret"), type);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}