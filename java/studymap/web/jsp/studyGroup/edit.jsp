<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%><jsp:useBean id="bancoView" class="br.com.jcomputacao.convivere.view.BancoWeb" scope="request"/>
<!DOCTYPE html><html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="studyGroupEdit"/></title></head>
<body><h2><fmt:message key="studyGroupEdit"/></h2>
<%@include file="/jsp/menu.jsp"%>
<form name="studyGroup" action="studyGroup/salvar" method="POST">
<fieldset>
<label for="studyGroupId"><fmt:message key="studyGroupId"/></label>
<input type="text" name="studyGroupId" maxlength="10" required="required"/>
<label for="ownerId"><fmt:message key="ownerId"/></label>
<input type="text" name="ownerId" maxlength="10" required="required"/>
<label for="studySubjectId"><fmt:message key="studySubjectId"/></label>
<input type="text" name="studySubjectId" maxlength="10" required="required"/>
<label for="description"><fmt:message key="description"/></label>
<input type="text" name="description" maxlength="255" required="required"/>
<label for="longitude"><fmt:message key="longitude"/></label>
<input type="text" name="longitude" maxlength="15" required="required"/>
<label for="latitude"><fmt:message key="latitude"/></label>
<input type="text" name="latitude" maxlength="15" required="required"/>
<input type="submit" value="<fmt:message key="salvar"/>">
</fieldset>
</form>
</body></html>