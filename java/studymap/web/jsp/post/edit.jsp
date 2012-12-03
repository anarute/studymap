<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%><jsp:useBean id="bancoView" class="br.com.jcomputacao.convivere.view.BancoWeb" scope="request"/>
<!DOCTYPE html><html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="postEdit"/></title></head>
<body><h2><fmt:message key="postEdit"/></h2>
<%@include file="/jsp/menu.jsp"%>
<form name="post" action="post/salvar" method="POST">
<fieldset>
<label for="postId"><fmt:message key="postId"/></label>
<input type="text" name="postId" maxlength="10" required="required"/>
<label for="title"><fmt:message key="title"/></label>
<input type="text" name="title" maxlength="100" required="required"/>
<label for="content"><fmt:message key="content"/></label>
<input type="text" name="content"/>
<label for="instant"><fmt:message key="instant"/></label>
<input type="text" name="instant" required="required"/>
<input type="submit" value="<fmt:message key="salvar"/>">
</fieldset>
</form>
</body></html>