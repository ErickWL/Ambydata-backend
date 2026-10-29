package com.ambydata.ambydata_backend.dto;

import com.ambydata.ambydata_backend.model.Usuario;

public record UsuarioResponse(String id, String nome, String email, String cargo) {
    public static UsuarioResponse de(Usuario u) {
        return new UsuarioResponse(u.getId(), u.getNome(), u.getEmail(), u.getCargo());
    }
}