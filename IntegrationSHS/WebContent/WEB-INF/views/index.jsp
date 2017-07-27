<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Login User</title>
	</head>
	<body>
	    <h2>Login</h2>
	    <div class="login">
	        <s:form action="Inicio" method="POST">
		       	<table>
		       		<tr>
		       			<td><s:textfield label="Username" name="user.username" /></td>
		       		</tr>
		       		<tr>
		       			<td><s:password label="Password" name="user.password" /></td>
		       		</tr>
		       		<tr>
		       			<td><s:submit value="LogIn" /></td>
		       		</tr>
		       	</table>
	        </s:form>
	    </div>
	    <s:if test="%{#session.login == 'false'}">
	        <% session.removeAttribute("login"); %>
	        <p>No existe el usuario</p>
	    </s:if>
	</body>
</html>