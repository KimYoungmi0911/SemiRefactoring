<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="noticeGongError.jsp"%>
<%@ page import="semi.notice.model.vo.Notice, 
semi.member.model.vo.Member, java.util.ArrayList "%>

<%
	Notice notice = (Notice)request.getAttribute("notice");
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
	int listCount =((Integer)request.getAttribute("listCount")).intValue();
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
	//String mId = (String)session.getAttribute("m_Id");	
	//String aId = (String)session.getAttribute("a_Id");
	String mno = (String)session.getAttribute("m_No");
	
%>

		<%@ include file= "../../header.jsp" %>

	


<!-- <script type="text/javascript">
	function showNoticeWriteForm(){
		//글등록 버튼 클릭하면, noticeGongWriteForm.jsp 이동
		loaction.href = "/semi/view/notice/noticeGongWriteForm.jsp";
	}
</script> -->
<script type="text/javascript">
	function moveWritePage(){
		location.href = "/semi/views/notice/noticeGongWriteForm.jsp";
	}
</script>        
        

<br><br><br>
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


<table class="section additional-details"
style="width:50%; height:80%;  border-collapse: collapse;"
id="table" align="center" cellspacing="0"  >
<tr style=" border-bottom:1px solid #444444; padding:10px;">
	<th style="text-align:center;  width:5%;">번호</th>
	<th style="text-align:center; width:50%">제목</th>
	<th style="text-align:center; width:10%">작성자</th>
	<th style="text-align:center; width:10%">날짜</th>
	<th style="text-align:center; width:10%">조회수</th>
	<!-- <th width="50" style="text-align:center;  display:none; ">등급</th> -->
	<!-- <th>번호</th><th></th><th>작성자</th>
	<th>날짜</th><th>조회수</th><th>첨부파일</th> -->
</tr>

<% for(Notice n : list){ %>

<tr >
	
	<td align="center"
	 style=" border-bottom: 1px solid #444444; padding: 10px;"><%= n.getN_no() %></td>
	<td style=" border-bottom: 1px solid #444444; padding: 10px; font-size:16px;">
	<% if("easy".equals(n.getN_grade())) { %>
		<a href="/semi/ngdetail?no=<%= n.getN_no()%>&page=<%= currentPage %>"
	style=" font-weight: normal; font-size: 13px;"> &nbsp;&nbsp;<%= n.getN_title() %></a>
			<% }else if("hard".equals(n.getN_grade())) { %>
			<a href="/semi/ngdetail?no=<%= n.getN_no()%>&page=<%= currentPage %>"
		style="  font-size: 15px;"> &nbsp;&nbsp;<%= n.getN_title() %></a>
			<% }else if("hell".equals(n.getN_grade())) { %>
			<a href="/semi/ngdetail?no=<%= n.getN_no()%>&page=<%= currentPage %>"
	style="color: #ff5e00; font-weight: bold; font-size: 20px;"> &nbsp;&nbsp;<%= n.getN_title() %></a>
			<% }else { %>
			<a href="/semi/ngdetail?no=<%= n.getN_no()%>&page=<%= currentPage %>"
		> &nbsp;&nbsp;<%= n.getN_title() %></a>
			<% } %>
		<% if(n.getN_file1() != null || n.getN_file2() != null) { %>
		<img style= "width:10px; height:10px;" src="/semi/resources/images/noticeList/disk.png">
	<% } %>
	</td>
	<td align="center"
	style=" border-bottom: 1px solid #444444; padding: 10px;"><%= n.getA_id() %></td>
	<td align="center"
	style=" border-bottom: 1px solid #444444; padding: 10px;"><%= n.getN_date() %></td>
	<td align="center"
	style=" border-bottom: 1px solid #444444; padding: 10px;"><%= n.getN_count() %></td>
	<%-- <td style=" display:none; "><%= n.getN_grade() %></td> --%>
	

</tr>
<% } %>
</table>
<!-- 페이징처리 -->
<div  
style="text-align: center; align:center;">
<% if(currentPage <= 1){ %>
	[맨처음]&nbsp;
<% }else{ %>
	<a href="/semi/nglist?page=1">[맨처음]</a>
<% } %>
<% if((currentPage - 10) < startPage &&
		(currentPage - 10) > 1){ %>
		<a href = "/semi/nglist?page=<%= startPage - 10 %>">[이전]</a>		
<% }else{ %>
	[이전]&nbsp;
<% } %>
<%--startPage ~ endPage 출력 --%>
<% for(int p = startPage; p <= endPage; p++){
		if(p == currentPage){
%>
	<font color="red" size="4">[<%= p %>]</font>
<% } else{ %>
	<a href="/semi/nglist?page=<%= p %>"><%= p %></a>
<% }} %>

<%----------------------------------------------------------- --%>

<% if((currentPage + 10) > endPage &&
		(currentPage + 10) < maxPage){ %>
	<a href="/semi/nglist?page=<%= endPage + 10 %>">[다음]</a>
<% }else{ %>
	[다음]&nbsp;
<% } %>

<% if(currentPage >= maxPage){ %>
	[맨끝]&nbsp;
<% }else{ %>
	<a href="/semi/nglist?page=<%= maxPage %>">[맨끝]</a>
<% } %>
<% System.out.print("max : " + maxPage); %>


</div>


<div  style="position: absolute; left: 1000px; top: 500px; ">
<% if(mId.equals(mId) != true){ %>
<button class="navbar-btn nav-button wow bounceInRight login animated"
 onclick="moveWritePage();">공지사항작성</button>
<% } %>

	<!-- <button  id="writeBtn" onclick="moveWritePage();">
	<a href = "/semi/views/notice/noticeGongWriteForm.jsp"
	class="navbar-btn nav-button wow bounceInRight login animated"
	>
	공지글 등록</a></button> -->
</div>
<br><br><br><br><br><br><br><br><br><br>

<%@ include file="../../footer.jsp" %>





