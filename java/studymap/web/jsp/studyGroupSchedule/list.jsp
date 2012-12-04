<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%><jsp:useBean id="bancoView" class="hackathom.studymap.jsp.controller.BancoWeb" scope="request"/>
<!DOCTYPE html><html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="studyGroupScheduleList"/></title></head>
<body><h2><fmt:message key="studyGroupScheduleList"/></h2>
<%@include file="/jsp/menu.jsp" %>
<table class="ui-widget" style="border-collapse: collapse;">
<thead class="ui-widget-header">
<tr>
<th><fmt:message key="studyGroupScheduleId"/></th>
<th><fmt:message key="studyGroupId"/></th>
<th><fmt:message key="dayOfWeek"/></th>
<th><fmt:message key="year"/></th>
<th><fmt:message key="month"/></th>
<th><fmt:message key="dayOfMonth"/></th>
<th><fmt:message key="hour"/></th>
<th><fmt:message key="minute"/></th>
</tr>
</thead>
<tbody class="ui-widget-content">
<c:forEach var="it" items="${requestScope.cooperadosListagem.cooperados}">
<tr>
<td>${it.studyGroupScheduleId}</td>
<td>${it.studyGroupId}</td>
<td>${it.dayOfWeek}</td>
<td>${it.year}</td>
<td>${it.month}</td>
<td>${it.dayOfMonth}</td>
<td>${it.hour}</td>
<td>${it.minute}</td>
</tr>
</c:forEach></tbody></table>
<%@include file="/jsp/fotter.jsp" %>
</body></html>