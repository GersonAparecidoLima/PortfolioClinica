package com.gerson.saude.clinica.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future // Garante que a consulta seja marcada para o futuro
        LocalDateTime data
) {
}
