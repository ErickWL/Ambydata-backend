package com.ambydata.ambydata_backend.model;

// Importações JPA — mesmas do modelo Metrica, mapeiam a classe para uma tabela no banco
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Importações Lombok — geram código repetitivo automaticamente em tempo de compilação
import lombok.Data;             // Gera: getters, setters, toString, equals e hashCode
import lombok.NoArgsConstructor; // Gera: construtor vazio — exigido pelo Hibernate
import lombok.AllArgsConstructor; // Gera: construtor com todos os campos como argumento

// Marca como entidade JPA — representa uma tabela no banco
@Entity

// Nome da tabela no MySQL
@Table(name = "usuarios")

// Lombok: substitui todos os getters, setters, toString, equals e hashCode
// que precisariam ser escritos manualmente (como foi feito na classe Metrica)
@Data

// Lombok: gera o construtor vazio — new Usuario()
// Necessário para o Hibernate recriar objetos vindos do banco
@NoArgsConstructor

// Lombok: gera o construtor completo — new Usuario(id, nome, email, senha)
// Útil para criar objetos já preenchidos em testes ou na lógica de negócio
@AllArgsConstructor
public class Usuario {

    // Chave primária com valor gerado automaticamente pelo banco (auto_increment)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;   // Nome completo do usuário
    private String email;  // Email — idealmente único no banco (@Column(unique = true))
    private String senha;  // Senha — idealmente armazenada com hash (ex: BCrypt)
}