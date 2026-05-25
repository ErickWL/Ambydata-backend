package com.ambydata.ambydata_backend.repository;

// Importa o modelo que este repositório vai gerenciar no banco
import com.ambydata.ambydata_backend.model.Metrica;

// JpaRepository fornece todos os métodos de banco prontos (findAll, save, delete, etc.)
import org.springframework.data.jpa.repository.JpaRepository;

// Marca essa interface como um componente de repositório do Spring
import org.springframework.stereotype.Repository;

// Indica ao Spring que essa interface é responsável pelo acesso ao banco de dados
// O Spring a detecta automaticamente e a disponibiliza para injeção com @Autowired
@Repository
public interface MetricaRepository extends JpaRepository<Metrica, Long> {
    // JpaRepository<Metrica, Long> recebe dois parâmetros:
    //   → Metrica : a entidade que será gerenciada (tabela metricas_producao)
    //   → Long    : o tipo do ID da entidade (campo "id" em Metrica)

    // O corpo está vazio pois o JpaRepository já fornece os métodos essenciais:
    // -----------------------------------------------------------
    // findAll()        → SELECT * FROM metricas_producao
    // findById(id)     → SELECT * FROM metricas_producao WHERE id = ?
    // save(metrica)    → INSERT ou UPDATE automático
    // deleteById(id)   → DELETE FROM metricas_producao WHERE id = ?
    // count()          → SELECT COUNT(*) FROM metricas_producao
    // existsById(id)   → SELECT EXISTS(...) WHERE id = ?
    // -----------------------------------------------------------
    // Consultas personalizadas podem ser adicionadas aqui quando necessário
    // Ex: List<Metrica> findByLabel(String label);
}