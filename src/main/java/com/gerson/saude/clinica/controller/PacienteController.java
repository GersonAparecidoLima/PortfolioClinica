package com.gerson.saude.clinica.controller;

import com.gerson.saude.clinica.medico.DadosCadastroMedico;
import com.gerson.saude.clinica.paciente.DadosCadastroPaciente;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("paciente")
public class PacienteController {

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroPaciente dados){
        System.out.println(dados);
    }

}
