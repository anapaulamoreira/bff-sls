package com.example.bff.controller;

import com.example.bff.service.LegadoServiceV6;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/v6")
public class BffControllerV6 {
    private final LegadoServiceV6 legadoService;

    public BffControllerV6(LegadoServiceV6 legadoService) {
        this.legadoService = legadoService;
    }

    @GetMapping("/slow-data-async")
    public CompletableFuture<String> dadosLentosAsync() {
        return legadoService.getDadosLentosAsync();
    }

    @GetMapping("/unstable-data-async")
    public CompletableFuture<String> dadosInstaveisAsync() {
        return legadoService.getDadosInstaveisAsync();
    }

    @GetMapping("/normal-data-async")
    public CompletableFuture<String> dadosNormaisAsync() {
        return legadoService.getDadosNormaisAsync();
    }

    @GetMapping("/data-async")
    public CompletableFuture<ResponseEntity<String>> dadoUnicoAsync() {
        return legadoService.getDadoUnicoAsync();
    }
}

