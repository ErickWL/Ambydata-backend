package com.ambydata.ambydata_backend.repository;

// Importa o modelo que este repositório vai gerenciar no banco
import com.ambydata.ambydata_backend.model.Usuario;

// JpaRepository fornece todos os métodos de banco prontos (findAll, save, delete, etc.)
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// Marca essa interface como um componente de repositório do Spring
import org.springframework.stereotype.Repository;
import java.util.Optional;

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

    /**
     * Busca um usuário pelo e-mail e senha para realizar a autenticação.
     * O "LEFT JOIN FETCH u.cargo" força o Hibernate a carregar os dados do Cargo 
     * na mesma consulta, evitando que o campo venha nulo no JSON.
     */
    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.cargo WHERE u.email = :email AND u.senha = :senha")
    Optional<Usuario> realizarLogin(@Param("email") String email, @Param("senha") String senha);
}