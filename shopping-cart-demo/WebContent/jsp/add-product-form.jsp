<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add Product Form</title>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2> Add Product Page</h2>
			<link type="text/css" rel="stylesheet" href="css/style.css">
			<link type="text/css" rel="stylesheet" href="css/add-student-style.css" >
		</div>
	</div>
	
	<div id="container">
		<h3>Add Product</h3>
		
		<form action="../ShoppingCartControllerServlet" method="POST"> 
			<input type="hidden" name="command" value="ADDPRODUCT" />
			
			<table>
				<tbody>
					<tr>
						<td><lable>Product ID:</lable></td>
						<td><input type="text" name="productId" /></td>
					</tr>
					<tr>	
						<td><lable>Product Name:</lable></td>
						<td><input type="text" name="productName" /></td>
					</tr>
					<tr>
						<td><lable>Price:</lable></td>
						<td><input type="text" name="price" /></td>
					</tr>
					<tr>
						<td><lable>Quantity:</lable></td>
						<td><input type="text" name="quantity" /></td>
					</tr>
					<tr>
						<td><lable></lable></td>
						<td><input type="submit" value="save" class="save"/></td>
					</tr>
				</tbody>
			</table>
			
		</form>
		
			<div style="clear: both;"></div>
			<p>
				<a href="../login.jsp"> Back to List</a>
			</p>
	
	</div>


</body>
</html>