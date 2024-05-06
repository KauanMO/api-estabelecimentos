package br.com.dio.apiestabelecimentos.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.dio.apiestabelecimentos.controller.dto.EnderecoDTO;
import br.com.dio.apiestabelecimentos.domain.model.Endereco;
import br.com.dio.apiestabelecimentos.domain.repository.EnderecoRepository;
import br.com.dio.apiestabelecimentos.service.EnderecoService;
import br.com.dio.apiestabelecimentos.service.exception.SemConteudoException;

@Service
public class EnderecoServiceImpl implements EnderecoService {
    private final EnderecoRepository repository;

    public EnderecoServiceImpl(EnderecoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Endereco> findAll() throws SemConteudoException {
        List<Endereco> enderecosEncontrados = repository.findAll();

        if (enderecosEncontrados.isEmpty()) {
            throw new SemConteudoException();
        }

        return enderecosEncontrados;
    }

    @Override
    public Endereco findById(UUID id) throws SemConteudoException {
        return repository.findById(id).orElseThrow(SemConteudoException::new);
    }

    @Override
    public Endereco create(EnderecoDTO dto) {
        Endereco enderecoCriado = new Endereco();

        BeanUtils.copyProperties(dto, enderecoCriado);

        return repository.save(enderecoCriado);
    }

    @Override
    public Endereco update(UUID id, EnderecoDTO dto) throws SemConteudoException {
        Endereco enderecoEncontrado = repository.findById(id).orElseThrow(SemConteudoException::new);

        BeanUtils.copyProperties(dto, enderecoEncontrado);

        return repository.save(enderecoEncontrado);
    }

    @Override
    public void delete(UUID id) throws SemConteudoException {
        if (!repository.existsById(id)) {
            throw new SemConteudoException();
        }

        repository.deleteById(id);
    }
}