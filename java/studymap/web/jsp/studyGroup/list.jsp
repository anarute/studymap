<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="ctrl" class="hackathom.studymap.jsp.controller.StudyGroupController" scope="request"/>
<!DOCTYPE html><html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><fmt:message key="studyGroupList"/></title>
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        <link rel="stylesheet" href="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.css" />
        <link rel="stylesheet" href="../css/main.css" />
        <script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
        <script src="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.js"></script>
        <script src="../js/main.js"></script>
        
        <!--meta name=viewport content='width=device-width'-->
        <link rel="stylesheet" href="../css/font-awesome.css">
        <script type="text/javascript" src="../js/map.js"></script>
</head>
<body><h2><fmt:message key="studyGroupList"/></h2>
<%@include file="/jsp/menu.jsp" %>
<table class="ui-widget" style="border-collapse: collapse;">
<thead class="ui-widget-header">
<tr>
<th><fmt:message key="studyGroupId"/></th>
<th><fmt:message key="ownerId"/></th>
<th><fmt:message key="studySubjectId"/></th>
<th><fmt:message key="description"/></th>
<th><fmt:message key="longitude"/></th>
<th><fmt:message key="latitude"/></th>
</tr>
</thead>
<tbody class="ui-widget-content">
<c:forEach var="it" items="${requestScope.ctrl.list}">
<tr>
<td><a href="<c:url value="/jsp/studyGroup/edit.jsp?studyGroupId="/>${it.studyGroupId}">${it.studyGroupId}</a></td>
<td>${it.ownerId}</td>
<td>${it.studySubjectId}</td>
<td>${it.description}</td>
<td>${it.longitude}</td>
<td>${it.latitude}</td>
</tr>
</c:forEach></tbody></table>
<%@include file="/jsp/fotter.jsp" %>
</body></html>