<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Controle de Alunos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="views/css/estilo.css">
    </head>
    <body>
        <h1>Editar Registro</h1><br>
        <form name="cadastro" action="Controle" method="POST">
            <c:forEach var="aluno" items="${listaAlunos}">
                <label>Nome:</label><input type="text" name="nomeAluno" class="input" value="${aluno.nome}"><br>
                <label>Curso:</label><input type="text" name="cursoAluno" class="input" value="${aluno.curso}"><br>
                <label>Disciplina:</label><input type="text" name="disciplinaAluno" class="input" value="${aluno.disciplina}"><br>
                <label>E-mail:</label><input type="text" name="emailAluno" class="input" value="${aluno.email}"><br>
                <label>&nbsp;</label><input type="submit" name="btCadastrar" value="Salvar" class="botao">
                <input type="hidden" name="flag" value="salvar">
                <input type="hidden" name="ra" value="${aluno.ra}">
                <input type="button" name="btVoltar" onclick="history.go(-1);" value="Voltar" class="botao">
            </c:forEach>
        </form>
    </body>
</html>
