package com.livraria.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Quem abrir http://localhost:8080 vai direto para a lista de livros. */
@Controller
public class HomeController {

    @GetMapping("/")
    public String inicio() {
        return "redirect:/livros";
    }
}
