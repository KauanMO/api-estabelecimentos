package br.com.dio.apiestabelecimentos.domain.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table
@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private Integer nota;

    @Column
    private LocalDate dataVisita;

    @Column
    private LocalDate dataAvaliacao;

    @Column
    private String comentario;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Estabelecimento estabelecimento;
}