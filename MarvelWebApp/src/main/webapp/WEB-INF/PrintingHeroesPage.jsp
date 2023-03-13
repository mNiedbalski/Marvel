<%-- 
    Document   : PrintingHeroesPage
    Created on : 30 lis 2022, 02:37:03
    Author     : Michał Niedbalski
    Version    : 1.0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Affiliation</title>
    </head>
    <body>
        <h1>Heroes affiliated with chosen universe</h1>
        <p>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>            
            <c:forEach items="${superheroes}" var="superhero">
                ${superhero.getCharName()}<br>
            </c:forEach>
        </p>
    </body>
</html>
