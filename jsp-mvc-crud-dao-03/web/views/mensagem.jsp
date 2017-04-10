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
            <h1>Aluno Cadastrado com Sucesso</h1>
            <c:forEach var="aluno" items="${listaAluno}">
                <tr>
                    <td>RA:</td>
                    <td>${aluno.ra}</td>
                </tr>                
                <tr>
                    <td>Nome:</td>
                    <td>${aluno.nome}</td>
                </tr>
                <tr>
                    <td>Curso:</td>
                    <td>${aluno.curso}</td>
                </tr>
                <tr>
                    <td>Disciplina:</td>
                    <td>${aluno.disciplina}</td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td>${aluno.email}</td>
                </tr>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <p> <a href="index.html">Voltar</a> </p>
</body>
</html>
