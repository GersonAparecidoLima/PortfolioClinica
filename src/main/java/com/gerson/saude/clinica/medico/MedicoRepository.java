package com.gerson.saude.clinica.medico;

import com.gerson.saude.clinica.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    // Esta Query ensina o Spring a buscar o status ativo no banco
    @Query("""
            select m.ativo
            from Medico m
            where m.id = :id
            """)
    Boolean findAtivoById(Long id);

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}

