package com.gerson.saude.clinica.medico;

import com.gerson.saude.clinica.endereco.DadosEndereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {

}
