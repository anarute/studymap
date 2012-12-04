<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="ctrl" class="hackathom.studymap.jsp.controller.StudyGroupPostController" scope="request"/>
<!DOCTYPE html><html><head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><fmt:message key="studyGroupPostEdit"/></title></head>
    <body><h2><fmt:message key="studyGroupPostEdit"/></h2>
        <c:if  test="${not empty param.studyGroupPostId}">
            <c:out value="edit"/>
            <%
                String studyGroupPostId = request.getParameter("studyGroupPostId");
                System.out.println("StudyGroupPost : " + studyGroupPostId);
                ctrl.load(Integer.parseInt(studyGroupPostId));
            %>
        </c:if>
        <%@include file="/jsp/menu.jsp"%>
        <form name="studyGroupPost" action="studyGroupPost/salvar" method="POST">
            <fieldset>
                <label for="studyGroupPostId"><fmt:message key="studyGroupPostId"/></label>
                <input type="text" name="studyGroupPostId" maxlength="10" required="required"/>
                <label for="studyGroupId"><fmt:message key="studyGroupId"/></label>
                <input type="text" name="studyGroupId" maxlength="10" required="required" value="${ctrl.model.studyGroupId}"/>
                <label for="userId"><fmt:message key="userId"/></label>
                <input type="text" name="userId" maxlength="10" required="required" value="${ctrl.model.userId}"/>
                <label for="title"><fmt:message key="title"/></label>
                <input type="text" name="title" maxlength="144" required="required" value="${ctrl.model.title}"/>"
                <label for="content"><fmt:message key="content"/></label>
                <input type="text" name="content" required="required" value="${ctrl.model.content}"/>
                <label for="posted"><fmt:message key="posted"/></label>
                <input type="text" name="posted" required="required" value="<fmt:formatDate dateStyle="short" timeStyle="short" value="${ctrl.model.posted}"/>">
                <input type="submit" value="<fmt:message key="save"/>">
            </fieldset>
        </form>
        <%@include file="/jsp/fotter.jsp" %>
    </body>
</html>