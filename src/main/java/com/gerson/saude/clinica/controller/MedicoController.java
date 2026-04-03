package com.gerson.saude.clinica.controller;

import com.gerson.saude.clinica.consulta.ConsultaRepository;
import com.gerson.saude.clinica.consulta.MotivoCancelamento;
import com.gerson.saude.clinica.medico.*;
import com.gerson.saude.clinica.paciente.DadosListagemPaciente;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@CrossOrigin(origins = "http://localhost:5173") // Libera o React
@RequestMapping("/api/medicos")
@RestController
//@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @Autowired // <--- Adicione isto
    private ConsultaRepository consultaRepository; // <--- E isto

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroMedico dados){

        //System.out.println(dados);
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody DadosAtualizacaoMedico dados) {
        var medico  = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir(); // Seta ativo = 0

        // REGRA DE NEGÓCIO: Cancela todas as consultas futuras deste médico
        // Busca as consultas e marca como canceladas por inatividade do médico
        consultaRepository.cancelarConsultasFuturasDoMedico(id, LocalDateTime.now(), MotivoCancelamento.MEDICO_INATIVADO);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/reativar")
    @Transactional
    public ResponseEntity reativar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.reativar(); // Vamos criar este método na Entidade agora

        return ResponseEntity.noContent().build(); // Retorna 204 No Content (Sucesso sem corpo)
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        // Certifique-se de que está devolvendo o novo DTO que você criou!
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

}
