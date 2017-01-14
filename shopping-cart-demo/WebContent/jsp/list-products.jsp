<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Products</title>
</head>
<body onload="productInfo.reset();">

	<div id = "wrapper">
		<div id="header">
			<form action action="ShoppingCartControllerServlet" method="GET">
				<input type="hidden" name="command" value="LIST" />
				<h2> Product List</h2>
				<link type="text/css" rel="stylesheet" href="css/style.css">
				<link type="text/css" rel="stylesheet" href="css/add-student-style.css" >
			</form>	
		</div>
		<div>
			<form action="ShoppingCartControllerServlet" method="POST" >
					<input type="hidden" name="command" value="LOAD" />
					<c:forEach begin="1" end="1" var="tempProduct" items="${PRODUCT_LIST }">
					<input type="submit" name="View Cart" value="View ${tempProduct.userId}'s Cart" />
					</c:forEach>
			</form>
		</div>
		<div id="logout">
			<form action="ShoppingCartControllerServlet" method="POST" >
					<input type="hidden" name="command" value="LOGOFF" />
					<input type="submit" name="LogOff" value="LogOff" />
					
			</form>
		</div>
		
	</div>
	
	

	<form action action="ShoppingCartControllerServlet" method="POST">
	<input type="hidden" name="command" value="LIST" />
	
	<div id="container">
	
		<div id="content">
	
				<table border="1" style="float: left;" >
				
					<tr>
						<th>  Product Id       </th>
						<th>  Product Name     </th>
						<th>  Product Price	   </th>	
						<th>  Quantity         </th>
					</tr>
					
					<c:forEach var="tempProduct" items="${PRODUCT_LIST }">
						<tr>
							<td>${tempProduct.productId } </td>
							<td>${tempProduct.productName }</td>
							<td>${tempProduct.productPrice }</td>
							<td>${tempProduct.productQuantity }</td>
						</tr>
						
					</c:forEach>
				</table>
			</form>
			<form id="productInfo" action="ShoppingCartControllerServlet" method="POST" autocomplete="off" >
					<input type="hidden" name="command" value="ADD" />
					
				<table style="float: left;" border="1">
				<tr>
				<th><h3>Add Products to Cart</h3></th>
					
				</tr>
				<tr>
					<td>Product ID</td>
					<td>
						<select name="productId">
							<c:forEach var="tempProduct" items="${PRODUCT_LIST }">
								<option value="${tempProduct.productId }">${tempProduct.productId }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>Desired Quantity</td> 
					<td><input type="text" name="desiredQuantity" /></td>
				</tr>
				<tr>
					<td>Press Add to Cart to add items</td>
					<td><input type="submit"  value="Add to Cart" 
					onclick="if(!(confirm('Are you sure?'))) return false" ></td>
				</tr>
				
					
					
					
				</table>
			</form>
			
			

		</div>
	
	</div>
	
</body>
</html>