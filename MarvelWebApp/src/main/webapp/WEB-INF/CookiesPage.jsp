<%-- 
    Document   : CookiesPage
    Created on : 1 gru 2022, 14:17:19
    Author     : Michał Niedbalski
    Version    : 1.0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Displaying cookies</title>
    </head>
    <body>
        <h1>Cookies that were stored:</h1>
        <p>
            <%
            out.println("Amount of times when user wanted to see strongest superhero: " + request.getAttribute("strongestVisits") + "<br>");
            out.println("Amount of times when user wanted to see Pearson's Correlation coefficient value: " + request.getAttribute("pearsonCorrVisits") + "<br>");
            out.println("Amount of times when user wanted to display superheroes affiliated with chosen universe: " + request.getAttribute("affiliationVisits") + "<br>");
            %> 
        </p>
    </body>
</html>
