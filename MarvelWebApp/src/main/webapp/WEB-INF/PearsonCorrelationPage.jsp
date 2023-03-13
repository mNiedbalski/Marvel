<%-- 
    Document   : PearsonCorrelationServlet
    Created on : 29 lis 2022, 20:56:03
    Author     : Michał Niedbalski
    Version    : 1.0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pearson correlation page</title>
    </head>
    <body>
        <h1>Pearson's correlation coefficient value:</h1>
        <p>
            <%
            out.println(request.getAttribute("pearsonCorr"));
            %>
        </p>
    </body>
</html>
