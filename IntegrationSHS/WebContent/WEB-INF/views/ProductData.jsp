<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Product</title>
	</head>
	<body>
	    <s:if test="%{getProduct() != null}">
		    <!-- EDITAR UN PRODUCTO -->
		    <div class="product__edit">
		        <h2>Edit product</h2>
		        <s:form action="edit_product" method="post">
		           <table>
		               <s:hidden name="product.id" value="%{getProduct().getId()}"/>
		               <s:hidden name="product.userId" value="%{getProduct().getUserId()}"/>
			           <tr>
			           		<td>
			                    <s:textfield label="Name" name="product.name" value="%{getProduct().getName()}"/> 
			           		</td>
			           </tr>
			           <tr>
			           		<td><s:textarea label="Description" name="product.description" value="%{getProduct().getDescription()}"/></td>
			           </tr>
			           <tr>
			           		<td><s:textfield label="Price" name="product.price" type="number" value="%{getProduct().getPrice()}"/> </td>
			           </tr>
			           <tr>
			           	   <td><s:submit value="Update"/></td>
			           </tr>
		           </table>
	            </s:form>
	        </div>
        </s:if>
        <s:else>
		    <!-- CREAR UN NUEVO PRODUCTO -->
		    <div class="product__new">
			    <h2>New product</h2>
			    <s:form action="new_product" method="post">
			        <table>
			        	<tr>
			        		<td><s:textfield label="Name" name="product.name"/></td>
			        	</tr>
			        	<tr>
			        		<td><s:textarea label="Description" name="product.description"/></td>
			        	</tr>
			        	<tr>
			        		<td><s:textfield label="Price" type="number" name="product.price"/></td>
			        	</tr>
			        	<tr>
			        		<td><s:submit value="Add product"/></td>
			        	</tr>
			        </table>
			    </s:form>
		    </div>
	    </s:else>
	</body>
</html>