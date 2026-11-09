package com.ambydata.ambydata_backend.config;

// [Guigo710] Ponto 4: seed inicial de um usuário administrador (arquivo novo).
// Só roda se ADMIN_EMAIL e ADMIN_SENHA estiverem definidos e o e-mail ainda não existir.

import com.ambydata.ambydata_backend.model.Cargo;
import com.ambydata.ambydata_backend.model.Usuario;
import com.ambydata.ambydata_backend.repository.UsuarioRepository;
import com.ambydata.ambydata_backend.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedAdmin(UsuarioRepository repository, UsuarioService service) {
        return args -> {
            String email = System.getenv("ADMIN_EMAIL");
            String senha = System.getenv("ADMIN_SENHA");
            if (email == null || email.isBlank() || senha == null || senha.isBlank()) {
                return;
            }
            if (repository.findByEmail(email.trim().toLowerCase(Locale.ROOT)).isPresent()) {
                return;
            }
            String nome = System.getenv().getOrDefault("ADMIN_NOME", "Administrador");
            service.salvar(new Usuario(null, nome, email, senha, Cargo.DIRETOR.name(), true));
        };
    }
}
