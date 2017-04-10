<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="views/css/estilo.css">
        <title>Controle de Alunos</title>
    </head>
    <body>
        <table>
            <h1>Alunos Cadastrados</h1>
            <thead>
                <tr>
                    <th>RA</th>
                    <th>Nome</th>
                    <th>Curso</th>
                    <th>Disciplina</th>
                    <th>E-mail</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="aluno" items="${listaAlunos}">
                    <tr>
                        <td>${aluno.ra}</td>
                        <td>${aluno.nome}</td>
                        <td>${aluno.curso}</td>
                        <td>${aluno.disciplina}</td>
                        <td>${aluno.email}</td>
                        <td><a href="Controle?flag=editar&ra=${aluno.ra}">Editar</a></td>
                        <td><a href="Controle?flag=excluir&ra=${aluno.ra}">Excluir</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>
        <p>[<a href="cadastrar.html">Novo Aluno</a>] &nbsp; -  &nbsp;[<a href="index.html">Home</a>] </p>
    </body>
</html>
