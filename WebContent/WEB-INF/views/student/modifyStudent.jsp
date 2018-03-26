<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>edit student</title>
</head>
<body>
	<h1>edit student</h1>
	<hr>
	<form action="modifyStudent" method="post">
		<input type="hidden" name="id" value="${studentData.id }" >
		<spring:message code="student.form.name" />
		:<input type="text" name="name" value="${studentData.name }" ><br>
		<spring:message code="student.form.clazz" />
		:<input type="text" name="clazz" value="${studentData.clazz }" ><br>
		<spring:message code="student.form.birthday" />
		:<input name="birthday" value="${studentData.birthday }" ><br>
		<input type="submit" value='<spring:message code="student.form.update" />' >
	</form>
</body>
</html>