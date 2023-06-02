package com.carrinho.carrinho.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.carrinho.carrinho.model.Usuario;
import com.carrinho.carrinho.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{

    UsuarioRepository repository;
    //BCryptPasswordEncoder encoder;

      public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;  
    }
/* 
    public void create1(Usuario u){
        u.setSenha(encoder.encode(u.getSenha()));
        this.repository.save(u);
    }
  */  
    

    public void create(Usuario u){
        this.repository.save(u);
    }

    public void delete(long id){
        repository.deleteById(id);
    }

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Optional<Usuario> findById(long id){
        return repository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> user = repository.findUsuarioByLogin(username);
        if (user.isPresent()){
            return user.get();
        }else{
            throw new UsernameNotFoundException("Username not found");
        }
    }



}
