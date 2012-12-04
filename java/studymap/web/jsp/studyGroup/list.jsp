<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="ctrl" class="hackathom.studymap.jsp.controller.StudyGroupController" scope="request"/>
<!DOCTYPE html><html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="studyGroupList"/></title></head>
<body><h2><fmt:message key="studyGroupList"/></h2>
<%@include file="/jsp/menu.jsp" %>
<table class="ui-widget" style="border-collapse: collapse;">
<thead class="ui-widget-header">
<tr>
<th><fmt:message key="studyGroupId"/></th>
<th><fmt:message key="area"/></th>
<th><fmt:message key="mainSubject"/></th>
<th><fmt:message key="subject"/></th>
<th><fmt:message key="ownerId"/></th>
<th><fmt:message key="studySubjectId"/></th>
<th><fmt:message key="description"/></th>
<th><fmt:message key="longitude"/></th>
<th><fmt:message key="latitude"/></th>
<th><fmt:message key="detail"/></th>
</tr>
</thead>
<tbody class="ui-widget-content">
<c:forEach var="it" items="${requestScope.ctrl.list}">
<tr>
<td><a href="<c:url value="/jsp/studyGroup/edit.jsp?studyGroupId="/>${it.studyGroupId}">${it.studyGroupId}</a></td>
<td>${it.ownerId}</td>
<td>${it.area}</td>
<td>${it.mainSubject}</td>
<td>${it.subject}</td>
<td>${it.studySubjectId}</td>
<td>${it.description}</td>
<td>${it.longitude}</td>
<td>${it.latitude}</td>
<td><a href="<c:url value="/jsp/studyGroup/detail.jsp?studyGroupId="/>${it.studyGroupId}">${it.studyGroupId}</a></td>
</tr>
</c:forEach></tbody></table>
<%@include file="/jsp/fotter.jsp" %>
</body></html>