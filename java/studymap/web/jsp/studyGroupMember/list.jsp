<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%><jsp:useBean id="bancoView" class="br.com.jcomputacao.convivere.view.BancoWeb" scope="request"/>
<!DOCTYPE html><html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="studyGroupMemberList"/></title></head>
<body><h2><fmt:message key="studyGroupMemberList"/></h2>
<%@include file="/jsp/menu.jsp" %>
<table class="ui-widget" style="border-collapse: collapse;">
<thead class="ui-widget-header">
<tr>
<th><fmt:message key="studyGroupMemeberId"/></th>
<th><fmt:message key="studyGroupId"/></th>
<th><fmt:message key="userId"/></th>
</tr>
</thead>
<tbody class="ui-widget-content">
<c:forEach var="it" items="${requestScope.cooperadosListagem.cooperados}">
<tr>
<td>${it.studyGroupMemeberId}</td>
<td>${it.studyGroupId}</td>
<td>${it.userId}</td>
</tr>
</c:forEach></tbody></table>
<%@include file="/jsp/fotter.jsp" %>
</body></html>