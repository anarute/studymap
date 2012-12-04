
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
        <script src="../js/studyFacebook.js"></script>
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        
        <!--meta name=viewport content='width=device-width'-->
        <link rel="stylesheet" href="../css/font-awesome.css">
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC1oxalNtwVwxEgQ1LW5mwE9xRtF2NCwBo&sensor=true"></script>
        <script type="text/javascript" src="../js/map.js"></script>
    </head>

    <body>

        <div data-role="page" id="page1">
            <div data-role="header" data-theme="c"> 
                <h1><i class="icon-group"></i>  StudyMap</h1>
                <div id="mini_profile">
                    <img id="profile_thumb" src="">
                    <span id="name"></span>
                </div>
            </div> 
            <a data-role="button" id="btn_fb" data-corners="false" data-theme="b" href="#page1">
                Login com Facebook
            </a>
            <div id="profile">
                UserID: <div id="userid"></div>
                UserName: <div id="username"></div>
                Name: <div id="name"></div>
                Link: <div id="link"></div>
            </div>
            <div data-role="fieldcontain" class="form_search">
                <fieldset data-role="controlgroup" data-mini="true">
                    <label for="mapfilter">
                    </label>
                    <input name="filter" id="mapfilter" placeholder="filtre por interesse"
                           value="" type="search">
                </fieldset>
            </div>

            <div id="sidebar_btn">
                <a href="mainmenu.jsp">
                    <i class="icon-reorder"></i>
                </a>
            </div>
            <div id="map_canvas" style="width:100%; height:100%">
            </div>
        </div>
        <%@include file="fotter.jsp" %>
    </body>

</html>
