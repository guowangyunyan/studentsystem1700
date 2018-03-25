<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add student</title>
</head>
<body>
	<h1>add student</h1>
	<hr>
	<form action="addStudent" method="post">
		<spring:message code="student.form.name" />
		:<input type="text" name="name"><br>
		<spring:message code="student.form.clazz" />
		:<input type="text" name="clazz"><br>
		<spring:message code="student.form.birthday" />
		:<input type="date" name="birthday"><br> 
		<input type="submit" value='<spring:message code="student.form.add" />'>
	</form>
</body>
</html>