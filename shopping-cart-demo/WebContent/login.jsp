<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

</head>
<body>

<div id = "wrapper">
		<div id="header">
			<h2> Login Page</h2>
				<link type="text/css" rel="stylesheet" href="css/style.css">
				<link type="text/css" rel="stylesheet" href="css/add-student-style.css" >
		</div>
	</div>
	
	<div border="1">
	<form action="ShoppingCartControllerServlet" method="POST">
		<input type="hidden" name="command" value="LOGIN">
		
        <fieldset style="width: 600px">
            <legend> Login to App </legend>
            <table style="border-spacing: 0; border-width: 0; padding: 0; border-width: 0;">
            	<p>${param.message}</p>
                <tr>
                    <td>User ID</td>
                    <td><input type="text" name="username" required="required" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" required="required" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Login" /></td>
                </tr>
                <tr></tr>
                <tr>
                	<td><a href="jsp/add-user-form.jsp">New User</a> </td>
                	<td><a href="jsp/forgot-password-form.jsp"> Forgot Password </a></td>
                </tr>
            </table>
            <br/><br/>
            <a href = "jsp/add-product-form.jsp">Add Products</a>
        </fieldset>
    </form>
	</div>
</body>
</html>