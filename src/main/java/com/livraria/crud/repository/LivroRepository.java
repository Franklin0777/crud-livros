package com.livraria.crud.repository;

import com.livraria.crud.model.Livro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CAMADA REPOSITORY — acesso ao banco.
 *
 * JpaRepository<Livro, Long> já traz pronto: save, findAll, findById, deleteById, count.
 * Os métodos abaixo são "Query Methods": o Spring lê o NOME do método e monta o SQL.
 * As consultas são parametrizadas, o que protege contra SQL Injection.
 */
@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    // SELECT * FROM livros ORDER BY titulo
    List<Livro> findAllByOrderByTituloAsc();

    // SELECT * FROM livros WHERE titulo LIKE %?% (sem diferenciar maiúsculas) ORDER BY titulo
    List<Livro> findByTituloContainingIgnoreCaseOrderByTituloAsc(String titulo);
}
