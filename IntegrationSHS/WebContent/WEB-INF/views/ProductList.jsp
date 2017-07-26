<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	    <s:if test="%{!getMyProducts().isEmpty()}">
            <h2>My Products List</h2>
            <table width="80%" border="1" style="border-collapse: collapse;">
            	<tr>
            		<th>Nº</th>
            		<th>Product Name</th>
            		<th>Description</th>
            		<th>Price</th>
            	</tr>
            	<s:iterator value="myProducts" status="status">
            	<tr>
            		<td><s:property value="#status.count"/></td>
            		<td><s:property value="name" /></td>
            		<td><s:property value="description" /></td>
                    <td><s:property value="price" /></td>
            	</tr>
            	</s:iterator>
            </table>
	    </s:if>
	    <s:else>
		    <h2>Product List</h2>
		    <div class="products__buttons">
		       <a href="new_product"><button>New Product</button></a>
		    </div>
		    <div class="products__list">
		        <table width="80%" border="1" style="border-collapse: collapse;">
		            <tr>
		                <th>No</th>
		                <th>Product Name</th>
		                <th>Description</th>
		                <th>Price</th>
		                <th>Options</th>
		            </tr>
		            <s:iterator var="prod" value="listProduct" status="stat">
		                <tr>
		                    <td><s:property value="#stat.count" /></td>
		                    <td><s:property value="name" /></td>
		                    <td><s:property value="description" /></td>
		                    <td><s:property value="price" /></td>
		                    <td>
		                        <a href="edit_product?id=<s:url value="%{id}"/>"><button>Edit</button></a>
		                        <a href="remove_product?id=<s:url value="%{id}"/>"><button>Delete</button></a>
		                    </td>
		                </tr>
		            </s:iterator>        
		        </table>
		    </div>
	    </s:else>
	</body>
</html>