package com.ambydata.ambydata_backend.model;

// [Guigo710] Ponto 3: antigo Cargo (entidade JPA/MySQL, comentada) substituído por enum,
// para validar o campo "cargo" de Usuario em vez de aceitar texto livre.
public enum Cargo {
    DIRETOR,
    OPERARIO;

    // [Guigo710] Converte texto livre (ignora maiúsculas/espaços); lança IllegalArgumentException se inválido
    public static Cargo de(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("Cargo é obrigatório.");
        }
        try {
            return Cargo.valueOf(valor.trim().toUpperCase(java.util.Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Cargo inválido: " + valor);
        }
    }
}
