<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mensagem de Status</title>
    </head>
    <body>
        <% out.print(request.getAttribute("mensagem"));%>
        <p><a href="index.html">Home</a></p>
    </body>
</html>
