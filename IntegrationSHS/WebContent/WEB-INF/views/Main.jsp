<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Main menú</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css?family=Oxygen" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/index.css">
	</head>
	<body class="main">
	    <div class="main__head">
		    <h2>Welcome <%= session.getAttribute("name") %></h2>
	    </div>
	    <div class="main__logout">
    	    <a href="LogOut"><button class="btn btn-danger">SignOut</button></a>
	    </div>
	    <div class="main__option">
		    <p>Choose a option</p>
		    <ul>
		    	<li><a href="MyProducts?userId=<%= session.getAttribute("user_id") %>">My products</a></li>
		    	<li><a href="listProduct">All products</a></li>
		    	<li><a href="MyListBuy">My list buy</a></li>
		    </ul>
	    </div>
	</body>
</html>