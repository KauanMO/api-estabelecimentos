package br.com.dio.apiestabelecimentos.service;

import java.util.UUID;

import br.com.dio.apiestabelecimentos.controller.dto.UsuarioDTO;
import br.com.dio.apiestabelecimentos.domain.model.Usuario;

public interface UsuarioService extends CrudService<Usuario, UsuarioDTO, UUID> { }