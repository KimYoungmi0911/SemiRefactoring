<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="semi.notice.model.vo.Notice, semi.notice.model.vo.NoticeComment"%>
<%
	Notice notice = (Notice)request.getAttribute("notice");
	NoticeComment noticecom = (NoticeComment)request.getAttribute("noticecom");
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
	String mno = (String)session.getAttribute("m_No");
	
%>
<%@ include file= "../../header.jsp" %>
<%-- <% if(mno != null && mno.equals("admin")){ %>
		<%@ include file= "../../adminHeader.jsp" %>
	<% }else{ %>
		<%@ include file="../../header.jsp" %>
	<% } %> --%>

<script type="text/javascript">
	function showList(){
		location.href = "/semi/nglist";
		return false;		
	}
</script>
<script type="text/javascript">
	function showimage(){
		
	}

</script>

<br><br>
	<form action="/semi/ngupdate" method="post"
		enctype="multipart/form-data">
<input type="hidden" name="no" value="<%= notice.getN_no() %>">
<input type="hidden" name="rfile" value="<%= notice.getN_file1()%>">
<table class="search-form wow pulse animated"
style="width:50%; height:80%; border-color: #FACC2E; "
id="table" align="center" cellspacing="0" >
			
			<col width="80">
			<col width="">
			
			<tr style="height: 40px;">
				<th style="text-align: center; font-size: 16px;">제목</th>
				<td>
					<input style="border: 1px solid #666; width:35%" type="text" name="ngtitle" 
					value="<%= notice.getN_title()%>" readonly>
				</td>
			</tr>
			<tr style="height: 40px;">
				<th style="font-size: 16px; text-align: center;">작성자</th>
				<td>
					<input style="border: 1px solid #666;" type="text" name="ngwriter" 
					value="<%= notice.getA_id() %>" readonly>
				</td>
			</tr>
			
		<tr style="height: 40px;">
			<th  
			style="text-align: center; font-size: 16px;">등급</th>
			<td>
			 	<select name="grade" >
			 	<% if(notice.getN_grade().equals("easy")){ %>
 	 			<option value="easy" selected="selected">보통</option>
 	 			
 	 			<% }else if(notice.getN_grade().equals("hard")){ %>
 				<option value="hard" selected="selected">중요</option>
 				
 				<% }else{ %>
  	 			<option value="hell" selected="selected">매우중요</option>
  	 			
  	 		
  	 			<% } %>
	</select>
				
			</td>
			</tr>
<tr style="height: 10px;">
<td colspan="2"></td>
</tr>			
			
			
			<tr>
				<th style="text-align: center; font-size: 16px;">내용</th>
				<td style=" height:100%; width:600px;  border: 1px solid #666;  "  readonly; >

					<%= notice.getN_content() %>
						<% if(notice.getN_file1() != null) { %>
						<img  src="/semi/semi/ngupfiles/<%= notice.getN_file1()%>">
					
						<% } %>
						<br>
						<br>
						<% if(notice.getN_file2() != null) { %>
						<img  
							src="/semi/semi/ngupfiles/<%= notice.getN_file2()%>"  >
								
						<% } %>
						<br>
						<br>
						
					
				</td>
			</tr>
			
			
		</table>
	</form>
	<br>
		
		
<%-- <form action="" method="post"  enctype="multipart/form-data">
<div style="margin: 0 auto; padding-top:10px; padding-left: 38px; width:719px; height:350px;">
<div>댓글</div >
<div style=" height:300px; width:700px; border: 1px solid #666; background-color:#FAF6F6;">
<div style="margin: 0 auto; padding-top:220px; padding-left: 20px; ">
<textarea id="comment" rows="2" cols="80" style="border: 1px solid #666;"></textarea>
		
<a href="#" onclick="aclick();">등록</a>
			<script type="text/javascript">
			function aclick(){
				var no = <%= notice.getN_no()%>;
				var com = $("#comment").val();
				$.ajax({
					url : "ngcomment",
					type : "post",
					data : {no : no, com : com},
					success : function(data){
						/* $("#p2").html($("#p2").text() + "<br>"
								+ data); */
								
								
					}
				});
			}
			
			
			
			</script>
			</div>
			</div>
		</div>
		</form> --%>
		
<div style="margin: 0 auto; padding-top: 15px; width: 950px; height: 200px; text-align: right;">
<% if(mId.equals(notice.getA_id()) == true){ %>
<a href="/semi/ngupview?no=<%= notice.getN_no() %>&page=<%= currentPage %>"
class="btn border-btn more-black" 
style="border-color: #2E2E2E; color:black;">
수정하기</a>
&nbsp;	
<a href="/semi/ngdelete?no=<%= notice.getN_no() %>" 
class="btn border-btn more-black" 
style="border-color: #2E2E2E; color:black;">삭제하기</a>
 &nbsp;	
<% } %>

<a href="/semi/nglist?page=<%= currentPage %>"
class="btn border-btn more-black" 
style="border-color: #2E2E2E; color:black;">목록</a>
					
		</div>
<br><br><br><br><br><br><br>

<%@ include file="../../footer.jsp" %>















