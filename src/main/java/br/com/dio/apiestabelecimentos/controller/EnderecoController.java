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

import br.com.dio.apiestabelecimentos.controller.dto.EnderecoDTO;
import br.com.dio.apiestabelecimentos.domain.model.Endereco;
import br.com.dio.apiestabelecimentos.service.EnderecoService;
import br.com.dio.apiestabelecimentos.service.exception.ObjetoRelacionadoNaoEncontrado;
import br.com.dio.apiestabelecimentos.service.exception.SemConteudoException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    // POST
    @PostMapping
    public ResponseEntity<EnderecoDTO> criarEndereco(@RequestBody @Valid EnderecoDTO dto) throws ObjetoRelacionadoNaoEncontrado {
        Endereco enderecoCriado = service.create(dto);

        EnderecoDTO dtoCriado = new EnderecoDTO(enderecoCriado);

        return ResponseEntity.status(201).body(dtoCriado);
    }

    // GET
    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> buscarTodosEnderecos() {
        try {
            List<Endereco> enderecosEncontrados = service.findAll();

            List<EnderecoDTO> dtosEncontradas = enderecosEncontrados.stream()
                    .map(endereco -> new EnderecoDTO(endereco))
                    .toList();

            return ResponseEntity.ok().body(dtosEncontradas);
        } catch (SemConteudoException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> buscarEnderecoPorId(@PathVariable UUID id) {
        try {
            Endereco enderecoEncontrado = service.findById(id);

            return ResponseEntity.ok().body(new EnderecoDTO(enderecoEncontrado));
        } catch (SemConteudoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable UUID id, @RequestBody @Valid EnderecoDTO dto) {
        try {
            Endereco enderecoEncontrado = service.update(id, dto);

            return ResponseEntity.ok().body(new EnderecoDTO(enderecoEncontrado));
        } catch (SemConteudoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEndereco(@PathVariable UUID id) {
        try {
            service.delete(id);

            return ResponseEntity.noContent().build();
        } catch (SemConteudoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}