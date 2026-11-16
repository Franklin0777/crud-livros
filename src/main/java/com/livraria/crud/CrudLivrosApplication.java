package com.livraria.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal: liga o servidor na porta 8080.
 * Fica no pacote raiz (com.livraria.crud) para o Spring achar
 * os pacotes model, repository, service e controller que estão abaixo dela.
 */
@SpringBootApplication
public class CrudLivrosApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudLivrosApplication.class, args);
    }
}
