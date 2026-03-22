package com.gerson.saude.clinica.consulta.validacoes;

import com.gerson.saude.clinica.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsulta {

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAbertura = dataConsulta.getHour() < 7;
        var depoisDoEncerramento = dataConsulta.getHour() > 18;

        if (domingo || antesDaAbertura || depoisDoEncerramento) {
            throw new RuntimeException("Consulta fora do horário de funcionamento da clínica (Seg-Sáb, 07:00 às 19:00)");
        }
    }
}