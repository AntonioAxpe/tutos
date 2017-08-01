<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css?family=Oxygen" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/index.css">
	</head>
	<body>
	    <!-- MI LISTA DE PRODUCTOS -->
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
	    
	    <!-- TODOS LOS PRODUCTOS -->
	    <s:else>
            <div class="product-list">
		        <h2>Product List</h2>
    		    <div class="products__actions">
    		       <a href="new_product"><button>Create New Product</button></a>
    		    </div>
    		    <div class="products__list">
    		        <table class="table_list" border="1">
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
		                        <div style="display: inline-block; width: 40%; text-align: center;">
			                        <a href="edit_product?id=<s:url value="%{id}"/>"><button>Edit</button></a>
			                        <a href="remove_product?id=<s:url value="%{id}"/>"><button>Delete</button></a>
		                        </div>
		                        <div style="display: inline-block; width: 40%; text-align: right;">
			                        <a href="buy_product?id=<s:url value="%{id}"/>"><button>Add to my cart</button></a>
		                        </div> 
		                    </td>
		                </tr>
    		            </s:iterator>        
    		        </table>
    		    </div>
    		    <div class="products__redirect">
    		        <a href="MyCart"><button>My Cart</button></a>
    		    </div>
            </div>
	    </s:else>
	</body>
</html>