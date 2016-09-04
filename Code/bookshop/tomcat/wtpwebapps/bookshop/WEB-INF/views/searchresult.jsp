<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Book</title>
</head>
<body>
<c:if test="${not empty books}">
    <table style="border: 1px solid; width: 1000px; text-align: left">
		<thead style="background: #fcf">
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Amount</th>
				<th>Price</th>
				<th>Authors</th>
				<th>Genre</th>
				<th>Publisher</th>
				<th colspan="3"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${books}" var="book">
				<tr>
					<td><c:out value="${book.id}" /></td>
					<td><c:out value="${book.title}" /></td>
					<td><c:out value="${book.availableAmount}" /></td>
					<td><c:out value="${book.price}" /></td>
					<td>
						<c:forEach items="${book.authors}" var="author">
							<c:out value="${author.toString()};" />
						</c:forEach>
					</td>
					<td><c:out value="${book.genre.name}" /></td>
					<td><c:out value="${book.publHouse.name}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>
</body>
</html>
