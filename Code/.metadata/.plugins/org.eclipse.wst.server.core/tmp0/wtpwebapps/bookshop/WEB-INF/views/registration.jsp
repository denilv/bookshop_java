<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 400px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
</head>
<body>
	<div id="login-box">
	<h1>Registration</h1>
	<form:form modelAttribute="customer" method="POST" action="registration">
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<table>
			<tr>
				<td><form:label path="FName">First Name:</form:label></td>
				<td><form:input path="FName" maxlength="40"/></td>
			</tr>
			<tr>
				<td><form:label path="LName">Last Name:</form:label></td>
				<td><form:input path="LName" maxlength="40"/></td>
			</tr>
			<tr>
				<td><form:label path="MName">Middle Name:</form:label></td>
				<td><form:input path="MName" maxlength="40"/></td>
			</tr>
			
			<tr>
				<td><form:label path="contactNumber">Contact Number <br>(like "+71234567890"):</form:label></td>
				<td><form:input path="contactNumber" maxlength="12"/></td>
			</tr>
			
			<tr>
				<td><form:label path="email">Email:</form:label></td>
				<td><form:input path="email" maxlength="40"/></td>
			</tr>
			
			<tr>
				<td><form:label path="password">Password <br>(8-20 characters):</form:label></td>
				<td><form:input path="password" maxlength="20"/></td>
			</tr>
			<tr>
				<td><form:label path="address">Address:</form:label></td>
				<td><form:input path="address" maxlength="100"/></td>
			</tr>
		</table>
		<input type="submit" value="Register" />
	</form:form>
	</div>
</body>
</html>
