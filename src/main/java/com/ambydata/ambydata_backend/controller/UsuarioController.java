package com.ambydata.ambydata_backend.controller;

import com.ambydata.ambydata_backend.dto.LoginRequest;
import com.ambydata.ambydata_backend.dto.UsuarioResponse;
import com.ambydata.ambydata_backend.model.Usuario;
import com.ambydata.ambydata_backend.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioResponse> listar() {
        return usuarioService.listarTodos().stream()
                .map(UsuarioResponse::de)
                .toList();
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Usuario usuario) {
        // [Guigo710] Pontos 1/3: devolve 400 com mensagem para e-mail duplicado, cargo inválido ou senha ausente
        try {
            return ResponseEntity.ok(UsuarioResponse.de(usuarioService.salvar(usuario)));
        } catch (IllegalArgumentException | org.springframework.dao.DuplicateKeyException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> autenticar(@RequestBody LoginRequest dadosLogin) {
        return usuarioService.autenticar(dadosLogin.email(), dadosLogin.senha())
                .<ResponseEntity<?>>map(usuario -> ResponseEntity.ok(UsuarioResponse.de(usuario)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("E-mail ou senha incorretos."));
    }
}