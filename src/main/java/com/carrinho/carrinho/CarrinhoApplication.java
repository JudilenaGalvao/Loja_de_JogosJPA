package com.carrinho.carrinho;

//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;

//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.password.PasswordEncoder;

//import com.carrinho.carrinho.model.Usuario;
//import com.carrinho.carrinho.repository.UsuarioRepository;

@SpringBootApplication
public class CarrinhoApplication {

	/*@Bean
    CommandLineRunner commandLineRunner(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        return args -> {

            List<Usuario> users = Stream.of(
					new Usuario("","Romulo", "admin", encoder.encode("admin"), true),
					new Usuario("","Judilena", "admin2", encoder.encode("admin2"), true),
					new Usuario("","Taniro", "user", encoder.encode("user"), true)
            ).collect(Collectors.toList());

            for (var e : users) {
                System.out.println(e);
            }
            usuarioRepository.saveAll(users);
        };
    }*/


	public static void main(String[] args) {
		SpringApplication.run(CarrinhoApplication.class, args);
	}

}
