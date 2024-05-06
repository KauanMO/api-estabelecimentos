package br.com.dio.apiestabelecimentos.controller.dto;

import br.com.dio.apiestabelecimentos.domain.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(
        @NotNull @NotBlank String logradouro,
        @NotNull @NotBlank String numero,
        String complemento,
        String referencia) {
    public EnderecoDTO(Endereco endereco) {
        this(endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(), endereco.getReferencia());
    }
}