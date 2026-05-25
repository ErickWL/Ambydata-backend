package com.ambydata.ambydata_backend.repository;

// Importa o modelo que este repositório vai gerenciar no banco
import com.ambydata.ambydata_backend.model.Usuario;

// JpaRepository fornece todos os métodos de banco prontos (findAll, save, delete, etc.)
import org.springframework.data.jpa.repository.JpaRepository;

// Marca essa interface como um componente de repositório do Spring
import org.springframework.stereotype.Repository;

// Indica ao Spring que essa interface é responsável pelo acesso à tabela "usuarios"
// O Spring a detecta automaticamente e a disponibiliza para injeção com @Autowired
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // JpaRepository<Usuario, Long> recebe dois parâmetros:
    //   → Usuario : a entidade que será gerenciada (tabela usuarios)
    //   → Long    : o tipo do ID da entidade (campo "id" em Usuario)

    // Métodos herdados do JpaRepository prontos para uso:
    // -----------------------------------------------------------
    // findAll()        → SELECT * FROM usuarios
    // findById(id)     → SELECT * FROM usuarios WHERE id = ?
    // save(usuario)    → INSERT ou UPDATE automático
    // deleteById(id)   → DELETE FROM usuarios WHERE id = ?
    // count()          → SELECT COUNT(*) FROM usuarios
    // existsById(id)   → SELECT EXISTS(...) WHERE id = ?
    // -----------------------------------------------------------

    // Como "usuarios" é uma entidade de autenticação, consultas comuns que
    // poderiam ser adicionadas aqui futuramente:
    // Optional<Usuario> findByEmail(String email);  // Buscar usuário pelo email no login
    // boolean existsByEmail(String email);          // Verificar email duplicado no cadastro
}