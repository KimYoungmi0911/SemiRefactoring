<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "semi.member.model.vo.Member, java.util.*" %>
<%
	String fId = (String)request.getAttribute("result");
	
	
%>
<%@ include file = "../../header.jsp" %>
<table width="1330px" height="530px" align="center">
    <tr>
     <td>
      
      <table width="550px" align=center border="0"
       style="color: black; font-size: 30px;">
       <tr align=center>
        <td style="height: 170px">* 회원님의 아이디를 찾았습니다! *</td>
       </tr>
       <tr align=center>
        <td style="font-size: 20px">아이디 : <%= fId %></td>
        
       </tr>
        <tr>
           <td>
            <table width="270px" height="10px" border="0"
             style="margin-top: 2%" align="center">
             <tr>
              <td><a href="/semi/views/member/idAndPasswordSearchView.jsp" style="width : 100px; color : #777; font-size : 16px;">아이디 찾기&nbsp;&nbsp;&nbsp; |</a></td>
              <td><a href="/semi/views/member/passwordSearchView.jsp" style="width : 100px; color : #777; font-size : 16px;">비밀번호 찾기</a></td>
             </tr>
            </table>
            <br>
           </td>
          </tr>
      </table> 
  
      <!-- <table width="550px" align=center border="0"
       style="color: black; font-size: 30px;">
       <tr align=center>
        <td style="height: 170px">* 아이디 찾기를 실패하셨습니다. *</td>
       </tr>
       <tr align=center>
        <td><input type="button" value="돌아가기"
         onClick="history.back()"></td>
       </tr>
      </table> -->



<%@ include file = "../../footer.jsp"%>