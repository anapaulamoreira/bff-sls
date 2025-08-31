package com.example.bff.service;

import io.github.resilience4j.cache.annotation.CacheResult;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LegadoServiceV4 {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${legado.base-url}")
    private String legadoBaseUrl;

    @CacheResult(cacheName = "dadosLentosCache")
    @Retry(name = "legadoServiceV4")
    public String getDadosLentos() {
        ResponseEntity<String> response = restTemplate.getForEntity(legadoBaseUrl + "/legacy/slow-data", String.class);
        return response.getBody();
    }

    @CacheResult(cacheName = "dadosInstaveisCache")
    @Retry(name = "legadoServiceV4")
    public String getDadosInstaveis() {
        ResponseEntity<String> response = restTemplate.getForEntity(legadoBaseUrl + "/legacy/unstable-data", String.class);
        return response.getBody();
    }

    @CacheResult(cacheName = "dadosNormaisCache")
    @Retry(name = "legadoServiceV4")
    public String getDadosNormais() {
        ResponseEntity<String> response = restTemplate.getForEntity(legadoBaseUrl + "/legacy/normal-data", String.class);
        return response.getBody();
    }

    @CacheResult(cacheName = "dadoUnicoCache")
    @Retry(name = "legadoServiceV4")
    public ResponseEntity<String> getDadoUnico() {
        return restTemplate.getForEntity(legadoBaseUrl + "/legacy/api/data", String.class);
    }
}

