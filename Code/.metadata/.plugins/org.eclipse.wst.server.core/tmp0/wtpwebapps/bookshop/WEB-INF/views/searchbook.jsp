<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Book</title>
</head>
<body>
	<h1>Search Book</h1>
	<form:form modelAttribute="bookCriteria" method="POST" action="search">
		<table>
			<tr>
				<td><form:label path="title">Title:</form:label></td>
				<td><form:input path="title" /></td>
			</tr>

			<tr>
				<td>Genre :</td>
				<td>
				<form:select path="genre" title="genre">
						<form:option value="" label="(None)"/>
						<form:options items="${allgenres}" itemValue="id" itemLabel="name" />
				</form:select>
				</td>
			</tr>
			<tr>
				<td>Author :</td>
				<td>
                	<form:select path="author" title="author">
						<form:option value="" label="(None)"/>
						<form:options items="${allauthors}" itemValue="id" itemLabel="LName" />
					</form:select>
				</td>
			</tr>
		</table>
		<input type="submit" value="Search" />
	</form:form>
</body>
</html>
