package com.carrinho.carrinho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrinho.carrinho.model.Jogo;


@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {
      
}