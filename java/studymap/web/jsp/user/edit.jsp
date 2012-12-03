<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%><jsp:useBean id="bancoView" class="br.com.jcomputacao.convivere.view.BancoWeb" scope="request"/>
<!DOCTYPE html><html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="userEdit"/></title></head>
<body><h2><fmt:message key="userEdit"/></h2>
<%@include file="/jsp/menu.jsp"%>
<form name="user" action="user/salvar" method="POST">
<fieldset>
<label for="userId"><fmt:message key="userId"/></label>
<input type="text" name="userId" maxlength="10" required="required"/>
<label for="login"><fmt:message key="login"/></label>
<input type="text" name="login" maxlength="255" required="required"/>
<label for="password"><fmt:message key="password"/></label>
<input type="text" name="password" maxlength="255" required="required"/>
<label for="name"><fmt:message key="name"/></label>
<input type="text" name="name" maxlength="255" required="required"/>
<label for="birthday"><fmt:message key="birthday"/></label>
<input type="text" name="birthday"/>
<label for="posts"><fmt:message key="posts"/></label>
<input type="text" name="posts" maxlength="1"/>
<label for="gold"><fmt:message key="gold"/></label>
<input type="text" name="gold" maxlength="17"/>
<input type="submit" value="<fmt:message key="salvar"/>">
</fieldset>
</form>
</body></html>