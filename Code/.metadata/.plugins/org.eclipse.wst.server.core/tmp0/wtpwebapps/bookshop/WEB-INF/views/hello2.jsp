<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<%@ page import="java.util.List"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page import="net.artofz.hibernate.Pokusaj"%> 
<%@ page import="net.artofz.test.JdbcDemo"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<title>Insert title here</title> 
</head> 
<body> 

<% 
Pokusaj pokusaj = new Pokusaj(); 
List list = pokusaj.getActorsByID(); 
request.setAttribute("database", list); 

%> 


<table border=1> 
<c:forEach var="row" items="${database}"> 
<tr> 
<td><cut value="${row.userId}"/></td> 
<td><cut value="${row.userName}"/></td> 
</tr> 
</c:forEach> 
</table> 

</body> 
</html>