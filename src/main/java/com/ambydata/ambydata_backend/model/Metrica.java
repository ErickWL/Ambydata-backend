package com.ambydata.ambydata_backend.model;

// Importações JPA — mapeiam essa classe para uma tabela no banco de dados
import jakarta.persistence.Entity;          // Marca a classe como uma entidade gerenciada pelo JPA/Hibernate
import jakarta.persistence.GeneratedValue;  // Define que o valor do ID será gerado automaticamente
import jakarta.persistence.GenerationType;  // Estratégias de geração de ID (IDENTITY, SEQUENCE, etc.)
import jakarta.persistence.Id;              // Marca qual campo é a chave primária da tabela
import jakarta.persistence.Table;           // Permite customizar o nome da tabela no banco

// Diz ao Hibernate que essa classe representa uma tabela no banco de dados
@Entity

// Define explicitamente o nome da tabela no MySQL
// Sem essa anotação, o Hibernate usaria o nome da classe ("Metrica") como nome da tabela
@Table(name = "metricas_producao")
public class Metrica {

    // Chave primária da tabela
    @Id

    // O banco gera o ID automaticamente com auto_increment (MySQL)
    // GenerationType.IDENTITY delega o controle do ID para o próprio banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;  // Rótulo da métrica — Ex: "Janeiro", "Máquina A"
    private Double custo;  // Custo do período/item  — Ex: 1500.00
    private Double lucro;  // Lucro do período/item  — Ex: 3200.00
    private Double oee;    // Eficiência global do equipamento (Overall Equipment Effectiveness) — Ex: 85.5%

    // Construtor vazio obrigatório — o Hibernate precisa instanciar a classe
    // sem argumentos ao reconstruir objetos vindos do banco de dados
    public Metrica() {}

    // -------------------------
    // Getters e Setters
    // Permitem ler e modificar os campos privados de fora da classe
    // O Spring/Jackson usa esses métodos para converter o objeto em JSON (e vice-versa)
    // -------------------------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public Double getCusto() { return custo; }
    public void setCusto(Double custo) { this.custo = custo; }

    public Double getLucro() { return lucro; }
    public void setLucro(Double lucro) { this.lucro = lucro; }

    public Double getOee() { return oee; }
    public void setOee(Double oee) { this.oee = oee; }
}