<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Login User</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	  	<meta name="viewport" content="width=device-width, initial-scale=1">
	  	<link href="https://fonts.googleapis.com/css?family=Oxygen" rel="stylesheet">
	  	<link rel="stylesheet" type="text/css" href="css/index.css">
	</head>
	<body>
	    <div class="login__tittle">
    		<h2>Proyecto de integración</h2>
    		<h3>Spring 4, Hibernate3 y Struts2</h3>
	    </div>
	    <div class="login__form">
    		<h2>Login</h2>
	        <s:form action="Inicio" method="POST">
		       	<div class="form-group">
	       	  		<s:textfield type="text" class="form-control" name="user.username" label="Username" placeholder="Username"/>
			    </div>
			    <div class="form-group">
		       		<s:password class="form-control" label="Password" name="user.password" placeholder="Password"/>
			    </div>
			    <div class="form-group">
		       		<s:submit value="LogIn" class="btn btn-default" />
			    </div>
	        </s:form>
	    </div>
	    <s:if test="%{#session.login == 'false'}">
	        <% session.removeAttribute("login"); %>
	        <p>No existe el usuario</p>
	    </s:if>
	</body>
</html>