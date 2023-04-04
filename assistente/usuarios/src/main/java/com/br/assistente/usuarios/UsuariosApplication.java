package com.br.assistente.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.assistente.usuarios")

public class UsuariosApplication {

    public static void main (String args[]) {
        SpringApplication.run(UsuariosApplication.class, args);
    }


}
