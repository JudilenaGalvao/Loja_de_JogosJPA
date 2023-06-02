package com.carrinho.carrinho.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.carrinho.carrinho.model.Jogo;
import com.carrinho.carrinho.repository.JogoRepository;

@Service
public class JogoService {

    JogoRepository repository;


    public JogoService(JogoRepository repository) {
        this.repository = repository;
    }


    public Optional<Jogo> findById(long id){
        return repository.findById(id);
    }

    public List<Jogo> findAll(){
        return repository.findAll();
    }

    public void create(Jogo j){
        this.repository.save(j);
    }

}