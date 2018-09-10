<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%
		String message = (String)request.getAttribute("message");    
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>qnaErrorPage</title>
</head>
<body>
<h1>공지사항 서비스 오류 : </h1>
<% if(message != null) { %>
	<h3>에러 메세지 : <%= message %></h3>
<% }else{ %>
	<h3>예외 발생 : <%= exception.getMessage() %></h3>
<% } %>
<a href="/semi/index.jsp">시작페이지 이동</a>
</body>
</html>