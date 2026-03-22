package com.gerson.saude.clinica.consulta.validacoes;

import com.gerson.saude.clinica.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.Duration;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoConsulta {

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new RuntimeException("Consulta deve ser agendada com antecedência mínima de 30 minutos!");
        }
    }
}