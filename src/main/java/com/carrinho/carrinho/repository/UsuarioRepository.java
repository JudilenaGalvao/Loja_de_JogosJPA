package com.carrinho.carrinho.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carrinho.carrinho.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findUsuarioByLogin(String login);
}
