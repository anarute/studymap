<%--
    Document   : index
    Created on : 20/11/2012, 18:13:12
    Author     : murilodemoraestuvani
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%@include file="menu.jsp" %>

        <table>
        <c:forEach items="${param}" var="p">
        <tr>
            <td>${p.key}</td>
            <td>${p.value}</td>
        </tr>
        </c:forEach>
        </table>
        <%@include file="fotter.jsp" %>


    </body>
</html>
