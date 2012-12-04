<%@page import="hackathon.studymap.jdbc.model.StudyGroupPost"%>
<%@page import="hackathon.studymap.jdbc.model.StudyGroupSchedule"%>
<%@page import="hackathon.studymap.jdbc.model.StudyGroupMember"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="ctrl" class="hackathom.studymap.jsp.controller.StudyGroupController" scope="request"/>
<jsp:useBean id="ctrlMember" class="hackathom.studymap.jsp.controller.StudyGroupMemberController" scope="request"/>
<jsp:useBean id="ctrlPost" class="hackathom.studymap.jsp.controller.StudyGroupPostController" scope="request"/>
<jsp:useBean id="ctrlSche" class="hackathom.studymap.jsp.controller.StudyGroupScheduleController" scope="request"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><fmt:message key="studyGroupEdit"/></title>
    </head>
    <body><h2><fmt:message key="studyGroupEdit"/></h2>
        <c:if  test="${not empty param.studyGroupId}">
            <c:out value="edit"/>
            <%
                String studyGroupId = request.getParameter("studyGroupId");
                System.out.println("StudyGroup : " + studyGroupId);
                ctrl.load(Integer.parseInt(studyGroupId));
                List<StudyGroupMember> members = ctrlMember.getListByStudyGroup(Integer.parseInt(studyGroupId));
                request.setAttribute("members", members);
                List<StudyGroupPost> posts   = ctrlPost.getListByStudyGroup(Integer.parseInt(studyGroupId));
                request.setAttribute("posts", posts);
                List<StudyGroupSchedule> schedles = ctrlSche.getListByStudyGroup(Integer.parseInt(studyGroupId));
                request.setAttribute("schedles", schedles);
            %>
        </c:if>
        <input type="text" name="studyGroupId" maxlength="10" required="required" value="${ctrl.model.studyGroupId}"/>
<label for="ownerId"><fmt:message key="ownerId"/></label>
<input type="text" name="ownerId" maxlength="10" required="required" value="${ctrl.model.ownerId}"/>
<label for="studySubjectId"><fmt:message key="studySubjectId"/></label>
<input type="text" name="studySubjectId" maxlength="10" required="required" value="${ctrl.model.studySubjectId}">
<label for="description"><fmt:message key="description"/></label>
<input type="text" name="description" maxlength="255" required="required" value="${ctrl.model.description}"/>
<label for="longitude"><fmt:message key="longitude"/></label>
<input type="text" name="longitude" maxlength="15" required="required" value="<fmt:formatNumber minFractionDigits="6" value="${ctrl.model.longitude}"/>"/>
<label for="latitude"><fmt:message key="latitude"/></label>
<input type="text" name="latitude" maxlength="15" required="required" value="<fmt:formatNumber minFractionDigits="6" value="${ctrl.model.latitude}"/>"/>




<table>
<thead>
<tr>
<th><fmt:message key="studyGroupPostId"/></th>
<th><fmt:message key="login"/></th>
<th><fmt:message key="name"/></th>
</tr>
</thead>
<tbody>
<c:forEach var="it" items="${requestScope.members}">
<tr>
<td><img src="http://graph.facebook.com/${it.fbUsername}/picture?type=small"/><p>${it.login}</p></td>
<td>${it.login}</td>
<td>${it.fbUsername}</td>
</tr>
</c:forEach>
</table>


<table>
<thead>
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
<tbody>
<c:forEach var="it" items="${requestScope.schedles}">
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
</c:forEach>
</table>

<table>
<thead>
<tr>
<th><fmt:message key="studyGroupPostId"/></th>
<th><fmt:message key="studyGroupPostId"/></th>
<th><fmt:message key="studyGroupId"/></th>
<th><fmt:message key="userId"/></th>
<th><fmt:message key="title"/></th>
<th><fmt:message key="content"/></th>
<th><fmt:message key="posted"/></th>
</tr>
</thead>
<tbody>
<c:forEach var="it" items="${requestScope.posts}">
<tr>
<td><img src="http://graph.facebook.com/${it.fbUsername}/picture?type=small"/><p>${it.login}</p></td>
<td><a href="/studymap/jsp/studyGroupPost/edit.jsp?studyGroupPostId=${it.studyGroupPostId}"/>${it.studyGroupPostId}</a></td>
<td>${it.studyGroupId}</td>
<td>${it.userId}</td>
<td>${it.title}</td>
<td>${it.content}</td>
<td>${it.posted}</td>
</tr>
</c:forEach>
</tbody>
</table>


        <%@include file="/jsp/menu.jsp"%>
    </body>
</html>