<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="qnaError.jsp"%>
<%@ page import="semi.qna.model.vo.QnaBoard"%>    
<%
		int qnaNo = Integer.parseInt(request.getParameter("no"));
		int currentPage = Integer.parseInt(request.getParameter("page"));
		QnaBoard qna = (QnaBoard)request.getAttribute("qna");
%>    
<script type="text/javascript" src="/semi/resources/js/jquery/jquery-3.3.1.min.js"></script>

<%@ include file="../../header.jsp" %> 
<div class="s-property-title"
 style="text-align:center; ">
<h2>글 남기기</h2>
</div>

<form action="/semi/qreply" method="post" >
<input type="hidden" name="no" value="<%= qnaNo %>">
<input type="hidden" name="page" value="<%= currentPage %>">
<table class="search-form wow pulse animated"
style="width:50%; height:50%; border-color: #FACC2E;"
id="table" align="center" cellspacing="0" >

<col width="80">
<col width="">

<tr style="height: 40px; margin-top:30px;">
	<td style="text-align: center; font-size: 16px; ">제목</td>
	<td><input type="text" name="qtitle" size="50" 
	style="border: 1px solid #666; width:35%;"></td>
</tr>

<tr style="height: 40px;">
	<td style="text-align: center; font-size: 16px;">작성자</td>
	<td><input type="text" name="qwriter" 
	value="<%= mId %>"
	style="border: 1px solid #666; width:35%;" readonly></td>
	
</tr>

<tr style="height: 10px;">
<td colspan="2"></td>
</tr>

<tr>
	<td style="text-align: center; font-size: 16px;">내용</td>
	<td style="height:50%; width:1pp%;  border: 1px solid #666; ">
	<textarea 
	style="width:100%; height:100%; resize:none; font-size: 16px;" rows="7" name="qcontent">
	</textarea>
</tr>
</table>


<div 
style="margin: 0 auto; padding-top: 15px; width: 950px; height: 30px; text-align: right;">
<input class="btn border-btn more-black"
type="submit" value="댓글등록"
style=" text-align:center; width:100px; height: 43px; border-color: #2E2E2E; color:black;"> &nbsp;
<input class="btn border-btn more-black"
type="button" value="취소" onclick ="history.go(-1); return false;"
style=" text-align:center; width:78px; height: 43px; border-color: #2E2E2E; color:black;">
&nbsp; 
<a href="/semi/qlist?page=<%= currentPage %>"
class="btn border-btn more-black"
style=" text-align:center; width:78px; height: 43px; border-color: #2E2E2E; color:black;">목록</a>
</div>


</form>
<br><br><br><br>
<hr>


<%@ include file="../../footer.jsp" %>
























