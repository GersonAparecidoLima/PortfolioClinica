package com.gerson.saude.clinica.controller;

import com.gerson.saude.clinica.medico.DadosCadastroMedico;
import com.gerson.saude.clinica.medico.Medico;
import com.gerson.saude.clinica.medico.MedicoRepository;
import com.gerson.saude.clinica.paciente.DadosCadastroPaciente;
import com.gerson.saude.clinica.paciente.DadosListagemPaciente;
import com.gerson.saude.clinica.paciente.Paciente;
import com.gerson.saude.clinica.paciente.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")

public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroPaciente dados){

        //System.out.println(dados);
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public List<DadosListagemPaciente> listar() {
        return repository.findAll().stream().map(DadosListagemPaciente::new).toList();
    }

}
