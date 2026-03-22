package com.gerson.saude.clinica.infra; // ou .controller

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    // Quando qualquer Service lançar uma RuntimeException, o Spring chamará este método
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity tratarErroRegraDeNegocio(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity tratarErroDuplicidade(DataIntegrityViolationException ex) {
        // Aqui capturamos o erro de UNIQUE KEY do SQL Server
        return ResponseEntity.badRequest().body("Erro: Já existe um cadastro com este CRM, CPF ou E-mail no sistema.");
    }
}
