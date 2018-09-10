<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="boardError.jsp"%>
<%@ page import="semi.board.model.vo.Board" %>   
<%
		Board board = (Board)request.getAttribute("board");
		int currentPage = ((Integer)request.getAttribute("page")).intValue();
%>

<%@ include file="../../header.jsp" %>

 <title>수정페이지</title>
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
		url : "/semi/bname",
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

function update(){
	alert("글 수정완료");
}

</script>

<br><br>
<!-- 아이디 일치 하지 않을시 수정 불가하게 작성 -->
<form action="/semi/boriginup" method="post" enctype="multipart/form-data">
<input type="hidden" name="no" value="<%= board.getRb_no() %>">
<input type="hidden" name="page" value="<%= currentPage%>">
<table class="search-form wow pulse animated"
style="width:50%; height:80%; border-color: #FACC2E;"
id="table" align="center" cellspacing="0" >

<col width="80">
<col width="">

<tr style="height: 40px;">
	<th style="text-align: center; font-size: 16px;">제목</th>
	<td>
	<input style="border: 1px solid #666; width:35%;" type="text" name="btitle" 
	value="<%= board.getRb_title() %>">
	</td>
</tr>
<tr style="height: 40px;">
	<th style="text-align: center; font-size: 16px;">작성자</th>
	<td>
	<input style="border: 1px solid #666;" type="text" name="bwriter" 
	value="<%= board.getM_id() %>" readonly>
	</td>
</tr>

<tr style="height: 40px;">
	<th style="text-align: center; font-size: 15px;">첨부파일변경</th>
	<td>
<input type="file" id="bupfile" name="bupfile" onchange="readURL(this);" 
		style="margin-bottom: 5px; padding: 3px; border: 1px solid #666;">
		<input type="file" id="bupfile2" name="bupfile2" onchange="readURL2(this);" 
		style="padding: 3px; border: 1px solid #666;">
	</td>
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

<tr style="height: 10px;" >
<td colspan="2"></td>
</tr>

<tr>
	<th style="text-align: center; font-size: 16px;">내용</th>
	<td style="height:100%; width:600px; overflow-y:scroll; border: 1px solid #666; ">
	<div contentEditable="true"  style="height:300px; " 	 >
	<textarea style="width:100%; resize:none; font-size: 16px;" rows="7" name="bcontent">
	<%= board.getRb_content() %>
	</textarea>
	<%-- <input type="text" name="bcontent"  
	value="<%= board.getRb_content() %>" style="border:none; pre-line" > --%>
	<% if(board.getRb_file1() != null ) { %>
	<img  id="blah" style=" border:0px; " src="/semi/semi/bupfiles/
	<%= board.getRb_file1()%>" >
	<% }else{ %>
	<img  id="blah" style=" border:0px;" >
	<% } %>
	<br><br>
	<% if(board.getRb_file2() != null) { %>
	<img  id="blah2" style=" border:0px;" src="/semi/semi/bupfiles/
	<%= board.getRb_file2()%>">
	<% }else{ %>
	<img  id="blah2" style=" border:0px;" >
	<% } %>
	<br><br>
	<%-- <% if(notice.getN_content() != null) { %>
	<input type="text" name="ngcontent"  
	value="<%= notice.getN_content() %>" style="border:none;">
	<% } %> --%>
	
	</div>
	</td>
</tr>

</table>

<div 
style="margin: 0 auto; padding-top: 15px; width: 950px; height: 30px; text-align: right;">
<input class="btn border-btn more-black"
type="submit" value="등록" onclick="update();"
style=" text-align:center; width:78px; height: 43px; border-color: #2E2E2E; color:black;">&nbsp;
<a href="/semi/blist?page=<%= currentPage %>" class="btn border-btn more-black" 
style=" text-align:center; width:78px; height: 43px; border-color: #2E2E2E; color:black;">목록</a>

</div>

</form>
<br><br><br><br><br><br><br><br>
<%@ include file="../../footer.jsp" %>   




