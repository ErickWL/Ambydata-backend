package com.ambydata.ambydata_backend.repository;

// Importa o modelo que este repositório vai gerenciar no banco
import com.ambydata.ambydata_backend.model.Usuario;

// JpaRepository fornece todos os métodos de banco prontos (findAll, save, delete, etc.)
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

// Indica ao Spring que essa interface é responsável pelo acesso à tabela "usuarios"
// O Spring a detecta automaticamente e a disponibiliza para injeção com @Autowired
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);
}