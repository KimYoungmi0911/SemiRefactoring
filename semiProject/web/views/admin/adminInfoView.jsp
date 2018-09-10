<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="adminError.jsp"%>
     <%@ page import="semi.admin.model.vo.Admin, java.util.*" %>
     <%
    	Admin admin = (Admin)request.getAttribute("admin");
    %>
<%@ include file="../../adminHeader.jsp" %>

<script type="text/javascript">
	$(function(){
		//암호 입력 상자에서 포커스가 사라질 때 암호확인 처리함
		$("#pwd2").blur(function(){
			console.log("포커스가 사라짐...");
			var pwd1 = $("#pwd1").val();
			var pwd2 = $("#pwd2").val();
			
			if(pwd1 != pwd2){
				alert("암호와 암호확인이 일치하지 않습니다.");
				$("#pwd1").select();
			}			
		});
	});
	</script>
<div class="page-head"> 
            <div class="container">
                <div class="row">
                    <div class="page-head-content">
                        <h1 class="page-title">09 : <span class="orange strong">My Page</span></h1>               
                    </div>
                </div>
            </div>
        </div>
        <h1 align="center"><b><%= admin.getaName() %></b> 님의 정보</h1><br>
        <form action="/semi/aupdate" method="post">
        <table width="600" align="center" cellspacing="5" bgcolor="#3399ff">
        <tr><td><label>아이디</label></td>
		<td>
			<input type="text" name="aid" 
			value="<%= admin.getaId() %>" readonly>
		</td></tr>
		<tr><td><label>이 름</label></td>
		<td>
			<input type="text" name="aname" 
			value="<%= admin.getaName() %>" readonly>
		</td></tr>
		<tr><td><label>암 호</label></td>
		<td>
			<input type="password" name="apassword" id="pwd1"
			value="<%= admin.getaPassword() %>">
		</td></tr>
		<tr><td><label>암호확인</label></td>
		<td>
			<input type="password" id="pwd2">
		</td></tr>
		<tr><td><label>닉네임</label></td>
		<td>
			<input type="text" name="anickname"
			value="<%= admin.getaNickname() %>">
		</td></tr>
		<tr><td><label>등 급</label></td>
		<td>
			<input type="text" name="agrade" 
			value="<%= admin.getaGrade() %>">
		</td></tr>
		<tr><td><label>동사무소 코드</label></td>
		<td>
			<input type="text" name="lno" 
			value="<%= admin.getlNo() %>">
		</td></tr>
		<tr>
	<td colspan="2" align="center">
		<input type="submit" value="수정하기" class="btn btn-default">
		&nbsp; &nbsp;
		<a href="/semi/adelete?aid=<%= admin.getaId() %>">탈퇴하기</a>
	</td>
</tr>
</table>
<br>

</form>
<hr>
<%@ include file="../../footer.jsp" %>