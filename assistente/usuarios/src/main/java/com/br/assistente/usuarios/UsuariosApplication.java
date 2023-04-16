package com.br.assistente.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "com.br.assistente.usuarios")
@EnableJpaRepositories
public class UsuariosApplication {

    public static void main (String args[]) {
        SpringApplication.run(UsuariosApplication.class, args);
    }


}
