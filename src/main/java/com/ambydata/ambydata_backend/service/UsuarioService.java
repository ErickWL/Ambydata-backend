package com.ambydata.ambydata_backend.service;

import com.ambydata.ambydata_backend.model.Cargo;
import com.ambydata.ambydata_backend.model.Usuario;
import com.ambydata.ambydata_backend.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> autenticar(String email, String senha) {
        if (email == null || email.isBlank() || senha == null || senha.isBlank()) {
            return Optional.empty();
        }

        return usuarioRepository.findByEmail(email.trim().toLowerCase(Locale.ROOT))
                .filter(usuario -> Boolean.TRUE.equals(usuario.getAtivo())
                        && usuario.getSenhaHash() != null
                        && passwordEncoder.matches(senha, usuario.getSenhaHash()));
    }

    public Usuario salvar(Usuario usuario) {
        if (usuario.getEmail() != null) {
            usuario.setEmail(usuario.getEmail().trim().toLowerCase(Locale.ROOT));
        }

        // [Guigo710] Ponto 1: checagem de e-mail duplicado (além do índice único no banco)
        if (usuario.getEmail() != null) {
            usuarioRepository.findByEmail(usuario.getEmail())
                    .filter(existente -> !existente.getId().equals(usuario.getId()))
                    .ifPresent(existente -> {
                        throw new IllegalArgumentException("E-mail já cadastrado.");
                    });
        }

        // [Guigo710] Ponto 3: valida e normaliza o cargo contra o enum Cargo
        usuario.setCargo(Cargo.de(usuario.getCargo()).name());

        // [Guigo710] Ponto 5: ativo nunca fica null (padrão true)
        if (usuario.getAtivo() == null) {
            usuario.setAtivo(true);
        }

        // [Guigo710] Ponto 2: evita hash duplo. Só aplica BCrypt quando a senha ainda não é um hash;
        // em atualização sem senha informada, mantém o hash já salvo.
        if (usuario.getSenhaHash() == null || usuario.getSenhaHash().isBlank()) {
            if (usuario.getId() == null) {
                throw new IllegalArgumentException("Senha é obrigatória.");
            }
            String hashAtual = usuarioRepository.findById(usuario.getId())
                    .map(Usuario::getSenhaHash)
                    .orElseThrow(() -> new IllegalArgumentException("Senha é obrigatória."));
            usuario.setSenhaHash(hashAtual);
        } else if (!pareceHashBcrypt(usuario.getSenhaHash())) {
            usuario.setSenhaHash(passwordEncoder.encode(usuario.getSenhaHash()));
        }
        return usuarioRepository.save(usuario);
    }

    // [Guigo710] Ponto 2: detecta hash BCrypt ($2a$, $2b$, $2y$ + 60 caracteres)
    private boolean pareceHashBcrypt(String valor) {
        return valor.length() == 60 && valor.matches("^\\$2[aby]\\$\\d{2}\\$.*");
    }
}
