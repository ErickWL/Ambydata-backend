package com.ambydata.ambydata_backend.service;

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
        if (usuario.getSenhaHash() != null) {
            usuario.setSenhaHash(passwordEncoder.encode(usuario.getSenhaHash()));
        }
        return usuarioRepository.save(usuario);
    }
}
