<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>My products</title>
	</head>
	<body>
	    <h2>My Cart</h2>

	    <s:if test="%{!getMyCartList().isEmpty()}">

	    <table width="90%" border="1" style="border-collapse: collapse;">
	    	<tr>
	    	    <th></th>
	    		<th>Product name</th>
	    		<th>Quantity</th>
	    		<th>Price</th>
	    		<th></th>
	    	</tr>
	    	<s:iterator value="myCartList" status="num">
	    	<tr>
	    		<td><s:property value="#num.count"/></td>
	    		<td><s:property value="primaryKey.Product.name"/></td>
	    		<td><s:property value="quantity"/></td>
	    		<td align="right"><s:property value="total"/> €</td>
	    		<td>
	    		    <a href="MyCart?action=delete&id=<s:url value="%{primaryKey.Product.id}"/>"><button>Delete</button></a>
	    		    <a href="MyCart?action=edit&id=<s:url value="%{primaryKey.Product.id}"/>"><button>Edit</button></a>
	    		</td>
	    	</tr>
	    	</s:iterator>
	    	<tr>
	    	    <td align="right" colspan="3">Total</td>
	    		<td align="right"><s:property value="totalMyCart" /> €</td>
	    	</tr>
	    </table>

        <a href="listProduct"><button>Back to the shop</button></a>

	    </s:if>	    
	    
	</body>
</html>