<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage = "true"%>
     <%
    String message = (String)request.getAttribute("message");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>errorPage</title>
</head>
<body>
<h1>회원 서비스 에러 발생하였습니다.</h1>
<h3>에러 메세지 : <%= message %></h3>
</body>
</html>