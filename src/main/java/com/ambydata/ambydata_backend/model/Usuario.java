package com.ambydata.ambydata_backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    private String id;

    private String nome;
    // [Guigo710] Ponto 1: índice único em email (impede contas duplicadas)
    @Indexed(unique = true)
    private String email;

    @ToString.Exclude
    private String senhaHash;

    private String cargo;
    // [Guigo710] Ponto 5: valor padrão true, para ativo não ficar null
    private Boolean ativo = true;
}