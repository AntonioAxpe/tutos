<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>User main</title>
	</head>
	<body>
	    <h2>Welcome <%= session.getAttribute("name") %></h2>
	    <a href="LogOut"><button>SignOut</button></a>
	    <p>Choose a option</p>
	    <ul>
	    	<li><a href="MyProducts?userId=<%= session.getAttribute("user_id") %>">My products</a></li>
	    	<li><a href="listProduct">All products</a></li>
	    	<li><a href="MyListBuy">My list buy</a></li>
	    </ul>
	</body>
</html>