package com.example.bff.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LegadoServiceV2 {
    private static final Logger logger = LoggerFactory.getLogger(LegadoServiceV2.class);
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${legado.base-url}")
    private String legadoBaseUrl;

    @CircuitBreaker(name = "legadoServiceV2", fallbackMethod = "fallbackDadosLentos")
    public String getDadosLentos() {
        ResponseEntity<String> response = restTemplate.getForEntity(legadoBaseUrl + "/legacy/slow-data", String.class);
        return response.getBody();
    }
    public String fallbackDadosLentos(Throwable t) {
        logger.warn("Circuit breaker ativado em getDadosLentos: {}", t.getMessage());
        return "Serviço indisponível (lento)";
    }

    @CircuitBreaker(name = "legadoServiceV2", fallbackMethod = "fallbackDadosInstaveis")
    public String getDadosInstaveis() {
        ResponseEntity<String> response = restTemplate.getForEntity(legadoBaseUrl + "/legacy/unstable-data", String.class);
        return response.getBody();
    }
    public String fallbackDadosInstaveis(Throwable t) {
        logger.warn("Circuit breaker ativado em getDadosInstaveis: {}", t.getMessage());
        return "Serviço indisponível (instável)";
    }

    @CircuitBreaker(name = "legadoServiceV2", fallbackMethod = "fallbackDadosNormais")
    public String getDadosNormais() {
        ResponseEntity<String> response = restTemplate.getForEntity(legadoBaseUrl + "/legacy/normal-data", String.class);
        return response.getBody();
    }
    public String fallbackDadosNormais(Throwable t) {
        logger.warn("Circuit breaker ativado em getDadosNormais: {}", t.getMessage());
        return "Serviço indisponível (normal)";
    }

    @CircuitBreaker(name = "legadoServiceV2", fallbackMethod = "fallbackDadoUnico")
    public ResponseEntity<String> getDadoUnico() {
        return restTemplate.getForEntity(legadoBaseUrl + "/legacy/api/data", String.class);
    }
    public ResponseEntity<String> fallbackDadoUnico(Throwable t) {
        logger.warn("Circuit breaker ativado em getDadoUnico: {}", t.getMessage());
        return ResponseEntity.status(503).body("Serviço indisponível (único)");
    }
}

