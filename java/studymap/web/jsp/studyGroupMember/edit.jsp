<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%><jsp:useBean id="bancoView" class="br.com.jcomputacao.convivere.view.BancoWeb" scope="request"/>
<!DOCTYPE html><html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="studyGroupMemberEdit"/></title></head>
<body><h2><fmt:message key="studyGroupMemberEdit"/></h2>
<%@include file="/jsp/menu.jsp"%>
<form name="studyGroupMember" action="studyGroupMember/salvar" method="POST">
<fieldset>
<label for="studyGroupMemeberId"><fmt:message key="studyGroupMemeberId"/></label>
<input type="text" name="studyGroupMemeberId" maxlength="10" required="required"/>
<label for="studyGroupId"><fmt:message key="studyGroupId"/></label>
<input type="text" name="studyGroupId" maxlength="10" required="required"/>
<label for="userId"><fmt:message key="userId"/></label>
<input type="text" name="userId" maxlength="10" required="required"/>
<input type="submit" value="<fmt:message key="salvar"/>">
</fieldset>
</form>
</body></html>