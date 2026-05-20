package com.ambydata.ambydata_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "metricas_producao") // Tabela que o Java vai criar no MySQL
public class Metrica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;      // Ex: "Janeiro", "Máquina A"
    private Double custo;      // Ex: 1500.00
    private Double lucro;      // Ex: 3200.00
    private Double oee;        // Ex: 85.5 (Porcentagem de OEE)

    // Construtor Vazio exigido pelo Hibernate
    public Metrica() {}

    // Getters e Setters
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