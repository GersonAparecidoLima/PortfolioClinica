package com.gerson.saude.clinica.paciente;

import com.gerson.saude.clinica.endereco.DadosEndereco;

public record DadosCadastroPaciente(String nome, String email,String telefone,String cpf,DadosEndereco endereco) {
}
