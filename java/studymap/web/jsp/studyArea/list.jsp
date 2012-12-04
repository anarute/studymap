<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="ctrl" class="hackathom.studymap.jsp.controller.StudyAreaController" scope="request"/>
<!DOCTYPE html><html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="studyAreaList"/></title></head>
<body><h2><fmt:message key="studyAreaList"/></h2>
<%@include file="/jsp/menu.jsp" %>
<table class="ui-widget" style="border-collapse: collapse;">
<thead class="ui-widget-header">
<tr>
<th><fmt:message key="studyAreaId"/></th>
<th><fmt:message key="description"/></th>
</tr>
</thead>
<tbody class="ui-widget-content">
<c:forEach var="it" items="${requestScope.ctrl.list}">
<tr>
<td><a href="<c:url value="/jsp/studyArea/edit.jsp?studyAreaId="/>${it.studyAreaId}">${it.studyAreaId}</a></td>
<td>${it.description}</td>
</tr>
</c:forEach></tbody></table>
<%@include file="/jsp/fotter.jsp" %>
</body></html>