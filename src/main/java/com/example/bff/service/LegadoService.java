package com.example.bff.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LegadoService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${legado.base-url}")
    private String legadoBaseUrl;

    public String getDadosLentos() {
        ResponseEntity<String> response = restTemplate.getForEntity(legadoBaseUrl + "/legacy/slow-data", String.class);
        return response.getBody();
    }

    public String getDadosInstaveis() {
        ResponseEntity<String> response = restTemplate.getForEntity(legadoBaseUrl + "/legacy/unstable-data", String.class);
        return response.getBody();
    }

    public String getDadosNormais() {
        ResponseEntity<String> response = restTemplate.getForEntity(legadoBaseUrl + "/legacy/normal-data", String.class);
        return response.getBody();
    }
}
