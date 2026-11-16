package com.livraria.crud.controller;

import com.livraria.crud.model.Livro;
import com.livraria.crud.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * CAMADA CONTROLLER — rotas do CRUD de livros.
 *
 * | Ação    | Método e URL                 | Tela               |
 * |---------|------------------------------|--------------------|
 * | Listar  | GET  /livros  (?busca=texto) | livros/lista       |
 * | Novo    | GET  /livros/novo            | livros/form        |
 * | Salvar  | POST /livros/salvar          | volta para a lista |
 * | Editar  | GET  /livros/{id}/editar     | livros/form        |
 * | Excluir | POST /livros/{id}/excluir    | volta para a lista |
 *
 * Depois do POST usamos "redirect:" (Post/Redirect/Get):
 * se o usuário apertar F5, o formulário não é enviado de novo.
 */
@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String busca, Model model) {
        model.addAttribute("busca", busca);
        model.addAttribute("livros", service.listar(busca));
        return "livros/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("tituloPagina", "Novo livro");
        model.addAttribute("livro", new Livro());
        return "livros/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("livro") Livro livro,
                         BindingResult erros,
                         Model model,
                         RedirectAttributes redirect) {
        // @Valid conferiu as anotações da classe Livro; os problemas ficam em "erros"
        if (erros.hasErrors()) {
            model.addAttribute("tituloPagina", livro.getId() == null ? "Novo livro" : "Editar livro");
            return "livros/form"; // volta ao formulário mostrando as mensagens
        }
        service.salvar(livro);
        redirect.addFlashAttribute("mensagem", "Livro salvo com sucesso.");
        return "redirect:/livros";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        try {
            model.addAttribute("livro", service.buscarPorId(id));
        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
            return "redirect:/livros";
        }
        model.addAttribute("tituloPagina", "Editar livro");
        return "livros/form"; // o mesmo formulário do cadastro, já preenchido
    }

    // Exclusão por POST (não por link GET), com confirmação no navegador
    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            service.excluir(id);
            redirect.addFlashAttribute("mensagem", "Livro excluído.");
        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/livros";
    }
}
