package com.gerson.saude.clinica.controller;

import com.gerson.saude.clinica.consulta.ConsultaRepository;
import com.gerson.saude.clinica.consulta.MotivoCancelamento;
import com.gerson.saude.clinica.medico.*;
import com.gerson.saude.clinica.paciente.DadosListagemPaciente;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        // 1. Busca o médico no banco pelo ID que veio no DTO
        var medico = repository.getReferenceById(dados.id());

        // 2. Executa a regra de negócio de atualização dentro da Entidade
        medico.atualizarInformacoes(dados);

        // 3. Retorna 200 OK e o corpo com os dados atualizados usando o DTO de detalhamento
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @PutMapping("/{id}/reativar")
    @Transactional
    public ResponseEntity reativar(@PathVariable Long id) {
        // 1. Busca o médico pelo ID
        var medico = repository.getReferenceById(id);

        // 2. Chama o método de negócio que você já criou na classe Medico.java
        medico.reativar();

        // 3. Retorna 200 OK com os detalhes do médico reativado
        // Assim o seu Front-end já recebe o objeto com "ativo: true"
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }



    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);

        // 1. Inativa o médico (Ativo = false)
        medico.excluir();

        // 2. REGRA DE NEGÓCIO: Cancela consultas futuras (Muito bom!)
        consultaRepository.cancelarConsultasFuturasDoMedico(
                id,
                LocalDateTime.now(),
                MotivoCancelamento.MEDICO_INATIVADO
        );

        // Retorna 204 No Content (Padrão para DELETE com sucesso)
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        // Certifique-se de que está devolvendo o novo DTO que você criou!
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

}
