package br.com.dio.apiestabelecimentos.controller.dto;

import java.time.LocalDate;

import br.com.dio.apiestabelecimentos.domain.model.Avaliacao;
import br.com.dio.apiestabelecimentos.domain.model.Estabelecimento;
import br.com.dio.apiestabelecimentos.domain.model.Usuario;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;

public record AvaliacaoDTO(
                @Min(0) @Max(10) Integer nota,
                @Past LocalDate dataVisita,
                LocalDate dataAvaliacao,
                String comentario,
                Usuario usuario,
                Estabelecimento estabelecimento) {
        public AvaliacaoDTO(Avaliacao avaliacao) {
                this(avaliacao.getNota(), avaliacao.getDataVisita(), avaliacao.getDataAvaliacao(),
                                avaliacao.getComentario(), avaliacao.getUsuario(), avaliacao.getEstabelecimento());
        }
}