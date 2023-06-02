package com.carrinho.carrinho.controller;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.carrinho.carrinho.model.Jogo;
import com.carrinho.carrinho.service.JogoService;
import com.carrinho.carrinho.util.UploadUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller

public class JogoController {


    JogoService service;
    @Autowired

    public JogoController(JogoService service) {
        this.service = service;
    }


    @GetMapping("/cadastrarJogo")
    public String getCadastrarJogo(Model model){
        Jogo j = new Jogo();
        model.addAttribute("jogo", j);
        model.addAttribute("acao", "cadastrar");

        return "cadastrarJogo";
    }


    @GetMapping("/editarJogo/{id}")
    public String getEditarJogo(@PathVariable(name = "id") long id, Model model){

        Optional<Jogo> j = service.findById(id);
        if (j.isPresent()){
            model.addAttribute("jogo", j.get());
            model.addAttribute("acao", "editar");
        }else{
            return "redirect:/admin";
        }

        return "editarJogo";
    }

    @GetMapping("/delete/{id}")
    public String getEditarPage(@PathVariable(name = "id") long id, Model model){

        Optional<Jogo> jogoOptional = service.findById(id);
        if (jogoOptional.isPresent()){
            Jogo jogo = jogoOptional.get();
            jogo.setDeleted(new Date());
            service.create(jogo);
            return "redirect:/index";
        }else{
            return "redirect:/index";
        }

       
    }


    @PostMapping(value = "/doCadastrarJogo")
    public String cadastrar(@ModelAttribute @Valid Jogo jogo, @RequestParam("file") MultipartFile imagem, Errors errors){
        if (errors.hasErrors()){
            System.out.println("erro: "+errors.getAllErrors());
            return "cadastrarJogo";
        }else{
            try {
                if (imagem != null && !imagem.isEmpty()) {
                    if (UploadUtil.fazerUploadImagem(imagem)) {
                        jogo.setImageUri(imagem.getOriginalFilename());
                    }
                }
                service.create(jogo);
                return "redirect:/admin";
            }catch(Exception e){
                return "redirect:/admin";
            }
            
        }
    }

    @GetMapping("/adicionarCarrinho/{id}")
    public String adicionarCarrinho(@PathVariable(name = "id") long id, HttpSession session){
        Optional<Jogo> j = service.findById(id);

        if(j.isPresent()){
            Jogo jogo = j.get();

            List<Jogo> carrinho = (List<Jogo>) session.getAttribute("carrinho");
            
            if (carrinho == null) {
                // Se não houver um carrinho na sessão, crie um novo ArrayList de itens
                carrinho = new ArrayList<>();
            }

            carrinho.add(jogo);
            session.setAttribute("carrinho", carrinho);
            return "redirect:/index";

        }
        else{
            return "redirect:/index";
        }

    }

    @GetMapping("/verCarrinho")
    public String verCarrinho(Model model, HttpSession session) {
        ArrayList<Jogo> carrinho = (ArrayList<Jogo>) session.getAttribute("carrinho");
    
        if (carrinho != null && !carrinho.isEmpty()) { 
            for (Jogo jogo : carrinho) {
                String caminhoImagem = "images/img-Upload/" + jogo.getImageUri();
                jogo.setImageUri(caminhoImagem);
            }         
            model.addAttribute("carrinho", carrinho);
           return "carrinho"; // Renderiza a página "carrinho" para exibir os itens do carrinho
        } else {
            return "redirect:/index"; // Redireciona para a página inicial com a mensagem informando que não há itens no carrinho
        }
    }

    @GetMapping("/finalizarCompra")
    public String finalizarCompra(HttpSession session) {
        // Invalidar a sessão
        session.invalidate();

        // Redirecionar para "/index"
        return "redirect:/index";
}

    @GetMapping(value = {"/", "/index", "/index.html"})
    public String getIndex(Model model, HttpServletResponse response, HttpServletRequest request){
        List<Jogo> jogos = service.findAll();
        for (Jogo jogo : jogos) {
            String caminhoImagem = "images/img-Upload/" + jogo.getImageUri();
            jogo.setImageUri(caminhoImagem);
        }
        HttpSession session = request.getSession(true);
        ArrayList<Jogo> listaJogos = (ArrayList<Jogo>)session.getAttribute("carrinho");   
        int qtdItens = 0;
        if(listaJogos != null){
            qtdItens = listaJogos.size();
        }
        model.addAttribute("qtdItens", qtdItens);
        model.addAttribute("jogos", jogos);
        Cookie visitaCookie = new Cookie("visita", LocalDateTime.now().toString());
        visitaCookie.setMaxAge(24 * 60 * 60); // Definir a validade do cookie para 24 horas (em segundos)

    // Adicionar o cookie à resposta
        response.addCookie(visitaCookie);

        return "formatada";
    }

    @GetMapping(value = "/admin")
    public String getAdmin(Model model){
        List<Jogo> jogos = service.findAll();
        for (Jogo jogo : jogos) {
            String caminhoImagem = "images/img-Upload/" + jogo.getImageUri();
            jogo.setImageUri(caminhoImagem);
        }
        model.addAttribute("jogos", jogos);

        return "admin";
    }

    @GetMapping("/formatada")
    public String getPaginaFormatada(Model model){
        List<Jogo> jogos = service.findAll();
        for (Jogo jogo : jogos) {
            String caminhoImagem = "images/img-Upload/" + jogo.getImageUri();
            jogo.setImageUri(caminhoImagem);
        }
        model.addAttribute("jogos", jogos);

        return "formatada";
    }
    
}
