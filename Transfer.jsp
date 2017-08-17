<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transfer Money</title>
</head>
<body>
	
	<%
		if (session.getAttribute("username") == null) {
			response.sendRedirect("login.jsp");
		}
	%>
	Transfer money from one account to another:
	<form action="Transfer">
	Account Number(From):<input type="text" name="accno1"><br>
	<br>
	Account Number(To):<input type="text" name="accno2"><br>
	<br>
	Amount to be transfered:<input type="text" name="amt"><br>
	<br>
	
	<br>
	<br>
	<input type="submit" value="Transfer">
	</form>
	<br>
	<br>
</body>
</html>