package com.gerson.saude.clinica.consulta.service;

import com.gerson.saude.clinica.consulta.Consulta;
import com.gerson.saude.clinica.consulta.ConsultaRepository;
import com.gerson.saude.clinica.consulta.DadosAgendamentoConsulta;
import com.gerson.saude.clinica.consulta.DadosCancelamentoConsulta;
import com.gerson.saude.clinica.consulta.validacoes.ValidadorAgendamentoConsulta;
import com.gerson.saude.clinica.medico.MedicoRepository;
import com.gerson.saude.clinica.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    // O Spring vai buscar TODAS as classes que implementam a Interface ValidadorAgendamentoConsulta
    @Autowired
    private List<ValidadorAgendamentoConsulta> validadores;

    public void agendar(DadosAgendamentoConsulta dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new RuntimeException("Id do paciente informado não existe!");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new RuntimeException("Id do médico informado não existe!");
        }

        // --- A MÁGICA ACONTECE AQUI ---
        // Percorre todos os validadores da pasta /validacoes e executa um por um
        validadores.forEach(v -> v.validar(dados));
        // ------------------------------

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = medicoRepository.getReferenceById(dados.idMedico());

        var consulta = new Consulta(null, medico, paciente, dados.data());
        consultaRepository.save(consulta);
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new RuntimeException("Id da consulta informado não existe!");
        }

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        var agora = java.time.LocalDateTime.now();
        var diferencaEmHoras = java.time.Duration.between(agora, consulta.getData()).toHours();

        // REGRA DE NEGÓCIO: Antecedência mínima de 24 horas para cancelar
        if (diferencaEmHoras < 24) {
            throw new RuntimeException("Consulta somente pode ser cancelada com antecedência mínima de 24 horas!");
        }

        consulta.cancelar(dados.motivo()); // Vamos criar esse método na Entidade agora
    }

}