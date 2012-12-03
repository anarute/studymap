<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%><jsp:useBean id="bancoView" class="br.com.jcomputacao.convivere.view.BancoWeb" scope="request"/>
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
<th><fmt:message key="password"/></th>
<th><fmt:message key="name"/></th>
<th><fmt:message key="birthday"/></th>
<th><fmt:message key="posts"/></th>
<th><fmt:message key="gold"/></th>
</tr>
</thead>
<tbody class="ui-widget-content">
<c:forEach var="it" items="${requestScope.cooperadosListagem.cooperados}">
<tr>
<td>${it.userId}</td>
<td>${it.login}</td>
<td>${it.password}</td>
<td>${it.name}</td>
<td>${it.birthday}</td>
<td>${it.posts}</td>
<td>${it.gold}</td>
</tr>
</c:forEach></tbody></table>
<%@include file="/jsp/fotter.jsp" %>
</body></html>