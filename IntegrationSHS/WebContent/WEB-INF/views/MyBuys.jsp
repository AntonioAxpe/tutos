<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Mis compras realizadas</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		<meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css?family=Oxygen" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script>
            $(function(){
            	$('.table__buy').on('click', function(){
            		
            		$('button.hidden').trigger('click');
            		
            		var id = $(this).data('id');
            		var contenido;
            		$('tbody.my_products').html('');
            		
            		$.getJSON('MyBuys', {oneBuySelect : id}, function(respond){
            			respond.detailsBuy;
            			
            			for (var i = 0; i < respond.detailsBuy.length; i++) {
                            contenido += '<tr>';							
	            			contenido +=     '<td>'+respond.detailsBuy[i].product.name+'</td>';
	            			contenido +=     '<td>'+respond.detailsBuy[i].quantity+'</td>';
	            			contenido +=     '<td>'+respond.detailsBuy[i].total+' €</td>';
	            			contenido += '</tr>';
						}
            			
            			$('tbody.my_products').html(contenido);
            		})
            	});
            });
        </script>
	</head>
	<body>
	    <div>
	        <h2>Listado de mis compras realizadas</h2>
	    </div>
	    <s:if test="%{!myBuys.isEmpty()}">
	    <div class="block_buys">
	        <table class="table table-hover">
	            <tr>
	            	<th>Nº</th>
	            	<th>Fecha</th>
	            	<th>Estado</th>
	            	<th>Total</th>
	            </tr>
	            <s:iterator value="myBuys" var="buy" status="num">
	                <s:if test="%{#buy.status == 'active'}">
			        	<tr class="table__buy" style="background-color: #2cca2c; color: white; font-weight: bold;" data-id='<s:property value="%{#buy.id}"/>'>
			        	    <td><s:property value="#num.count"/></td>
			        	    <td><s:date name="#buy.date" format="dd/MM/yyyy"/></td>
			        	    <td><s:property value="#buy.status"/></td>
			        	    <td><s:property value="#buy.total"/></td>
			        	</tr>
	                </s:if>
	                <s:else>
			        	<tr class="table__buy" data-id='<s:property value="%{#buy.id}"/>'>
			        	    <td><s:property value="#num.count"/></td>
			        	    <td><s:date name="#buy.date" format="dd/MM/yyyy"/></td>
			        	    <td><s:property value="#buy.status"/></td>
			        	    <td><s:property value="#buy.total"/></td>
			        	</tr>
	                </s:else>
	            </s:iterator>
	        </table>
	    </div>
	    </s:if>
	    <s:else>
	    <div class="block_buys">
	        <h3>Listado vacio</h3>
	    </div>
	    </s:else>
	    <div>
	        <button type="button" class="hidden" data-toggle="modal" data-target=".bs-example-modal-sm">Small modal</button>
	        <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
			  <div class="modal-dialog modal-sm" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
				      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				      <h4 class="modal-title">Modal title</h4>
			      </div>
			      <div class="modal-body">
				      <table class="table table-bordered">
				        <thead>
					      	<tr>
					      		<th>Producto</th>
					      		<th>Cantidad</th>
					      		<th>Total</th>
					      	</tr>
				        </thead>
				        <tbody class="my_products">
				        </tbody>
				      </table>
			      </div>
			      <div class="modal-footer">
			         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			      </div>
			    </div>
			  </div>
			</div>
	    </div>
    </body>
</html>