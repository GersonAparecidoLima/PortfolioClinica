package com.gerson.saude.clinica.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    // No arquivo ConsultaRepository.java
    @Modifying
    @Query("""
    update Consulta c
    set c.motivoCancelamento = :motivo
    where c.medico.id = :idMedico
    and c.data > :dataAtual
    """)
    void cancelarConsultasFuturasDoMedico(Long idMedico, LocalDateTime dataAtual, MotivoCancelamento motivo);


    @Query("""
        select count(c.id) > 0 
        from Consulta c 
        where c.paciente.id = :idPaciente 
        and c.data = :data 
        and c.motivoCancelamento is null 
        """)
    Boolean existsByPacienteIdAndDataAndMotivoCancelamentoIsNull(Long idPaciente, LocalDateTime data);

}