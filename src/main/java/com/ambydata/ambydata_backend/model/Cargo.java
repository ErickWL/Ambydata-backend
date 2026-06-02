package com.ambydata.ambydata_backend.model;

import jakarta.persistence.Column;
// Importações JPA para mapear a tabela de cargos no banco
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Importações Lombok para gerar os getters e setters automaticamente
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

// Marca como entidade JPA — representa a tabela de cargos no banco
@Entity

// Nome da tabela de cargos no MySQL
@Table(name = "cargos")

// Lombok: gera getters, setters, toString e construtores
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cargo {

    // Chave primária da tabela cargos (auto_increment)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cargo")
    private Long id;

    // Nome do cargo (ex: "DIRETOR", "OPERARIO")
    // Deve bater exatamente com o nome que o React vai ler (nomeCargo)
    @Column(name = "nome_cargo")
    private String nomeCargo;

    private String descricao;
}