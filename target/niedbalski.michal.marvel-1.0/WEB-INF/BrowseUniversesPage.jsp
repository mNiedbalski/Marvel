<%-- 
    Document   : BrowseUniversesPage
    Created on : 29 lis 2022, 22:15:04
    Author     : Michał Niedbalski
    Version    : 1.0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Browse Universes</title>
    </head>
    <body>
        <h1>Choose universe to browse</h1>
        <div>
            <form action="BrowsingSpecificUniverse">
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>            
            <select name="universe">
                <c:forEach items="${universes}" var="universeName" varStatus="loop">
                    <option value="${universeName}">
                    ${universeName}
                    </option>
                </c:forEach>
            </select>
            
            <input type="submit" name="Browse" value="browse" />
            </form>
        </div>
    </body>
</html>
