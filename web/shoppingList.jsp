<%-- 
    Document   : shoppingList
    Created on : Dec 10, 2020, 10:49:00 PM
    Author     : dorge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List Page</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello, ${username} <a href="register.jsp">Logout</a></p>

        <form action="shoppinglist" method="post" >
            <label>Add item:</label>
            <input type="text" name="item" value ="">
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add">
            <br>
        </form>

        <c:if test="${items.size() > 0}">
            <form action="shoppinglist" method="post">
                <c:forEach var='item' items ="${items}">
                    <input type="radio" name="delete" value="${item}">${item}
                    <br>
                </c:forEach>
                <input type="submit" value="delete">
                <input type="hidden" name="action" value="delete">
            </form>
        </c:if>

    </body>
</html>
