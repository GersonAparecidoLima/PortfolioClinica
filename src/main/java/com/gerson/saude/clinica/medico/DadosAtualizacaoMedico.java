package com.gerson.saude.clinica.medico;



import com.gerson.saude.clinica.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        DadosEndereco endereco,
        String telefone){


}

