<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%><jsp:useBean id="bancoView" class="br.com.jcomputacao.convivere.view.BancoWeb" scope="request"/>
<!DOCTYPE html><html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="studySubjectEdit"/></title></head>
<body><h2><fmt:message key="studySubjectEdit"/></h2>
<%@include file="/jsp/menu.jsp"%>
<form name="studySubject" action="studySubject/salvar" method="POST">
<fieldset>
<label for="studySubjectId"><fmt:message key="studySubjectId"/></label>
<input type="text" name="studySubjectId" maxlength="10" required="required"/>
<label for="studyMainSubjectId"><fmt:message key="studyMainSubjectId"/></label>
<input type="text" name="studyMainSubjectId" maxlength="10" required="required"/>
<label for="description"><fmt:message key="description"/></label>
<input type="text" name="description" maxlength="100" required="required"/>
<input type="submit" value="<fmt:message key="salvar"/>">
</fieldset>
</form>
</body></html>