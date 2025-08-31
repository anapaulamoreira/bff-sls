package com.example.bff.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class LegadoServiceV6 {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${legado.base-url}")
    private String legadoBaseUrl;

    @Async
    public CompletableFuture<String> getDadosLentosAsync() {
        ResponseEntity<String> response = restTemplate.getForEntity(legadoBaseUrl + "/legacy/slow-data", String.class);
        return CompletableFuture.completedFuture(response.getBody());
    }

    @Async
    public CompletableFuture<String> getDadosInstaveisAsync() {
        ResponseEntity<String> response = restTemplate.getForEntity(legadoBaseUrl + "/legacy/unstable-data", String.class);
        return CompletableFuture.completedFuture(response.getBody());
    }

    @Async
    public CompletableFuture<String> getDadosNormaisAsync() {
        ResponseEntity<String> response = restTemplate.getForEntity(legadoBaseUrl + "/legacy/normal-data", String.class);
        return CompletableFuture.completedFuture(response.getBody());
    }

    @Async
    public CompletableFuture<ResponseEntity<String>> getDadoUnicoAsync() {
        ResponseEntity<String> response = restTemplate.getForEntity(legadoBaseUrl + "/legacy/api/data", String.class);
        return CompletableFuture.completedFuture(response);
    }
}

