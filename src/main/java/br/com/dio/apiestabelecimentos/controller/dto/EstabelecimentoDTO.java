package br.com.dio.apiestabelecimentos.controller.dto;

import br.com.dio.apiestabelecimentos.domain.model.Endereco;
import br.com.dio.apiestabelecimentos.domain.model.Estabelecimento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EstabelecimentoDTO(@NotNull @NotBlank String nome,
        String horaAbertura,
        String horaFechamento,
        @NotNull Endereco endereco) {
    public EstabelecimentoDTO(Estabelecimento estabelecimento) {
        this(estabelecimento.getNome(), estabelecimento.getHoraAbertura(), estabelecimento.getHoraFechamento(),
                estabelecimento.getEndereco());
    }
}