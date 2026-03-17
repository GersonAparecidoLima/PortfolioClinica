package com.gerson.saude.clinica.medico;

import com.gerson.saude.clinica.endereco.DadosEndereco;

public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {

}
