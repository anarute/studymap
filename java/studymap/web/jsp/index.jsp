<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>StudyMap</title>
        <link rel="stylesheet" href="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.css" />
        <link rel="stylesheet" href="../css/main.css" />
        <script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
        <script src="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.js"></script>
        <script src="../js/main.js"></script>
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        
        <!--meta name=viewport content='width=device-width'-->
        <link rel="stylesheet" href="../css/font-awesome.css">
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC1oxalNtwVwxEgQ1LW5mwE9xRtF2NCwBo&sensor=true"></script>
        <script type="text/javascript" src="../js/map.js"></script>
    </head>

    <body>

        <div data-role="page" id="page1">
            <div>
                <script src="../js/studyFacebook.js"></script>
                <button data-role="button" id="btn_fb" data-corners="false" data-theme="b" onclick="login()"><i class="icon-facebook"></i> Logar</button>
                <!--<a data-role="button" id="btn_fb" data-corners="false" data-theme="b" href="#page1">
                    <i class="icon-facebook"></i> Logar
                </a>-->
            </div>
            <div>
                <a data-role="button" id="btn_fb" data-corners="false" data-theme="c" href="index.jsp">
                    <i class="icon-map-marker"></i> Ver mapa
                </a>
            </div>
            <div>
                <a data-role="button" id="btn_fb" data-corners="false" data-theme="d" href="<c:url value="/jsp/studyArea/edit.jsp"/>">
                    <i class="icon-plus-sign"></i> <fmt:message key="studyAreaEdit"/>
                </a>
            </div>
            <div>
                <a data-role="button" id="btn_fb" data-corners="false" data-theme="e" href="#page1">
                    <i class="icon-screenshot"></i> Meu local
                </a>
            </div>

        </div>
        <%@include file="fotter.jsp" %>
    </body>

</html>