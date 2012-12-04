<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="ctrl" class="hackathom.studymap.jsp.controller.UserController" scope="request"/>
<!DOCTYPE html><html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="userList"/></title></head>
<body><h2><fmt:message key="userList"/></h2>
<%@include file="/jsp/menu.jsp" %>
<table class="ui-widget" style="border-collapse: collapse;">
<thead class="ui-widget-header">
<tr>
<th><fmt:message key="userId"/></th>
<th><fmt:message key="login"/></th>
<th><fmt:message key="email"/></th>
</tr>
</thead>
<tbody class="ui-widget-content">
<c:forEach var="it" items="${requestScope.ctrl.list}">
<tr>
<td><a href="<c:url value="/jsp/user/edit.jsp?userId="/>${it.userId}">${it.userId}</a></td>
<td>${it.login}</td>
<td>${it.email}</td>
</tr>
</c:forEach></tbody></table>
<%@include file="/jsp/fotter.jsp" %>
</body></html>