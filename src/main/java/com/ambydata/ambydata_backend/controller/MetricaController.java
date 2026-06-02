package com.ambydata.ambydata_backend.controller;

// Importações necessárias para o funcionamento do controller
import com.ambydata.ambydata_backend.repository.MetricaRepository; // Interface de acesso ao banco de dados
import com.ambydata.ambydata_backend.model.Metrica;                 // Modelo/entidade da tabela de métricas
import org.springframework.beans.factory.annotation.Autowired;      // Injeção de dependência do Spring
import org.springframework.web.bind.annotation.*;                    // Anotações REST do Spring (GET, POST, etc.)
import java.util.List;                                               // Lista para retornar múltiplos registros

// Define que essa classe é um controller REST — responde requisições HTTP com JSON
@RestController

// Todas as rotas dessa classe começam com /api/dados
@RequestMapping("/api/dados")

// Permite que o frontend React (rodando na porta 5173) acesse essa API
// Sem isso, o navegador bloquearia as requisições por política de segurança (CORS)
@CrossOrigin(origins = "*")
public class MetricaController {

    // O Spring injeta automaticamente uma instância do repositório aqui
    // Não é necessário instanciar manualmente com "new"
    @Autowired
    private MetricaRepository metricaRepository;

    // Responde requisições GET em /api/dados
    // Retorna a lista completa de métricas do banco de dados em formato JSON
    @GetMapping
    public List<Metrica> listarMetricas() {
        return metricaRepository.findAll(); // findAll() busca todos os registros da tabela no MySQL
    }
}