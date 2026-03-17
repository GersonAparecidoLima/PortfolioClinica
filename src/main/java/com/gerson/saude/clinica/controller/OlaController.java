package com.gerson.saude.clinica.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teste")
public class OlaController {

    @GetMapping
    public String saudar() {
        return "API da Clínica rodando com sucesso! Pronta para o React...";
    }

}
