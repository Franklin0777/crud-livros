package com.livraria.crud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * CAMADA MODEL — entidade Livro.
 *
 * @Entity  -> o Hibernate transforma esta classe na tabela "livros" do MySQL.
 * Cada atributo vira uma coluna. As anotações de validação (@NotBlank, @Min...)
 * são conferidas quando o Controller usa @Valid.
 */
@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT no MySQL
    private Long id;

    @NotBlank(message = "Informe o título.")
    @Size(max = 150, message = "O título pode ter no máximo 150 caracteres.")
    @Column(nullable = false, length = 150)
    private String titulo;

    @NotBlank(message = "Informe o autor.")
    @Size(max = 100, message = "O autor pode ter no máximo 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String autor;

    @NotNull(message = "Informe o ano de publicação.")
    @Min(value = 1450, message = "Ano inválido.")
    @Max(value = 2100, message = "Ano inválido.")
    @Column(name = "ano_publicacao", nullable = false)
    private Integer anoPublicacao;

    // BigDecimal: tipo certo para dinheiro (Double arredonda centavos)
    @NotNull(message = "Informe o preço.")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero.")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    // Construtor vazio: obrigatório para o JPA
    public Livro() {
    }

    // Getters e setters (NetBeans: Alt+Insert -> Getter and Setter)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
