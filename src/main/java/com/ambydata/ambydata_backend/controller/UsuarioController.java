package com.ambydata.ambydata_backend.controller;

import com.ambydata.ambydata_backend.model.Usuario;
import com.ambydata.ambydata_backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios") // URL que o React vai chamar
@CrossOrigin(origins = "http://localhost:5173") // O seu CORS protetor agora feito em Java!
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Rota GET para listar os usuários
    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listarTodos();
    }

    // Rota POST para cadastrar um novo usuário
    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        return usuarioService.salvar(usuario);
    }
}