package com.ambydata.ambydata_backend.controller;

import com.ambydata.ambydata_backend.repository.MetricaRepository;
import com.ambydata.ambydata_backend.model.Metrica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/dados")
@CrossOrigin(origins = "http://localhost:5173") // Libera o React acessar o Java
public class MetricaController {

    @Autowired
    private MetricaRepository metricaRepository;

    @GetMapping
    public List<Metrica> listarMetricas() {
        return metricaRepository.findAll(); // Retorna todas as métricas do MySQL em JSON
    }
}