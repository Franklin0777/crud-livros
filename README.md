# CRUD de Livros — Spring Boot + Thymeleaf + MySQL

Atividade CRUD · Projeto de Software e Segurança da Informação (Prof. Gean) · ADS UMC 2026/02
Aluno: Franklin David Pereira da Silva · Tema: **Livros (Direito do Trabalho)**

## Tecnologias
Java 17 · Spring Boot 4.1.1 · Spring MVC · Spring Data JPA · Thymeleaf · MySQL (XAMPP) · Maven · NetBeans

## Como rodar
1. XAMPP: **Start** no MySQL (e no Apache, para usar o phpMyAdmin).
2. No phpMyAdmin, crie o banco `livraria` (o sistema também cria sozinho se não existir).
3. NetBeans: **File → Open Project** → pasta `crud-livros` → **Run (F6)**.
4. Acesse **http://localhost:8080**.

A tabela `livros` é criada pelo Hibernate a partir da classe `Livro`.

## Variáveis de ambiente (senha fora do código)
| Variável | Padrão | Uso |
|---|---|---|
| `DB_PORT` | 3306 | porta do MySQL |
| `DB_USER` | root | usuário do banco |
| `DB_PASSWORD` | (vazia) | senha do banco |

No NetBeans: botão direito no projeto → **Properties → Run → VM Options**, ex.: `-DDB_PORT=3307`.

## Camadas
Navegador → **Controller** → **Service** → **Repository** → MySQL, e a **View** (Thymeleaf) devolve o HTML.

| Camada | Arquivo |
|---|---|
| Model (entidade) | `model/Livro.java` — título, autor, ano de publicação, preço |
| Repository | `repository/LivroRepository.java` |
| Service | `service/LivroService.java` |
| Controller | `controller/LivroController.java` |
| View | `templates/livros/lista.html` e `form.html` + `static/css/estilo.css` |

## Rotas
| Ação | Método e URL |
|---|---|
| Listar / buscar | GET `/livros` |
| Novo | GET `/livros/novo` |
| Salvar | POST `/livros/salvar` |
| Editar | GET `/livros/{id}/editar` |
| Excluir | POST `/livros/{id}/excluir` |

## Boas práticas aplicadas
- Validação de entrada com `@Valid` + `@NotBlank`, `@Min`, `@Max`, `@DecimalMin`.
- Consultas parametrizadas do JPA (proteção contra SQL Injection).
- `th:text` escapa o texto (proteção contra XSS).
- Post/Redirect/Get depois de salvar e excluir.
- Exclusão só por POST, com confirmação.
- Senha do banco fora do código (variáveis de ambiente).
