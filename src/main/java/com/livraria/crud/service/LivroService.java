package com.livraria.crud.service;

import com.livraria.crud.model.Livro;
import com.livraria.crud.repository.LivroRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * CAMADA SERVICE — regras de negócio dos livros.
 *
 * O Controller nunca fala direto com o banco: ele chama o Service,
 * e o Service usa o Repository (Controller -> Service -> Repository -> MySQL).
 */
@Service
public class LivroService {

    private final LivroRepository repository;

    // Injeção de dependência pelo construtor: o Spring entrega o Repository pronto
    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    /** Lista todos, ou só os que têm o texto no título quando há busca. */
    public List<Livro> listar(String busca) {
        if (busca == null || busca.isBlank()) {
            return repository.findAllByOrderByTituloAsc();
        }
        return repository.findByTituloContainingIgnoreCaseOrderByTituloAsc(busca.trim());
    }

    public Livro buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado."));
    }

    /** id vazio = INSERT (novo); id preenchido = UPDATE (edição). */
    public Livro salvar(Livro livro) {
        livro.setTitulo(livro.getTitulo().trim());
        livro.setAutor(livro.getAutor().trim());
        return repository.save(livro);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Livro não encontrado.");
        }
        repository.deleteById(id);
    }
}
