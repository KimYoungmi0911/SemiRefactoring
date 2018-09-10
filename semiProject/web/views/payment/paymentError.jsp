<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%
	String message = (String) request.getAttribute("message");
%>
<%@ include file="../../header.jsp" %>

<h1>결제 서비스 에러 발생하였습니다.</h1>
<h3>에러 메세지 : <%= message %></h3>

<%@ include file="../../footer.jsp" %>