<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "semi.review.model.vo.ReviewBoard, java.util.*, semi.member.model.vo.Member" %>    
<%

	ArrayList<ReviewBoard> reviewboard = (ArrayList<ReviewBoard>)request.getAttribute("list");
	
%>    
    
    
<%@ include file="../../header.jsp" %>
<script type="text/javascript">
function moveboard(){
	location.href = "/semi/myboard?mid=<%=mId %>"
}
function movehome(){
	location.href = "/semi/myinfo?mid=<%= mId %>"
}
function movepoint(){
	location.href = "/semi/mypoint?mid=<%= mId %>"
}
function moverent(){
	location.href = "/semi/myrent?mid=<%= mId %>"
}  
</script>

<div class="page-head"> 
            <div class="container">
                <div class="row">
                    <div class="page-head-content">
                        <h1 class="page-title">09 : <span class="orange strong">My board</span></h1>               
                    </div>
                </div>
            </div>
        </div>
<br>

	

<button class="btn btn-default btn-lg"
		style="background: white; margin-left: 670px;" onclick="movehome();">
		<a style="color: black;">Home</a>
	</button>
	<button class="btn btn-default btn-lg" style="background: white;" onclick="movepoint();">
		<a style="color: black;">포인트 내역</a>
	</button>
	<button class="btn btn-default btn-lg" style="background: white;" onclick="moverent();">
		<a style="color: black;">최근 대여목록</a>
	</button>
	<button class="btn btn-default btn-lg" style="background: white;" onclick="moveboard();">
		<a style="color: black;">나의 게시글</a>
	</button>

<h1 align="center"><b><%= mId %></b> 님의 게시글</h1><br>

<table align="center" border="1" cellspacing="0" 
width="700">
<tr> 
	<th align="center">번호</th><th align="center">제목</th>
	<th align="center">작성일자</th><th align="center">조회수</th><th align="center">첨부파일1</th><th align="center">첨부파일2</th>
</tr>
<% for(ReviewBoard rb : reviewboard){ %>
<tr>
<td align="center"><%= rb.getRbNo() %></td> 
<td align="center"><%= rb.getRbTitle() %></td>
<td align="center"><%= rb.getRbDate() %></td>
<td align="center"><%= rb.getRbCount() %></td>
<% if(rb.getRbFile1() != null){ %>
<td align="center"><%= rb.getRbFile1() %></td>
<%}else{ %>
<td align="center">&nbsp;</td>
<%} %>
<% if(rb.getRbFile2() != null){ %>
<td align="center"><%= rb.getRbFile2() %></td>
<%}else{ %>
<td align="center">&nbsp;</td>
<%} %>

</tr>
<%} %> 
</table>




<br>
<%@ include file="../../footer.jsp" %>