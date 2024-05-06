package br.com.dio.apiestabelecimentos.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dio.apiestabelecimentos.controller.dto.EstabelecimentoDTO;
import br.com.dio.apiestabelecimentos.domain.model.Estabelecimento;
import br.com.dio.apiestabelecimentos.service.EstabelecimentoService;
import br.com.dio.apiestabelecimentos.service.exception.ObjetoRelacionadoNaoEncontrado;
import br.com.dio.apiestabelecimentos.service.exception.SemConteudoException;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {
    private final EstabelecimentoService service;

    public EstabelecimentoController(EstabelecimentoService service) {
        this.service = service;
    }

    // POST
    @PostMapping
    public ResponseEntity<EstabelecimentoDTO> criarEstabelecimento(@RequestBody @Valid EstabelecimentoDTO dto) {
        try {
            Estabelecimento estabelecimentoCriado = service.create(dto);

            EstabelecimentoDTO dtoCriado = new EstabelecimentoDTO(estabelecimentoCriado);

            return ResponseEntity.status(201).body(dtoCriado);
        } catch (ObjetoRelacionadoNaoEncontrado e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET
    @GetMapping
    public ResponseEntity<List<EstabelecimentoDTO>> buscarTodosEstabelecimentos() {
        try {
            List<Estabelecimento> estabelecimentosEncontrados = service.findAll();

            List<EstabelecimentoDTO> dtosEncontradas = estabelecimentosEncontrados.stream()
                    .map(estabelecimento -> new EstabelecimentoDTO(estabelecimento))
                    .toList();

            return ResponseEntity.ok().body(dtosEncontradas);
        } catch (SemConteudoException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstabelecimentoDTO> buscarEstabelecimentoPorId(@PathVariable UUID id) {
        try {
            Estabelecimento estabelecimentosEncontrados = service.findById(id);

            return ResponseEntity.ok().body(new EstabelecimentoDTO(estabelecimentosEncontrados));
        } catch (SemConteudoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<EstabelecimentoDTO> atualizarEstabelecimento(@PathVariable UUID id,
            @RequestBody @Valid EstabelecimentoDTO dto) {
        try {
            Estabelecimento estabelecimentosEncontrados = service.update(id, dto);

            return ResponseEntity.ok().body(new EstabelecimentoDTO(estabelecimentosEncontrados));
        } catch (SemConteudoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEstabelecimento(@PathVariable UUID id) {
        try {
            service.delete(id);

            return ResponseEntity.noContent().build();
        } catch (SemConteudoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}