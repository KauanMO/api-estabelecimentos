package br.com.dio.apiestabelecimentos.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.dio.apiestabelecimentos.controller.dto.UsuarioDTO;
import br.com.dio.apiestabelecimentos.domain.model.Usuario;
import br.com.dio.apiestabelecimentos.domain.repository.UsuarioRepository;
import br.com.dio.apiestabelecimentos.service.UsuarioService;
import br.com.dio.apiestabelecimentos.service.exception.SemConteudoException;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Usuario> findAll() throws SemConteudoException {
        List<Usuario> usuariosEncontrados = repository.findAll();

        if (usuariosEncontrados.isEmpty()) {
            throw new SemConteudoException();
        }

        return usuariosEncontrados;
    }

    @Override
    public Usuario findById(UUID id) throws SemConteudoException {
        return repository.findById(id).orElseThrow(SemConteudoException::new);
    }

    @Override
    public Usuario create(UsuarioDTO dto) {
        Usuario usuarioCriado = new Usuario();

        BeanUtils.copyProperties(dto, usuarioCriado);

        return repository.save(usuarioCriado);
    }

    @Override
    public Usuario update(UUID id, UsuarioDTO dto) throws SemConteudoException {
        Usuario usuarioEncontrado = repository.findById(id).orElseThrow(SemConteudoException::new);

        BeanUtils.copyProperties(dto, usuarioEncontrado);

        return repository.save(usuarioEncontrado);
    }

    @Override
    public void delete(UUID id) throws SemConteudoException {
        if (!repository.existsById(id)) {
            throw new SemConteudoException();
        }

        repository.deleteById(id);
    }
}