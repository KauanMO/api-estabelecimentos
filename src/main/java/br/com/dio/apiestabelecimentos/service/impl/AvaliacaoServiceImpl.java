package br.com.dio.apiestabelecimentos.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.dio.apiestabelecimentos.controller.dto.AvaliacaoDTO;
import br.com.dio.apiestabelecimentos.domain.model.Avaliacao;
import br.com.dio.apiestabelecimentos.domain.repository.AvaliacaoRepository;
import br.com.dio.apiestabelecimentos.domain.repository.EstabelecimentoRepository;
import br.com.dio.apiestabelecimentos.domain.repository.UsuarioRepository;
import br.com.dio.apiestabelecimentos.service.AvaliacaoService;
import br.com.dio.apiestabelecimentos.service.exception.ObjetoRelacionadoNaoEncontrado;
import br.com.dio.apiestabelecimentos.service.exception.SemConteudoException;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {
    private final AvaliacaoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;

    public AvaliacaoServiceImpl(AvaliacaoRepository repository, UsuarioRepository usuarioRepository,
            EstabelecimentoRepository estabelecimentoRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    @Override
    public List<Avaliacao> findAll() throws SemConteudoException {
        List<Avaliacao> avaliacoesEncontradas = repository.findAll();

        if (avaliacoesEncontradas.isEmpty()) {
            throw new SemConteudoException();
        }

        return avaliacoesEncontradas;
    }

    @Override
    public Avaliacao findById(UUID id) throws SemConteudoException {
        return repository.findById(id).orElseThrow(SemConteudoException::new);
    }

    @Override
    public Avaliacao create(AvaliacaoDTO dto) throws ObjetoRelacionadoNaoEncontrado {
        if (!usuarioRepository.existsById(dto.usuario().getId())
                || !estabelecimentoRepository.existsById(dto.estabelecimento().getId())) {
            throw new ObjetoRelacionadoNaoEncontrado();
        }

        Avaliacao avaliacaoCriada = new Avaliacao();

        BeanUtils.copyProperties(dto, avaliacaoCriada);

        return repository.save(avaliacaoCriada);
    }

    @Override
    public Avaliacao update(UUID id, AvaliacaoDTO dto) throws SemConteudoException {
        Avaliacao avaliacaoEncontrada = repository.findById(id).orElseThrow(SemConteudoException::new);

        BeanUtils.copyProperties(dto, avaliacaoEncontrada);

        return repository.save(avaliacaoEncontrada);
    }

    @Override
    public void delete(UUID id) throws SemConteudoException {
        if (!repository.existsById(id)) {
            throw new SemConteudoException();
        }

        repository.deleteById(id);
    }
}