package com.ambydata.ambydata_backend.service;

// Importa o modelo e o repositório que este serviço vai utilizar
import com.ambydata.ambydata_backend.model.Usuario;
import com.ambydata.ambydata_backend.repository.UsuarioRepository;

// Injeção de dependência automática do Spring
import org.springframework.beans.factory.annotation.Autowired;

// Marca essa classe como um componente de serviço do Spring
import org.springframework.stereotype.Service;
import java.util.List;

// Indica ao Spring que essa classe contém a lógica de negócio da aplicação
// Fica entre o Controller (recebe requisições) e o Repository (acessa o banco)
@Service
public class UsuarioService {

    // O Spring injeta automaticamente uma instância do repositório aqui
    // O Service nunca acessa o banco diretamente — sempre via Repository
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Retorna a lista completa de usuários cadastrados no banco
    // Chamado pelo UsuarioController quando chega um GET em /api/usuarios
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll(); // SELECT * FROM usuarios
    }

    // Salva um novo usuário ou atualiza um existente
    // O JPA decide automaticamente: se tem ID → UPDATE, se não tem → INSERT
    // Retorna o usuário salvo já com o ID gerado pelo banco
    // Chamado pelo UsuarioController quando chega um POST em /api/usuarios
    public Usuario salvar(Usuario usuario) {
        // ⚠️ Aqui seria o lugar ideal para aplicar regras de negócio antes de salvar
        // Ex: validar email duplicado, aplicar hash na senha com BCrypt, etc.
        return usuarioRepository.save(usuario);
    }
}