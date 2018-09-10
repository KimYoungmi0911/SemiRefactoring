<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="boardError.jsp"%>
<%@ page import="semi.board.model.vo.Board, java.util.ArrayList " %>

<%
	Board board = (Board)request.getAttribute("board");
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	int listCount =((Integer)request.getAttribute("listCount")).intValue();
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
%>    

<%@ include file="../../header.jsp"%>

<script type="text/javascript">
	function moveWritePage(){
		location.href = "/semi/views/board/boardWriteView.jsp";
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
<br><br><br>
<table class="section additional-details"
style="width:50%; height:80%;  border-collapse: collapse;"
id="table" align="center" cellspacing="0"  >
<tr style=" border-bottom:1px solid #444444; padding:10px;">
	<th style="text-align:center; width:10%;">번호</th>
	<th style="text-align:center; width:50%">제목</th>
	<th style="text-align:center; width:10%">작성자</th>
	<th style="text-align:center; width:10%">날짜</th>
	<th style="text-align:center; width:10%">조회수</th>
</tr>
<% for(Board b : list){ %>
<tr>
	<td align="center"
	style=" border-bottom:1px solid #444444; padding:10px;"><%= b.getRb_no() %></td>
	<td style=" border-bottom:1px solid #444444; padding:10px;">
	<a href="/semi/bdetail?no=<%= b.getRb_no()%>&page=<%= currentPage%>"
	style="text-size:30px;"> &nbsp;&nbsp;<%= b.getRb_title() %></a>
	<% if(b.getRb_file1() != null || b.getRb_file2() != null){ %>
		<img style= "width:10px; height:10px;" src="/semi/resources/images/noticeList/disk.png">
	<% } %>
	</td>
	<td align="center"
	style=" border-bottom:1px solid #444444; padding:10px;"><%= b.getM_id() %></td>
	<td align="center"
	style=" border-bottom:1px solid #444444; padding:10px;"><%= b.getRb_date() %></td>
	<td align="center"
	style=" border-bottom:1px solid #444444; padding:10px;"><%= b.getRb_count() %></td>
</tr>
<% } %>
</table>

<!-- 페이징처리 -->
<div style="text-align: center;">
<% if(currentPage <= 1){ %>
	[맨처음]&nbsp;
<% }else{ %>
	<a href="/semi/blist?page=1">[맨처음]</a>
<% } %>
<% if((currentPage - 10) < startPage &&
		(currentPage - 10) > 1){ %>
		<a href = "/semi/blist?page=<%= startPage - 10 %>">[이전]</a>		
<% }else{ %>
	[이전]&nbsp;
<% } %>
<%--startPage ~ endPage 출력 --%>
<% for(int p = startPage; p <= endPage; p++){
		if(p == currentPage){
%>
	<font color="red" size="4">[<%= p %>]</font>
<% } else{ %>
	<a href="/semi/blist?page=<%= p %>"><%= p %></a>
<% }} %>

<%----------------------------------------------------------- --%>

<% if((currentPage + 10) > endPage &&
		(currentPage + 10) < maxPage){ %>
	<a href="/semi/blist?page=<%= endPage + 10 %>">[다음]</a>
<% }else{ %>
	[다음]&nbsp;
<% } %>

<% if(currentPage >= maxPage){ %>
	[맨끝]&nbsp;
<% }else{ %>
	<a href="/semi/blist?page=<%= maxPage %>"></a>
<% } %>

</div>

<div  style="position: absolute; left: 1000px; top: 1100px; ">

<button class="navbar-btn nav-button wow bounceInRight login animated"
 onclick="moveWritePage();">글쓰기</button>
</div>
<br><br><br><br><br><br>




<%@ include file="../../footer.jsp" %>