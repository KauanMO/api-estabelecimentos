package br.com.dio.apiestabelecimentos.service;

import java.util.List;

import br.com.dio.apiestabelecimentos.service.exception.ObjetoRelacionadoNaoEncontrado;
import br.com.dio.apiestabelecimentos.service.exception.SemConteudoException;

public interface CrudService<T, DTO, ID> {
    List<T> findAll() throws SemConteudoException;

    T findById(ID id) throws SemConteudoException;

    T create(DTO dto) throws ObjetoRelacionadoNaoEncontrado;

    T update(ID id, DTO dto) throws SemConteudoException;

    void delete(ID id) throws SemConteudoException;
}