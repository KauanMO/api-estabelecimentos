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

import br.com.dio.apiestabelecimentos.controller.dto.AvaliacaoDTO;
import br.com.dio.apiestabelecimentos.domain.model.Avaliacao;
import br.com.dio.apiestabelecimentos.service.AvaliacaoService;
import br.com.dio.apiestabelecimentos.service.exception.ObjetoRelacionadoNaoEncontrado;
import br.com.dio.apiestabelecimentos.service.exception.SemConteudoException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
    private final AvaliacaoService service;

    public AvaliacaoController(AvaliacaoService service) {
        this.service = service;
    }

    // POST
    @PostMapping
    public ResponseEntity<AvaliacaoDTO> criarAvaliacao(@RequestBody @Valid AvaliacaoDTO dto) {
        try {
            Avaliacao avaliacaoCriada = service.create(dto);

            AvaliacaoDTO dtoCriado = new AvaliacaoDTO(avaliacaoCriada);

            return ResponseEntity.status(201).body(dtoCriado);
        } catch (ObjetoRelacionadoNaoEncontrado e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET
    @GetMapping
    public ResponseEntity<List<AvaliacaoDTO>> buscarTodasAvaliacoes() {
        try {
            List<Avaliacao> avaliacoesEncontradas = service.findAll();

            List<AvaliacaoDTO> dtosEncontradas = avaliacoesEncontradas.stream()
                    .map(avaliacao -> new AvaliacaoDTO(avaliacao))
                    .toList();

            return ResponseEntity.ok().body(dtosEncontradas);
        } catch (SemConteudoException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> buscarAvaliacaoPorId(@PathVariable UUID id) {
        try {
            Avaliacao avaliacaoEncontrada = service.findById(id);

            return ResponseEntity.ok().body(new AvaliacaoDTO(avaliacaoEncontrada));
        } catch (SemConteudoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> atualizarAvaliacao(@PathVariable UUID id,
            @RequestBody @Valid AvaliacaoDTO dto) {
        try {
            Avaliacao avaliacaoEncontrada = service.update(id, dto);

            return ResponseEntity.ok().body(new AvaliacaoDTO(avaliacaoEncontrada));
        } catch (SemConteudoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarAvaliacao(@PathVariable UUID id) {
        try {
            service.delete(id);

            return ResponseEntity.noContent().build();
        } catch (SemConteudoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}