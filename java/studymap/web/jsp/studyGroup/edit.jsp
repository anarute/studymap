<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="ctrl" class="hackathom.studymap.jsp.controller.StudyGroupController" scope="request"/>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><fmt:message key="studyGroupEdit"/></title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        <link rel="stylesheet" href="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.css" />
        <link rel="stylesheet" href="../css/main.css" />
        <script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
        <script src="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.js"></script>
        <script src="../js/main.js"></script>
        
        <!--meta name=viewport content='width=device-width'-->
        <link rel="stylesheet" href="../css/font-awesome.css">
        <script type="text/javascript" src="../js/map.js"></script>
</head>
    <body><h2><fmt:message key="studyGroupEdit"/></h2>
        <c:if  test="${not empty param.studyAreaId}">
            <c:out value="edit"/>
            <%
                String studyGroupId = request.getParameter("studyGroupId");
                System.out.println("StudyGroup : " + studyGroupId);
                ctrl.load(Integer.parseInt(studyGroupId));
            %>
        </c:if>
        <c:if test="${empty param.studyAreaId}">
            <c:out value="new"/>
        </c:if>
                      
<%@include file="/jsp/menu.jsp"%>
<form name="studyGroup" action="studyGroup/salvar" method="POST">
<fieldset>
<div data-role="fieldcontain">
    <label for="studyGroupId"><fmt:message key="studyGroupId"/></label>
    <input type="text" name="studyGroupId" maxlength="10" required="required" value="${ctrl.model.studyGroupId}"/>
</div>
<div data-role="fieldcontain">
    <label for="ownerId"><fmt:message key="ownerId"/></label>
    <input type="text" name="ownerId" maxlength="10" required="required" value="${ctrl.model.ownerId}"/>
</div>
<div data-role="fieldcontain">
    <label for="studySubjectId"><fmt:message key="studySubjectId"/></label>
<input type="text" name="studySubjectId" maxlength="10" required="required" value="${ctrl.model.studySubjectId}">
</div>
<div data-role="fieldcontain">
    <label for="description"><fmt:message key="description"/></label>
<input type="text" name="description" maxlength="255" required="required" value="${ctrl.model.description}"/>
</div>
<div data-role="fieldcontain">
    <label for="longitude"><fmt:message key="longitude"/></label>
<input type="text" name="longitude" maxlength="15" required="required" value="<fmt:formatNumber minFractionDigits="6" value="${ctrl.model.longitude}"/>"/>
</div>
<div data-role="fieldcontain">
    <label for="latitude"><fmt:message key="latitude"/></label>
<input type="text" name="latitude" maxlength="15" required="required" value="<fmt:formatNumber minFractionDigits="6" value="${ctrl.model.latitude}"/>"/>
</div>

<input type="submit" name="submit" data-theme="b" value="<fmt:message key="save"/>">
</fieldset>
</form>
</body></html>