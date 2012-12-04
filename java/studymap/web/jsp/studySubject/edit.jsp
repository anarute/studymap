<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="ctrl" class="hackathom.studymap.jsp.controller.StudySubjectController" scope="request"/>
<!DOCTYPE html><html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="studySubjectEdit"/></title></head>
<body><h2><fmt:message key="studySubjectEdit"/></h2>
    <c:if  test="${not empty param.studySubjectId}">
        <c:out value="edit"/>
        <%
            String studySubjectId = request.getParameter("studySubjectId");
            System.out.println("StudySubject : " + studySubjectId);
            ctrl.load(Integer.parseInt(studySubjectId));
        %>
    </c:if>
<%@include file="/jsp/menu.jsp"%>
<form name="studySubject" action="studySubject/salvar" method="POST">
<fieldset>
<label for="studySubjectId"><fmt:message key="studySubjectId"/></label>
<input type="text" name="studySubjectId" maxlength="10" required="required" value="${ctrl.model.studySubjectId}"/>
<label for="studyMainSubjectId"><fmt:message key="studyMainSubjectId"/></label>
<input type="text" name="studyMainSubjectId" maxlength="10" required="required" value="${ctrl.model.studyMainSubjectId}"/>
<label for="description"><fmt:message key="description"/></label>
<input type="text" name="description" maxlength="100" required="required" value="${ctrl.model.description}"/>
<input type="submit" value="<fmt:message key="save"/>">
</fieldset>
</form>
<%@include file="/jsp/fotter.jsp" %>
</body></html>