<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
	<table style="border: 1px solid; width: 1000px; text-align: left">
		<thead style="background: #fcf">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Books</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${authors}" var="author">
				<tr>
					<td><c:out value="${author.id}" /></td>
					<td><c:out value="${author.toString()}" /></td>
					<td>
						<c:forEach items="${author.books}" var="book">
							<c:out value="${book.title};" />
						</c:forEach>
					</td>
					<td><a href="/bookshop/authors/edit?id=${author.id}">Edit</a></td>
					<td><a href="/bookshop/authors/delete?id=${author.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
<form action="/bookshop/authors/add" align="middle">
	<input type="submit" value="Add author" />
</body>
</html>