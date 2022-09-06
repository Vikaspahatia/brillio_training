<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="net.javaguides.jsp.tutorial.database.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="student"
		class="net.javaguides.jsp.tutorial.database.Student" />

	<jsp:setProperty property="*" name="student" />

	<p>Details you have entered:</p>
	First Name : <jsp:getProperty property="firstName" name="student"/><br>
	Last Name : <jsp:getProperty property="lastName" name="student"/><br>  
	Username : <jsp:getProperty property="username" name="student"/><br>  
	Password : <jsp:getProperty property="password" name="student"/><br>    
	College : <jsp:getProperty property="college" name="student"/><br>  
	Address : <jsp:getProperty property="address" name="student" /><br>
	Contact : <jsp:getProperty property="contact" name="student"/><br> <br>   
	<%
		StudentDao studDao = new StudentDao();
		int status = studDao.registerStudent(student);
		if (status > 0) {
			out.print("Student registration successful...");
		}
		
	%>
		
</body>
</html>