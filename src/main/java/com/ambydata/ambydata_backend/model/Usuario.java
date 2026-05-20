package com.ambydata.ambydata_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;  // 1. IMPORTANTE: Adicione esse import
import lombok.AllArgsConstructor; // 2. IMPORTANTE: Adicione esse import

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor  // 3. ESSA AQUI: Cria o construtor vazio que o Hibernate exigiu!
@AllArgsConstructor // 4. ESSA AQUI: Cria um construtor com todos os campos (boa prática)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;
}