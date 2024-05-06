package br.com.dio.apiestabelecimentos.service;

import java.util.UUID;

import br.com.dio.apiestabelecimentos.controller.dto.EnderecoDTO;
import br.com.dio.apiestabelecimentos.domain.model.Endereco;

public interface EnderecoService extends CrudService<Endereco, EnderecoDTO, UUID> { }