package com.carrinho.carrinho.model;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jogo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long id;

    @NotBlank
    String nome;
    @NotBlank
    String classificacao;
    @NotBlank
    String preco;
    @NotBlank
    String desenvolvedores;
    @NotBlank
    String genero;
   @NotBlank
    String dataDeLancamento;
    @NotBlank
    String plataformas;
    
    String imageUri;
    Date deleted;

    /* 
    public void setDeleted(Date deleted){
        this.deleted = deleted;
    }
    */

}