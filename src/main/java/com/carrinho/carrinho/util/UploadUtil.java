package com.carrinho.carrinho.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {
    
    public static boolean fazerUploadImagem(MultipartFile imagem){

        boolean sucessoUpload = false;

        if(!imagem.isEmpty()){
            
            String nomeArquivo = imagem.getOriginalFilename();

            try {

                String pastaUploadImage = "C:\\Users\\romul\\Documents\\Programação Web\\ProjetoCarrinho\\carrinho\\src\\main\\resources\\static\\images\\img-Upload";
                File dir = new File(pastaUploadImage);
                if(!dir.exists()){
                    dir.mkdirs();
                }

                File serverFile = new File(dir.getAbsolutePath() + File.separator + nomeArquivo);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

                stream.write(imagem.getBytes());
                stream.close();

                System.out.println("Armazenando em: " + serverFile.getAbsolutePath());
                System.out.println("Você fez o upload do arquivo " + nomeArquivo + "com sucesso!");
                sucessoUpload = true;

            } catch (Exception e) {
             
                System.out.println("Você falhou em carregar o arquivo: " + nomeArquivo + " => " + e.getMessage());
            }
        }else{

            System.out.println("Você fallhou em carregar o arquivo porque ele esta vazio!");

        }
        return sucessoUpload;
    }
}
 