package com.example.bff.service;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LegadoServiceV3 {
    private static final Logger logger = LoggerFactory.getLogger(LegadoServiceV3.class);
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${legado.base-url}")
    private String legadoBaseUrl;

    @Retry(name = "legadoServiceV3")
    public String getDadosLentos() {
        ResponseEntity<String> response = restTemplate.getForEntity(legadoBaseUrl + "/legacy/slow-data", String.class);
        return response.getBody();
    }

    @Retry(name = "legadoServiceV3")
    public String getDadosInstaveis() {
        ResponseEntity<String> response = restTemplate.getForEntity(legadoBaseUrl + "/legacy/unstable-data", String.class);
        return response.getBody();
    }

    @Retry(name = "legadoServiceV3")
    public String getDadosNormais() {
        ResponseEntity<String> response = restTemplate.getForEntity(legadoBaseUrl + "/legacy/normal-data", String.class);
        return response.getBody();
    }

    @Retry(name = "legadoServiceV3")
    public ResponseEntity<String> getDadoUnico() {
        return restTemplate.getForEntity(legadoBaseUrl + "/legacy/api/data", String.class);
    }
}
