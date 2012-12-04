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
<<<<<<< HEAD
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>StudyMap</title>
        <link rel="stylesheet" href="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.css" />
        <link rel="stylesheet" href="../css/main.css" />
        <script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
        <script src="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.js"></script>
        <script src="../js/main.js"></script>
        <script src="../js/studyFacebook.js"></script>
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        
        <!--meta name=viewport content='width=device-width'-->
        <link rel="stylesheet" href="../css/font-awesome.css">
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC1oxalNtwVwxEgQ1LW5mwE9xRtF2NCwBo&sensor=true"></script>
        <script type="text/javascript" src="../js/map.js"></script>
=======
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>JSP Page</title>
>>>>>>> d46677e23f1055a5b31d14f525e6cb58bb5e09e6
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
