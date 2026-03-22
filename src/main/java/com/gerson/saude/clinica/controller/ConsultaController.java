package com.gerson.saude.clinica.controller;

import com.gerson.saude.clinica.consulta.DadosCancelamentoConsulta;
import com.gerson.saude.clinica.consulta.service.AgendaDeConsultas;
import com.gerson.saude.clinica.consulta.DadosAgendamentoConsulta;
import com.gerson.saude.clinica.consulta.DadosDetalhamentoConsulta;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda; // Injetamos o nosso Service aqui

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        // Chamamos o método agendar do Service
        agenda.agendar(dados);

        // Por enquanto retornamos 200 OK.
        // No futuro, podemos retornar os detalhes da consulta criada!
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(null, dados.idMedico(), dados.idPaciente(), dados.data()));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agenda.cancelar(dados); // Vamos criar esse método no Service
        return ResponseEntity.noContent().build();
    }
}