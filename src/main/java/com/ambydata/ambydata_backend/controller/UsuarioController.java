package com.ambydata.ambydata_backend.controller;

// Importações necessárias para o controller de usuários
import com.ambydata.ambydata_backend.model.Usuario;         // Entidade que representa um usuário no banco
import com.ambydata.ambydata_backend.repository.UsuarioRepository;
import com.ambydata.ambydata_backend.service.UsuarioService; // Camada de serviço com as regras de negócio
import org.springframework.beans.factory.annotation.Autowired; // Injeção de dependência automática do Spring
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;              // Anotações REST (GET, POST, RequestBody, etc.)
import java.util.List;                                         // Lista para retornar múltiplos usuários
import java.util.Optional;                                     // Para lidar com resultados que podem ser nulos

// Define essa classe como um controller REST — responde com JSON
@RestController

// Todas as rotas desta classe ficam sob o caminho /api/usuarios
@RequestMapping("/api/usuarios")

// Libera o acesso do frontend React (porta 5173) — evita bloqueio por CORS no navegador
@CrossOrigin(origins = "https://ambydata-react.vercel.app")
public class UsuarioController {

    // O Spring injeta automaticamente o serviço aqui — sem precisar de "new UsuarioService()"
    // A lógica de negócio fica no Service, não no Controller
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Responde requisições GET em /api/usuarios
    // Retorna a lista completa de usuários cadastrados no banco
    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listarTodos(); // Delega a busca para a camada de serviço
    }

    // Responde requisições POST em /api/usuarios
    // Recebe os dados do novo usuário no corpo da requisição (JSON → objeto Usuario)
    // Retorna o usuário salvo, já com o ID gerado pelo banco
    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        // @RequestBody converte automaticamente o JSON recebido para um objeto Usuario
        return usuarioService.salvar(usuario); // Delega o salvamento para a camada de serviço
    }

    //Responde requisições POST em /api/usuarios/login
    @PostMapping("/login")
    public ResponseEntity<?> autenticar(@RequestBody Usuario dadosLogin) { 
        
        // 1. Busca diretamente o usuário correto trazendo o cargo acoplado via JOIN FETCH
        Optional<Usuario> usuarioOp = usuarioRepository.realizarLogin(dadosLogin.getEmail(), dadosLogin.getSenha());

        // 2. Se encontrou, retorna o usuário completo com Status 200 (OK)
        if (usuarioOp.isPresent()) {
            return ResponseEntity.ok(usuarioOp.get()); 
        }

        // 3. Se não encontrou, retorna Status 401 (Não Autorizado)
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha incorretos."); 
    }
}