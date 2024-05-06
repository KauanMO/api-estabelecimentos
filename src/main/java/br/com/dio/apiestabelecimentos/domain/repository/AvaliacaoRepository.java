package br.com.dio.apiestabelecimentos.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dio.apiestabelecimentos.domain.model.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, UUID> { }