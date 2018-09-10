<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="qnaError.jsp"%>
<%@ page import="semi.qna.model.vo.QnaBoard, 
		semi.products.model.vo.Product, java.util.ArrayList "%>

<%
	QnaBoard qna = (QnaBoard)request.getAttribute("qna");
	ArrayList<QnaBoard> list = (ArrayList<QnaBoard>)request.getAttribute("list");
	int listCount =((Integer)request.getAttribute("listCount")).intValue();
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
	
%>

<%@ include file="../../header.jsp" %>
<script type="text/javascript" src="/semi/resources/js/jquery/jquery-3.3.1.min.js"></script>

<script type="text/javascript">
	function moveWritePage(){
		location.href = "/semi/views/qna/qnaWriteView.jsp";
	}
</script> 

<style>

table {
    width: 100%;
    border-top: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td.table {
    border-bottom: 1px solid #444444;
    padding: 10px;
  }


</style>

<br><br>
<table class="section additional-details"
style="width:50%; height:80%;"
id="table" align="center" cellspacing="0" >
<tr style=" border-bottom:1px solid #444444; padding:10px;">
	<th style="text-align:center; width:5%;">번호</th>
	<th style="text-align:center; width:50%">제목</th>
	<th style="text-align:center; width:10%">작성자</th>
	<th style="text-align:center; width:10%">날짜</th>
	<th style="text-align:center; width:5%">조회수</th>
	<!-- <th width="50" style="text-align:center;  display:none; ">등급</th> -->
	<!-- <th>번호</th><th></th><th>작성자</th>
	<th>날짜</th><th>조회수</th><th>첨부파일</th> -->
</tr>
<% for(QnaBoard q : list){ %>
<tr>
	<td align="center"
	style=" border-bottom:1px solid #444444; padding:10px;"><%= q.getQ_no() %></td>
	<td 
	style=" border-bottom:1px solid #444444; padding:10px;">
	<% if(q.getQ_level() == 1){//원글의 댓글일 때 %>
	&nbsp; &nbsp; ▶
	<% }else if(q.getQ_level() ==2){//댓글의 댓글일 때 %>
	&nbsp; &nbsp; &nbsp; &nbsp; ▶▶
	<% } %>
	<%-- 로그인한 사용자만 상세보기할 수 있도록 처리 --%>
	<% if(mId != null){ %>
		<a href="/semi/qdetail?no=<%= q.getQ_no() %>&page=<%= currentPage %>"><%= q.getQ_title() %></a>
	<% }else{ %>
		<%= q.getQ_title() %>
	<% } %>
	<% if(q.getQ_file1() != null || q.getQ_file2() != null) { %>
		<img style= "width:10px; height:10px;" src="/semi/resources/images/noticeList/disk.png">
	<% } %>
	</td>
	<td align="center"
	style=" border-bottom:1px solid #444444; padding:10px;"><%= q.getM_id() %></td>
	<td align="center"
	style=" border-bottom:1px solid #444444; padding:10px;"><%= q.getQ_date()%></td>
	<td align="center"
	style=" border-bottom:1px solid #444444; padding:10px;"><%= q.getQ_count() %></td>
</tr>
<% } %>
</table>

<!-- 페이징처리 -->
<div style="text-align: center;">
<% if(currentPage <= 1){ %>
	[맨처음]&nbsp;
<% }else{ %>
	<a href="/semi/qlist?page=1">[맨처음]</a>
<% } %>
<% if((currentPage - 10) < startPage &&
		(currentPage - 10) > 1){ %>
		<a href = "/semi/qlist?page=<%= startPage - 10 %>">[이전]</a>		
<% }else{ %>
	[이전]&nbsp;
<% } %>
<%--startPage ~ endPage 출력 --%>
<% for(int p = startPage; p <= endPage; p++){
		if(p == currentPage){
%>
	<font color="red" size="4">[<%= p %>]</font>
<% } else{ %>
	<a href="/semi/qlist?page=<%= p %>"><%= p %></a>
<% }} %>

<%----------------------------------------------------------- --%>

<% if((currentPage + 10) > endPage &&
		(currentPage + 10) < maxPage){ %>
	<a href="/semi/qlist?page=<%= endPage + 10 %>">[다음]</a>
<% }else{ %>
	[다음]&nbsp;
<% } %>

<% if(currentPage >= maxPage){ %>
	[맨끝]&nbsp;
<% }else{ %>
	<a href="/semi/qlist?page=<%= maxPage %>"></a>
<% } %>

</div>




<div  style="position: absolute; left: 1000px; top: 800px; ">

<button class="navbar-btn nav-button wow bounceInRight login animated"
 onclick="moveWritePage();">글쓰기</button>

	<!-- <button  id="writeBtn" onclick="moveWritePage();">
	<a href = "/semi/views/qna/qnaWriteView.jsp" >
	공지글 등록</a></button>
 -->
 </div>


<br><br><br><br><br><br><br><br><br><br>

<%@ include file="../../footer.jsp" %>





















