package com.example.bff.controller;

import com.example.bff.service.LegadoServiceV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2")
public class BffControllerV2 {
    private final LegadoServiceV2 legadoService;

    public BffControllerV2(LegadoServiceV2 legadoService) {
        this.legadoService = legadoService;
    }

    @GetMapping("/slow-data")
    public String dadosLentos() {
        return legadoService.getDadosLentos();
    }

    @GetMapping("/unstable-data")
    public String dadosInstaveis() {
        return legadoService.getDadosInstaveis();
    }

    @GetMapping("/normal-data")
    public String dadosNormais() {
        return legadoService.getDadosNormais();
    }

    @GetMapping("/data")
    public ResponseEntity<String> dadoUnico() {
        return legadoService.getDadoUnico();
    }
}
