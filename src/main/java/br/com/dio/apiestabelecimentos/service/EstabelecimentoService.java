package br.com.dio.apiestabelecimentos.service;

import java.util.UUID;

import br.com.dio.apiestabelecimentos.controller.dto.EstabelecimentoDTO;
import br.com.dio.apiestabelecimentos.domain.model.Estabelecimento;

public interface EstabelecimentoService extends CrudService<Estabelecimento, EstabelecimentoDTO, UUID> { }