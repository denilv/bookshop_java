<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Book</title>
</head>
<body>
	<h1>Edit Book</h1>
	<c:url var="saveUrl" value="/books/edit?id=${bookAttribute.id}" />
	<form:form modelAttribute="bookAttribute" method="POST"
		action="${saveUrl}">
		<table>
			<tr>
				<td><form:label path="id">Id:</form:label></td>
				<td><form:input path="id" disabled="true" /></td>
			</tr>

			<tr>
				<td><form:label path="title">Title:</form:label></td>
				<td><form:input path="title" /></td>
			</tr>

			<tr>
				<td><form:label path="price">Price:</form:label></td>
				<td><form:input path="price" /></td>
			</tr>

			<tr>
				<td><form:label path="availableAmount">Available amount:</form:label></td>
				<td><form:input path="availableAmount" /></td>
			</tr>
			<tr>
				<td>Genre :</td>
				<td>
					<form:select path="genre" items="${allgenres}" itemLabel="name" itemValues="id"/>
				</td>
			</tr>
			<tr>
				<td>Publishing House :</td>
				<td>
					<form:select path="publHouse" items="${allpublhouses}" itemLabel="name" itemValues="id"/>
				</td>
			</tr>
			<tr>
				<td>Authors :</td>
				<td><form:checkboxes path="authors" items="${allauthors}"
						itemLabel="LName" itemValue="id" /></td>
			</tr>
		</table>

		<input type="submit" value="Save" />
	</form:form>
	<form action="/bookshop/books" align="middle">
		<input type="submit" value="Main page" />
</body>
</html>
