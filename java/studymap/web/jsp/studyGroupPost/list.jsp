<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%><jsp:useBean id="bancoView" class="br.com.jcomputacao.convivere.view.BancoWeb" scope="request"/>
<!DOCTYPE html><html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="studyGroupPostList"/></title></head>
<body><h2><fmt:message key="studyGroupPostList"/></h2>
<%@include file="/jsp/menu.jsp" %>
<table class="ui-widget" style="border-collapse: collapse;">
<thead class="ui-widget-header">
<tr>
<th><fmt:message key="studyGroupPostId"/></th>
<th><fmt:message key="studyGroupId"/></th>
<th><fmt:message key="userId"/></th>
<th><fmt:message key="title"/></th>
<th><fmt:message key="content"/></th>
<th><fmt:message key="posted"/></th>
</tr>
</thead>
<tbody class="ui-widget-content">
<c:forEach var="it" items="${requestScope.cooperadosListagem.cooperados}">
<tr>
<td>${it.studyGroupPostId}</td>
<td>${it.studyGroupId}</td>
<td>${it.userId}</td>
<td>${it.title}</td>
<td>${it.content}</td>
<td>${it.posted}</td>
</tr>
</c:forEach></tbody></table>
<%@include file="/jsp/fotter.jsp" %>
</body></html>