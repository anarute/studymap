<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="ctrl" class="hackathom.studymap.jsp.controller.StudyMainSubjectController" scope="request"/>
<!DOCTYPE html><html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="studyMainSubjectEdit"/></title></head>
<body><h2><fmt:message key="studyMainSubjectEdit"/></h2>
    <c:if  test="${not empty param.studyMainSubjectId}">
        <c:out value="edit"/>
        <%
            String studyMainSubjectId = request.getParameter("studyMainSubjectId");
            System.out.println("StudyGroup : " + studyMainSubjectId);
            ctrl.load(Integer.parseInt(studyMainSubjectId));
        %>
    </c:if>
<%@include file="/jsp/menu.jsp"%>
<form name="studyMainSubject" action="studyMainSubject/salvar" method="POST">
<fieldset>
<label for="studyMainSubjectId"><fmt:message key="studyMainSubjectId"/></label>
<input type="text" name="studyMainSubjectId" maxlength="10" required="required"/>
<label for="studyAreaId"><fmt:message key="studyAreaId"/></label>
<input type="text" name="studyAreaId" maxlength="10" required="required"/>
<label for="description"><fmt:message key="description"/></label>
<input type="text" name="description" maxlength="100" required="required"/>
<input type="submit" value="<fmt:message key="save"/>">
</fieldset>
</form>
<%@include file="/jsp/fotter.jsp" %>
</body></html>