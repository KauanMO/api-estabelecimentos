package br.com.dio.apiestabelecimentos.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dio.apiestabelecimentos.controller.dto.UsuarioDTO;
import br.com.dio.apiestabelecimentos.domain.model.Usuario;
import br.com.dio.apiestabelecimentos.service.UsuarioService;
import br.com.dio.apiestabelecimentos.service.exception.ObjetoRelacionadoNaoEncontrado;
import br.com.dio.apiestabelecimentos.service.exception.SemConteudoException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // POST
    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody @Valid UsuarioDTO dto) throws ObjetoRelacionadoNaoEncontrado {
        Usuario usuarioCriado = service.create(dto);

        UsuarioDTO dtoCriado = new UsuarioDTO(usuarioCriado);

        return ResponseEntity.status(201).body(dtoCriado);
    }

    // GET
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscarTodosUsuarios() {
        try {
            List<Usuario> usuariosEncontrados = service.findAll();

            List<UsuarioDTO> dtosEncontradas = usuariosEncontrados.stream()
                    .map(usuario -> new UsuarioDTO(usuario))
                    .toList();

            return ResponseEntity.ok().body(dtosEncontradas);
        } catch (SemConteudoException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable UUID id) {
        try {
            Usuario usuarioEncontrado = service.findById(id);

            return ResponseEntity.ok().body(new UsuarioDTO(usuarioEncontrado));
        } catch (SemConteudoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable UUID id, @RequestBody @Valid UsuarioDTO dto) {
        try {
            Usuario usuarioEncontrado = service.update(id, dto);

            return ResponseEntity.ok().body(new UsuarioDTO(usuarioEncontrado));
        } catch (SemConteudoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable UUID id) {
        try {
            service.delete(id);

            return ResponseEntity.noContent().build();
        } catch (SemConteudoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}