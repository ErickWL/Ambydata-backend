package com.ambydata.ambydata_backend.repository;

import com.ambydata.ambydata_backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Prontinho! O Spring já sabe como mexer na tabela de usuários.
}
