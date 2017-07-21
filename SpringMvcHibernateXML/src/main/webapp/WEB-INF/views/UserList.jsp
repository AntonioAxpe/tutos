<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <div>
            <h1>Users List</h1>
            <div class="users">
                <div class="users__button">
            	   <a href="newUser"><button>New User</button></a>
				</div>
                <div class="users__content">
					<table border="1" width="300" style="border-collapse:collapse"s>
					 <tr>
					     <th>No</th>
					     <th>Username</th>
					     <th>Email</th>
					     <th>Actions</th>
					 </tr>
					    <c:forEach var="user" items="${UserList}" varStatus="status">
					    <tr>
					        <td>${status.index + 1}</td>
					        <td>${user.username}</td>
					        <td>${user.email}</td>
					        <td width="100px" align="center">
					            <a href="editUser?id=${user.id}">Edit</a>
					            <a href="deleteUser?id=${user.id}">Delete</a>
					        </td>
					    </tr>
					    </c:forEach>             
					</table>
           	    </div>
            </div>
        </div>
        <div class="sports">
            <h2>Sports</h2>
            <div class="sport__buttons">
                <a href="newSport"><button>New Sport</button></a>
            </div>
            <div class="sport__content">
                <table border="1" width="300" style="border-collapse:collapse">
                    <tr>
                        <th>Nº sport</th>
                        <th>Sport name</th>
                        <th>Actions</th>
                    </tr>
                    <c:forEach var="sport" items="${SportList}">
                        <tr>
                            <td>${sport.id}</td>
                            <td>${sport.name}</td>
                            <td>
                                <a href="editSport?id=${sport.id}">Edit</a>
                                <a href="deleteSport?id=${sport.id}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>