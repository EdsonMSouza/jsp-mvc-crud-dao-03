<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mensagem de Erro</title>
    </head>
    <body>
        <% out.print(request.getAttribute("mensagem"));%>
        <p><a href="javascript:history.go(-1);">Voltar</a></p>
    </body>
</html>
