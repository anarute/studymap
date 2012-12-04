<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="ctrl" class="hackathom.studymap.jsp.controller.StudyGroupScheduleController" scope="request"/>
<!DOCTYPE html><html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="studyGroupScheduleEdit"/></title></head>
<body><h2><fmt:message key="studyGroupScheduleEdit"/></h2>
    <c:if  test="${not empty param.studyGroupScheduleId}">
        <c:out value="edit"/>
        <%
            String studyGroupScheduleId = request.getParameter("studyGroupScheduleId");
            System.out.println("StudyGroup : " + studyGroupScheduleId);
            ctrl.load(Integer.parseInt(studyGroupScheduleId));
        %>
    </c:if>
<%@include file="/jsp/menu.jsp"%>
<form name="studyGroupSchedule" action="studyGroupSchedule/salvar" method="POST">
<fieldset>
<label for="studyGroupScheduleId"><fmt:message key="studyGroupScheduleId"/></label>
<input type="text" name="studyGroupScheduleId" maxlength="10" required="required"/>
<label for="studyGroupId"><fmt:message key="studyGroupId"/></label>
<input type="text" name="studyGroupId" maxlength="10" required="required"/>
<label for="dayOfWeek"><fmt:message key="dayOfWeek"/></label>
<input type="text" name="dayOfWeek" maxlength="1"/>
<label for="year"><fmt:message key="year"/></label>
<input type="text" name="year" maxlength="4"/>
<label for="month"><fmt:message key="month"/></label>
<input type="text" name="month" maxlength="2"/>
<label for="dayOfMonth"><fmt:message key="dayOfMonth"/></label>
<input type="text" name="dayOfMonth" maxlength="2"/>
<label for="hour"><fmt:message key="hour"/></label>
<input type="text" name="hour" maxlength="2" required="required"/>
<label for="minute"><fmt:message key="minute"/></label>
<input type="text" name="minute" maxlength="2" required="required"/>
<input type="submit" value="<fmt:message key="save"/>">
</fieldset>
</form>
<%@include file="/jsp/fotter.jsp" %>
</body></html>