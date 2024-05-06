package br.com.dio.apiestabelecimentos.service;

import java.util.UUID;

import br.com.dio.apiestabelecimentos.controller.dto.AvaliacaoDTO;
import br.com.dio.apiestabelecimentos.domain.model.Avaliacao;

public interface AvaliacaoService extends CrudService<Avaliacao, AvaliacaoDTO, UUID> { }