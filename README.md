# jsp-mvc-crud-dao-03
MVC JSP CRUD com MySQL (JDBC)

# Alterações
- ~~Corrigido tamanho dos campos SQL no Script SQL~~
- ~~Inclusão do arquivo pesquisar.html~~
- ~~Inclusão do arquivo editar.jsp~~
- ~~Adicionada as funcionalidades de: Pesquisa e Edição (sem salvamento)~~

- Incluídas as funcionalidades: Editar, Salvar e Excluir

# Script SQL
```
CREATE DATABASE mvc_jsp;

USE mvc_jsp;

CREATE TABLE aluno(
    id          int             primary key not null auto_increment,
    ra          varchar(12)     not null,
    nome        varchar(100)    not null,
    curso       varchar(50)     not null,
    disciplina  varchar(50)     not null,
    email       varchar(30)     not null
);
