package com.gerson.saude.clinica.controller;

import com.gerson.saude.clinica.medico.DadosAtualizacaoMedico;
import com.gerson.saude.clinica.medico.DadosCadastroMedico;
import com.gerson.saude.clinica.medico.Medico;
import com.gerson.saude.clinica.medico.MedicoRepository;
import com.gerson.saude.clinica.paciente.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")

public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroPaciente dados) {

        //System.out.println(dados);
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListagemPaciente> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
    }
    //public List<DadosListagemPaciente> listar() {
        //return repository.findAll().stream().map(DadosListagemPaciente::new).toList();
    //}

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody DadosAtualizacaoPaciente dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
    }

    @PutMapping("/{id}/reativar")
    @Transactional
    public ResponseEntity reativar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.reativar(); // Vamos criar este método na Entidade agora

        return ResponseEntity.noContent().build(); // Retorna 204 No Content (Sucesso sem corpo)
    }

}