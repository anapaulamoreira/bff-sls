package com.example.bff.controller;

import com.example.bff.service.LegadoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bff")
public class BffController {

    private final LegadoService legadoService;

    public BffController(LegadoService legadoService) {
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
}
