package com.carrinho.carrinho.controller;


import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.carrinho.carrinho.model.Usuario;
import com.carrinho.carrinho.service.UsuarioService;

@Controller
public class UsuarioController {

    UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }


    @GetMapping("/cadastrarUsuario")
    public String cadastrarUsuario(Model model){
        Usuario u = new Usuario();
        model.addAttribute("usuario", u);
        return "cadastrarUsuario";
    }

    @GetMapping("/editarUsuario/{id}")
    public String getEditarPage(@PathVariable(name = "id") long id, Model model){

        Optional<Usuario> u = service.findById(id);
        if (u.isPresent()){
            model.addAttribute("usuario", u.get());
        }else{
            return "redirect:/index";
        }

        return "editarUsuario";
    }

    @GetMapping("/deletar/{id}")
    public String doDeletarUsuario(@PathVariable(name = "id") long id){
        
        Optional<Usuario> usuario = service.findById(id);

        if(usuario.isPresent()){
            service.delete(id);
        }

        return "redirect:/index";
    }


    @PostMapping("/doSalvarUsuario")
    public String doSalvarUsuario(@ModelAttribute Usuario u){
        service.create(u);

        return "redirect:/";
    }

   
}
