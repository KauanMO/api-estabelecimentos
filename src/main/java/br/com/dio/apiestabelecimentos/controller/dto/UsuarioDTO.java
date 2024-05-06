package br.com.dio.apiestabelecimentos.controller.dto;

import java.time.LocalDate;

import br.com.dio.apiestabelecimentos.domain.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record UsuarioDTO(
        @NotNull @NotBlank String nome,
        @NotNull @Past LocalDate dataNascimento) {
    public UsuarioDTO(Usuario usuario) {
        this(usuario.getNome(), usuario.getDataNascimento());
    }
}