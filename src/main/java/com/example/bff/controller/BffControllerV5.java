package com.example.bff.controller;

import com.example.bff.service.LegadoServiceV5;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v5")
public class BffControllerV5 {
    private final LegadoServiceV5 legadoService;

    public BffControllerV5(LegadoServiceV5 legadoService) {
        this.legadoService = legadoService;
    }

    @GetMapping("/parallel")
    public List<String> dadosParalelos() {
        return legadoService.getDadosParalelos();
    }
}

