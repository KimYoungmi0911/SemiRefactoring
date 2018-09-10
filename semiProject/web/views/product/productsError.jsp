<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<% //scriptlet tag
	String message = (String)request.getAttribute("message");
%>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>productsError</title>
</head>
<body>

	<h1>물품 서비스 오류 : </h1>
	<% if(message != null) { %>
		<h3> 에러 메시지 : <%= message %> </h3>
	<% } else { %>
		<h3>예외 발생 : <%= exception.getMessage() %></h3>
	<% } %>
	<br>
	
	<a href="/p9/index.jsp">시작페이지로 이동</a>



</body>
</html>