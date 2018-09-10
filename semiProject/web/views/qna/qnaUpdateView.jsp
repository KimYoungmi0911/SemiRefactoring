<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="qnaError.jsp"%>
<%@ page import="semi.qna.model.vo.QnaBoard" %>
<%
		QnaBoard qna = (QnaBoard)request.getAttribute("qna");
		int currentPage = ((Integer)request.getAttribute("page")).intValue();
%>

<%@ include file="../../header.jsp" %>

<script type="text/javascript" src="/semi/resources/js/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript"> 

function readURL(input) {
	if (input.files && input.files[0]) { 
		var reader = new FileReader(); 
		reader.onload = function (e) { 
			$('#blah').attr('src', e.target.result); 
			} 
			reader.readAsDataURL(input.files[0]); 
	}
}

function readURL2(input) {
	if (input.files && input.files[0]) { 
		var reader = new FileReader(); 
		reader.onload = function (e) { 
			$('#blah2').attr('src', e.target.result); 
			} 
			reader.readAsDataURL(input.files[0]); 
	}
}

$(function(){
	
	$.ajax({
		url : "/semi/qname",
		type : "post",
		dataType : "json",
		success : function (data){
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);
				var s = "";
			for(var i in json.list){
				
				s += "<option>" + json.list[i].pno + " . " + decodeURIComponent(json.list[i].pname) + "</option>";
				//$("#pro").append("<option>" + decodeURIComponent(json.list[i].pname) + "</option>");
			}
			
			$("#pro").html(s);
		}, error : function(jqXHR, textstatus, errorThrown){
			console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
		}
	});
}); 
function deletebbb(){
	alert("글 수정완료");
}

function deleteddd(){
	alert("댓글 수정완료");
}
</script>

<br><br>
<!-- 아이디 일치 하지 않을시 수정 불가하게 작성 -->
<% if(qna.getQ_level() == 0){ %> <%-- 원글일 때 --%>
<form action="/semi/qoriginup" method="post" enctype="multipart/form-data">
<input type="hidden" name="no" value="<%= qna.getQ_no() %>">
<input type="hidden" name="page" value="<%= currentPage %>">
<table class="search-form wow pulse animated"
style="width:50%; height:80%; border-color: #FACC2E;"
id="table" align="center" cellspacing="0" >

<col width="80">
<col width="">

<tr style="height: 40px;">
	<td style="text-align: center; font-size: 16px; ">제목</td>
	<td>
	<input type="text" name="qtitle" 
	value="<%= qna.getQ_title() %>"
	style="border: 1px solid #666; width:35%;">
	</td>
</tr>
<tr style="height: 40px;">
	<td style="text-align: center; font-size: 16px;">작성자</td>
	<td>
<input type="text" name="qwriter" style="border: 1px solid #666;"
value="<%= qna.getM_id() %>"readonly
></td>
</tr>

<tr style="height: 10px;">
<td colspan="2"></td>
</tr>

<tr  style="height: 40px;">
	<th style="text-align: center; font-size: 16px;">물품명</th>
	<td>
	<select id="pro" name="pro">
  	<option >물품명</option>
	</select>
	</td>
</tr>
			
			
<tr style="height: 40px;">
	<th style="text-align: center; font-size: 15px;">첨부파일변경</th>
	<td>
<input type="file" id="qupfile" name="qupfile" onchange="readURL(this);" 
		style="margin-bottom: 5px; padding: 3px; border: 1px solid #666;">
		<input type="file" id="qupfile2" name="qupfile2" onchange="readURL2(this);" 
		style="padding: 3px; border: 1px solid #666;">
	</td>
</tr>

<tr style="height: 10px;">
<td colspan="2"></td>
</tr>

<tr>
	<th style="text-align: center; font-size: 16px;">내용</th>
	<td style="height:100%; width:600px; overflow-y:scroll; border: 1px solid #666; ">
	<div contentEditable="true"  style="height:300px; " >
	<textarea style="width:100%; resize:none; font-size: 16px;" rows="7" name="qcontent">
	<%= qna.getQ_content() %>
	</textarea>
	<% if(qna.getQ_file1() != null ) { %>
	<img  id="blah" style=" border:0px; " src="/semi/semi/qupfiles/
	<%=qna.getQ_file1()%>" >
	
	<% }else{ %>
	<img  id="blah" style=" border:0px;" >
	<% } %>
	<br><br>
	<% if(qna.getQ_file2() != null) { %>
	<img  id="blah2" style=" border:0px;" src="/semi/semi/qupfiles/
	<%= qna.getQ_file2()%>">
	<% }else{ %>
	<img  id="blah2" style=" border:0px;" >
	<% } %>
	<br><br>

	
	</div>
	</td>
</tr>
</table>

<div style="margin: 0 auto; padding-top: 15px; width: 950px; height: 30px; text-align: right;">
<input class="btn border-btn more-black"
type="submit" value="등록" onclick="deletebbb();"
style=" text-align:center; width:78px; height: 43px; border-color: #2E2E2E; color:black;">&nbsp;
<a href="/semi/qlist?page=<%= currentPage %>" class="btn border-btn more-black"
style=" text-align:center; width:78px; height: 43px; border-color: #2E2E2E; color:black;">목록</a>
</div>
</form>

<% }else{ %><%-- 댓글일 때 --%>
<form action="/semi/qreplyup" method="post" method="post">
<input type="hidden" name="no" value="<%= qna.getQ_no() %>">
<input type="hidden" name="page" value="<%= currentPage %>">
<table class="search-form wow pulse animated"
style="width:50%; height:80%; border-color: #FACC2E;"
id="table" align="center" cellspacing="0" >

<col width="80">
<col width="">

<tr style="height: 40px;">
	<td style="text-align: center; font-size: 16px;">제목</td>
	<td>
<input type="text" name="qtitle" value="<%= qna.getQ_title()%>"
style="border: 1px solid #666; width:35%;">
</td>
</tr>

<tr style="height: 40px;">
	<td style="text-align: center; font-size: 16px;">작성자</td>
	<td>
<input type="text" name="qwriter" value="<%= qna.getM_id() %>" readonly
style="border: 1px solid #666; width:35%;">
</td>
</tr>

<tr style="height: 10px;">
<td colspan="2"></td>
</tr>

<tr style="height: 40px;">
	<td style="text-align: center; font-size: 16px;">내용</td>
	<td style="height:100%; width:600px; overflow-y:scroll; border: 1px solid #666; ">
	<textarea name="qcontent" cols="50" rows="7" 
	 style="width:100%; resize:none; font-size: 16px;"><%= qna.getQ_content() %>
	</textarea>
	</td>
</tr>
</table>
<div 
style="margin: 0 auto; padding-top: 15px; width: 950px; height: 30px; text-align: right;">
<% if (mId.equals(qna.getM_id()) == true) { %>
<input type="submit" value="수정하기"
class="btn border-btn more-black" onclick="deleteddd();"
style=" text-align:center; width:100px; height: 43px; border-color: #2E2E2E; color:black;"> &nbsp;
	<% } %>
<input type="reset" value="취소"
class="btn border-btn more-black"
style=" text-align:center; width:78px; height: 43px; border-color: #2E2E2E; color:black;"> &nbsp;	
<a href="/semi/qlist?page=<%= currentPage %>"
class="btn border-btn more-black"
style=" text-align:center; width:78px; height: 43px; border-color: #2E2E2E; color:black;">
목록</a>
	

</div>
</form>
<% } %>



<br><br><br><br><br><br><br><br>
<%@ include file="../../footer.jsp" %>




















