<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="semi.qna.model.vo.QnaBoard, semi.products.model.vo.Product"%>
<%
	QnaBoard qna = (QnaBoard)request.getAttribute("qna");
	Product pro = (Product)request.getAttribute("pro");
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
%>
<%@ include file="../../header.jsp" %>

<script type="text/javascript">
	function showList(){
		location.href = "/semi/qlist";
		return false;		
	}


 $(function(){
	var qno1 = "<%= qna.getQ_no()%>";
	$.ajax({
		url : "/semi/qname1",
		type : "post",
		data : {"qno" : qno1},
		dataType : "json",
		success : function (data){
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);

				$("#pro").val(decodeURIComponent(json.pname));
			
		}, error : function(jqXHR, textstatus, errorThrown){
			console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
		}
	});
});  

 function deletebbb(){
	 alert("글 삭제완료");
 }
</script>



<br><br>
	<form action="/semi/qupdate" method="post"
		enctype="multipart/form-data">
		<%-- <input type="hidden" name="no" value="<%= qna.getQ_no() %>">
		<input type="hidden" name="rfile" value="<%= qna.getQ_file1() %>"> --%>
<table class="search-form wow pulse animated"
style="width:50%; height:80%; border-color: #FACC2E;"
id="table" align="center" cellspacing="0" >	
			
			<col width="80">
			<col width="">
			
			<tr style="height: 40px;">
				<th style="text-align: center; font-size: 16px; ">제목</th>
				<td>
					<input style="border: 1px solid #666; width:35%;" type="text" name="qtitle" 
					value="<%=qna.getQ_title() %>" readonly>
				</td>
			</tr>
			<tr style="height: 40px;">
				<th style="text-align: center; font-size: 16px;">작성자</th>
				<td>
					<input style="border: 1px solid #666;" type="text" name="qwriter" 
					value="<%= qna.getM_id() %>" readonly>
				</td>
			</tr>

<tr style="height: 40px;">
			<th style="text-align: center; font-size: 16px;">물품명</th>
			<td>
				<input style="border: 1px solid #666;" type="text" id="pro" name="pro"  
					 readonly>
			</td>
			</tr>
			
<tr style="height: 10px;">
<td colspan="2"></td>
</tr>					
	
			<tr>
				<th style="text-align: center; font-size: 16px;">내용</th>
				<td style=" height:100%; width:600px;  border: 1px solid #666;" readonly; >

					<%= qna.getQ_content() %>
						<% if(qna.getQ_file1() != null) { %>
						<img  src="/semi/semi/qupfiles/<%= qna.getQ_file1() %>">
						<% } %>
						<br>
						<br>
						<% if(qna.getQ_file2() != null) { %>
						<img  
							src="/semi/semi/qupfiles/<%= qna.getQ_file2() %>"  >
								
						<% } %>
						<br>
						<br>
				</td>
			</tr>
		</table>
	</form>
	<br>
	
	<div style="margin: 0 auto; padding-top: 15px; width: 950px; height: 200px; text-align: right;">
			<%if(mId.equals(qna.getM_id()) == true){ %>
			<a href="/semi/qupview?no=<%= qna.getQ_no() %>&page=<%= currentPage %>"
			class="btn border-btn more-black" 
			style="  border-color: #2E2E2E; color:black;">
				수정하기</a>
		 &nbsp;		
			<a href="/semi/qdelete?no=<%= qna.getQ_no() %>"
			class="btn border-btn more-black" onclick="deletebbb();"
			style=" border-color: #2E2E2E; color:black;">
			삭제하기</a>
			<% }else if(qna.getQ_level() != 2){ %>
				<a href="/semi/views/qna/qnaReplyForm.jsp?no=<%= qna.getQ_no() %>&page=<%= currentPage %>"
				class="btn border-btn more-black" 
				style="  border-color: #2E2E2E; color:black;">
				댓글달기</a>
			<% } %>
		 &nbsp;
			<a href="/semi/qlist?page=<%= currentPage %>"
			class="btn border-btn more-black" 
			style=" border-color: #2E2E2E; color:black;">
			목록</a>
					
		</div>
<br><br><br><br><br><br><br>

<%@ include file="../../footer.jsp" %>















