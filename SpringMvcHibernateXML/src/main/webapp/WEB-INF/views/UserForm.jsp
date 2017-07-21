<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>New or Edit User</title>
		<script src="https://code.jquery.com/jquery-3.2.1.min.js"
			integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
			crossorigin="anonymous"></script>
	</head>
	<body>
		<c:if test="${formTable == 'user'}">
			<div>
				<h1>New/Edit User</h1>
				<table>
					<form:form action="saveUser" method="GET" modelAttribute="user">
						<form:hidden path="id" />
						<tr>
							<td>Username:</td>
							<td><form:input path="username" /></td>
						</tr>
						<tr>
							<td>Email:</td>
							<td><form:input path="email" /></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><form:password path="password" /></td>
						</tr>
						<tr>
							<td>Favourites Sport</td>
							<td>
							    <!-- INSERTAR -->
							    <c:if test="${(user.sports == null) || (empty user.sports) }">
							        <form:select path="sports" items="${SportList}" multiple="true" itemValue="id" itemLabel="name"/>
							        
							        <!--<form:select path="sports">
							            <c:forEach items="${SportList}" var="s">
							                <form:option value="${s.id}">${s.name}</form:option>
							            </c:forEach>
							        </form:select>-->
							    </c:if>
							    
							    <!-- EDITAR -->
							    <c:if test="${not empty user.sports}">
							        <form:checkboxes items="${user.sports}" path="sports" itemValue="id" itemLabel="name"/>
							    </c:if>							    
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="submit"	value="saveUser"></td>
						</tr>
					</form:form>
				</table>
			</div>
		</c:if>
		<c:if test="${formTable == 'sport'}">
			<div class="formSport">
				<h2>Edit Sport</h2>
				<form:form action="saveSport" method="POST" modelAttribute="sport">
					<form:hidden path="id" />
					<table>
						<tr>
							<td>Name</td>
							<td><form:input type="text" path="name" /></td>
						</tr>
						<tr>
							<td><input type="submit" value="Save" /></td>
							<td><a href="/"><button>Cancel</button></a></td>
						</tr>
					</table>
				</form:form>
			</div>
		</c:if>
	</body>
</html>