<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="bancoView" class="br.com.jcomputacao.convivere.view.BancoWeb" scope="request"/>
<!DOCTYPE html><html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="studyAreaEdit"/></title></head>
<body><h2><fmt:message key="studyAreaEdit"/></h2>
<%@include file="/jsp/menu.jsp"%>
<form name="studyArea" action="studyArea/salvar" method="POST">
<fieldset>
<label for="studyAreaId"><fmt:message key="studyAreaId"/></label>
<input type="text" name="studyAreaId" maxlength="10" required="required"/>
<label for="description"><fmt:message key="description"/></label>
<input type="text" name="description" maxlength="100" required="required"/>
<input type="submit" value="<fmt:message key="salvar"/>">
</fieldset>
</form>
</body></html>