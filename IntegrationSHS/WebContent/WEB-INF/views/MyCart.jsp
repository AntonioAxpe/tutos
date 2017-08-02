<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>My products</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css?family=Oxygen" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
        <script type="text/javascript" src="js/mycart.js"></script>
	</head>
	<body class="my_cart">
        <div class="my_cart__title">
	        <h2>My Cart</h2>
        </div>
        <div class="my_cart__cart">
            
            <s:if test="%{buyPayed}">
                <h2>The buy has been payed successfully</h2>
                <div>
                    <a href="Inicio"><button>Back to main</button></a>
                    <a href="LogOut"><button>SigOut</button></a>
                </div>
            </s:if>
            <s:else>
			    <s:if test="%{!getMyCartList().isEmpty()}">
				    <table class="cart_table" border="1">
				    	<tr>
				    	    <th></th>
				    	    <th></th>
				    		<th>Product name</th>
				    		<th>Quantity</th>
				    		<th>Price</th>
				    		<th></th>
				    	</tr>
				    	<s:iterator value="myCartList" status="num">
				    	<tr class="cart_table__product">
				    	    <td><a href="MyCart?action=delete&id=<s:url value="%{primaryKey.Product.id}"/>">x</a></td>
				    		<td><s:property value="#num.count"/></td>
				    		<td><s:property value="primaryKey.Product.name"/></td>
				    		<td class="cart_table__product__quantity">
				    		    <span><s:property value="quantity"/></span>
				    		    <div class="cart_table__product__quantity__buttons">
					    		    <button class="btn_more">+</button>
					    		    <button class="btn_less">-</button>
				    		    </div>
			    		    </td>
				    		<td align="right"><s:property value="total"/> €</td>
				    	</tr>
				    	</s:iterator>
				    	<tr class="cart_table__product">
				    	    <td align="right" colspan="4">Total</td>
				    		<td align="right"><s:property value="totalMyCart" /> €</td>
				    	</tr>
				    </table>
				    <div class="cart__actions">
				        <a href="listProduct"><button>Back to the shop</button></a>
				        <a href="PayBuy"><button>Buy now!!</button></a>
				    </div>
			    </s:if>
			    <s:else>
			        <h3>Your cart is emtpy</h3>
			        <div class="cart__actions">
	                    <a href="listProduct"><button>Back to the shop</button></a>
	                </div>
			    </s:else>
            </s:else>
        </div>
	</body>
</html>