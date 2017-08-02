<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Product</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css?family=Oxygen" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/index.css">
	</head>
	<body>
	    <s:if test="%{getProduct() != null}">
		    <!-- EDITAR UN PRODUCTO -->
		    <div class="product__new">
		        <h2>Edit product</h2>
		        <a href="listProduct"><button class="btn btn-danger">Cancel</button></a>
		        <s:form action="edit_product" method="post">
	               <s:hidden name="product.id" value="%{getProduct().getId()}"/>
	               <s:hidden name="product.userId" value="%{getProduct().getUserId()}"/>
		           <s:textfield label="Name" name="product.name" value="%{getProduct().getName()}"/> 
		           <s:textarea label="Description" name="product.description" value="%{getProduct().getDescription()}"/>
		           <s:textfield label="Price" name="product.price" type="number" value="%{getProduct().getPrice()}"/>
		           <s:submit class="btn btn-success" value="Update"/>
	            </s:form>
	        </div>
        </s:if>
        <s:else>
		    <!-- CREAR UN NUEVO PRODUCTO -->
		    <div class="product__new">
			    <h2>New product</h2>
			    <a href="listProduct"><button class="btn btn-danger">Cancel</button></a>
			    <s:form action="new_product" method="post">
			        <s:textfield label="Name" name="product.name"/>
			        <s:textarea label="Description" name="product.description"/></td>
			        <s:textfield label="Price" type="number" name="product.price"/></td>
			        <s:submit class="btn btn-success" value="Add product"/></td>
			    </s:form>
		    </div>
	    </s:else>
	</body>
</html>