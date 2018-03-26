<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>students list</title>
</head>
<body>
	<h1>students list</h1>
	<hr>
	<a href="addStudent">add Student</a>
	<table border="1">
		<thead>
			<tr>
				<th><spring:message code="student.form.index" /></th>
				<th><spring:message code="student.form.id" /></th>
				<th><spring:message code="student.form.name" /></th>
				<th><spring:message code="student.form.clazz" /></th>
				<th><spring:message code="student.form.birthday" /></th>
				<th><spring:message code="student.form.createTime" /></th>
				<th><spring:message code="student.form.modifyTime" /></th>
				<th colspan="2"><spring:message code="student.form.operation" /></th>
			</tr>
		</thead>
		<tbody>
			<c:set var="results" value="${studentDatas.result }"></c:set>
			<c:set var="pagination" value="${studentDatas.pagination }"></c:set>
			<c:forEach items="${results }" var="stu" varStatus="status">
				<tr>
					<td>${status.index+1 }</td>
					<td>${stu.id }</td>
					<td>${stu.name }</td>
					<td>${stu.clazz }</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${stu.birthday }" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${stu.createTime }" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${stu.modifyTime }" /></td>
					<td><a href="modifyStudent?id=${stu.id }"><spring:message code="student.form.update" /></a></td>
					<td><a href="deleteStudent?id=${stu.id }"><spring:message code="student.form.delete" /></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:set var="currentPage" value="${pagination.currentPage }"></c:set>
	<c:if test="${currentPage>1 }">
		<a href="loadStudentsByFields?currentPage=${currentPage-1 }">
		<spring:message code="pre.page" /></a>&nbsp;
		</c:if>
	${currentPage}&nbsp;
	<c:if test="${currentPage<pagination.totalPage }">
		<a href="loadStudentsByFields?currentPage=${currentPage+1 }">
		<spring:message code="next.page" /></a>
	</c:if>
</body>
</html>