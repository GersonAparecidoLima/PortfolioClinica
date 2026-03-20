package com.gerson.saude.clinica.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {

    // O Spring gera automaticamente: SELECT * FROM pacientes WHERE ativo = 1
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);
}
