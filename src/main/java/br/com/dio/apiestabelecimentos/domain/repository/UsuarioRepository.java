package br.com.dio.apiestabelecimentos.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dio.apiestabelecimentos.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> { }