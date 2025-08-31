package com.example.bff.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class LegadoServiceV5 {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${legado.base-url}")
    private String legadoBaseUrl;
    private final ExecutorService executor = Executors.newFixedThreadPool(10); // Ajuste conforme necessidade

    public List<String> getDadosParalelos() {
        List<String> endpoints = List.of(
            "/legacy/slow-data",
            "/legacy/unstable-data",
            "/legacy/normal-data",
            "/legacy/api/data"
        );
        List<CompletableFuture<String>> futures = endpoints.stream()
            .map(endpoint -> CompletableFuture.supplyAsync(() -> {
                ResponseEntity<String> response = restTemplate.getForEntity(legadoBaseUrl + endpoint, String.class);
                return response.getBody();
            }, executor))
            .collect(Collectors.toList());
        return futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }
}

