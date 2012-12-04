<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="ctrl" class="hackathom.studymap.jsp.controller.StudyGroupMemberController" scope="request"/>
<!DOCTYPE html><html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="studyGroupMemberEdit"/></title></head>
<body><h2><fmt:message key="studyGroupMemberEdit"/></h2>
    <c:if  test="${not empty param.studyAreaId}">
        <c:out value="edit"/>
        <%
            String studyGroupMemeberId = request.getParameter("studyGroupMemeberId");
            System.out.println("StudyGroupMember : " + studyGroupMemeberId);
            ctrl.load(Integer.parseInt(studyGroupMemeberId));
        %>
    </c:if>
<%@include file="/jsp/menu.jsp"%>
<form name="studyGroupMember" action="studyGroupMember/salvar" method="POST">
<fieldset>
<label for="studyGroupMemeberId"><fmt:message key="studyGroupMemeberId"/></label>
<input type="text" name="studyGroupMemeberId" maxlength="10" required="required" value="${ctrl.model.studyGroupMemeberId}"/>
<label for="studyGroupId"><fmt:message key="studyGroupId"/></label>
<input type="text" name="studyGroupId" maxlength="10" required="required" value="${ctrl.model.studyGroupId}"/>
<label for="userId"><fmt:message key="userId"/></label>
<input type="text" name="userId" maxlength="10" required="required" value="${ctrl.model.userId}"/>
<input type="submit" value="<fmt:message key="save"/>">
</fieldset>
</form>
<%@include file="/jsp/fotter.jsp" %>
</body></html>