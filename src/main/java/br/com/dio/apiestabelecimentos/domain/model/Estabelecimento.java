package br.com.dio.apiestabelecimentos.domain.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table
@Entity
public class Estabelecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String nome;

    @Column
    private String horaAbertura;

    @Column
    private String horaFechamento;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Endereco endereco;

    @OneToMany(mappedBy = "id")
    private List<Avaliacao> avalicacoes;
}