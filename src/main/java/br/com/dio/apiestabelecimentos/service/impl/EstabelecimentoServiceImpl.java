package br.com.dio.apiestabelecimentos.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.dio.apiestabelecimentos.controller.dto.EstabelecimentoDTO;
import br.com.dio.apiestabelecimentos.domain.model.Estabelecimento;
import br.com.dio.apiestabelecimentos.domain.repository.EnderecoRepository;
import br.com.dio.apiestabelecimentos.domain.repository.EstabelecimentoRepository;
import br.com.dio.apiestabelecimentos.service.EstabelecimentoService;
import br.com.dio.apiestabelecimentos.service.exception.ObjetoRelacionadoNaoEncontrado;
import br.com.dio.apiestabelecimentos.service.exception.SemConteudoException;

@Service
public class EstabelecimentoServiceImpl implements EstabelecimentoService {
    private final EstabelecimentoRepository repository;
    private final EnderecoRepository enderecoRepository;

    public EstabelecimentoServiceImpl(EstabelecimentoRepository repository, EnderecoRepository enderecoRepository) {
        this.repository = repository;
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public List<Estabelecimento> findAll() throws SemConteudoException {
        List<Estabelecimento> estabelecimentosEncontrados = repository.findAll();

        if (estabelecimentosEncontrados.isEmpty()) {
            throw new SemConteudoException();
        }

        return estabelecimentosEncontrados;
    }

    @Override
    public Estabelecimento findById(UUID id) throws SemConteudoException {
        return repository.findById(id).orElseThrow(SemConteudoException::new);
    }

    @Override
    public Estabelecimento create(EstabelecimentoDTO dto) throws ObjetoRelacionadoNaoEncontrado{
        if(!enderecoRepository.existsById(dto.endereco().getId())) {
            throw new ObjetoRelacionadoNaoEncontrado();
        }

        Estabelecimento estabelecimentoCriado = new Estabelecimento();

        BeanUtils.copyProperties(dto, estabelecimentoCriado);

        return repository.save(estabelecimentoCriado);
    }

    @Override
    public Estabelecimento update(UUID id, EstabelecimentoDTO dto) throws SemConteudoException {
        Estabelecimento estabelecimentoEncontrado = repository.findById(id).orElseThrow(SemConteudoException::new);

        BeanUtils.copyProperties(dto, estabelecimentoEncontrado);

        return estabelecimentoEncontrado;
    }

    @Override
    public void delete(UUID id) throws SemConteudoException {
        if (!repository.existsById(id)) {
            throw new SemConteudoException();
        }

        repository.deleteById(id);
    }
}