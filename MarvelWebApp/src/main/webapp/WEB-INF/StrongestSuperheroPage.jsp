<%-- 
    Document   : StrongestSuperheroPage
    Created on : 29 lis 2022, 20:32:07
    Author     : Michał Niedbalski
    Version    : 1.0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Strongest superhero page</title>
    </head>
    <body>
        <h1>Strongest superhero</h1>
        <p>
            <%
            out.println(request.getAttribute("strongest"));
            %>
        </p>
    </body>
</html>
