package com.gerson.saude.clinica.consulta.service;

import com.gerson.saude.clinica.consulta.Consulta;
import com.gerson.saude.clinica.consulta.ConsultaRepository;
import com.gerson.saude.clinica.consulta.DadosAgendamentoConsulta;
import com.gerson.saude.clinica.medico.MedicoRepository;
import com.gerson.saude.clinica.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DadosAgendamentoConsulta dados) {
        // 1. Validar se o paciente existe
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new RuntimeException("Id do paciente informado não existe!");
        }

        // 2. Validar se o médico existe (se foi informado)
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new RuntimeException("Id do médico informado não existe!");
        }

        // --- NOVA REGRA: VALIDAR SE O MÉDICO ESTÁ ATIVO ---
        if (dados.idMedico() != null) {
            var medicoEstaAtivo = medicoRepository.findAtivoById(dados.idMedico());
            if (!medicoEstaAtivo) {
                throw new RuntimeException("Consulta não pode ser agendada com médico inativo!");
            }
        }

        // 3. Buscar as entidades no banco
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = medicoRepository.getReferenceById(dados.idMedico());

        // 4. Criar a nova consulta
        var consulta = new Consulta(null, medico, paciente, dados.data());

        // 5. Salvar!
        consultaRepository.save(consulta);
    }
}
