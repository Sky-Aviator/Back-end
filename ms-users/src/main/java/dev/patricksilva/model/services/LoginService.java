package dev.patricksilva.model.services;

import org.springframework.http.HttpHeaders;

import dev.patricksilva.model.enums.InformationType;

public interface LoginService {
    public String getUserInformationByToken(HttpHeaders headers, String secret, InformationType type);
}