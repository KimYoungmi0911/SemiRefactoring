<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="noticeGongError.jsp"%>
<%@ page import="semi.notice.model.vo.Notice" %>
<%
		Notice notice = (Notice)request.getAttribute("notice");
		int currentPage = ((Integer)request.getAttribute("page")).intValue();
		//String aId = (String)session.getAttribute("a_Id");
		//String mId = (String)session.getAttribute("m_Id");	
%>


<%@ include file= "../../header.jsp" %>

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



</script>
     
        
<br><br>
<!-- 아이디 일치 하지 않을시 수정 불가하게 작성 -->
<form action="/semi/ngoriginup" method="post" enctype="multipart/form-data">
<input type="hidden" name="no" value="<%= notice.getN_no()%>">
<input type="hidden" name="page" value="<%= currentPage%>">
<table class="search-form wow pulse animated"
style="width:50%; height:80%; border-color: #FACC2E;"
id="table" align="center" cellspacing="0" >

<col width="80">
<col width="">

<tr style="height: 40px;">
<th style="text-align: center; font-size: 16px;">제목</th>
	<td>
	<input style="border: 1px solid #666; width:35%;" type="text" name="ngtitle" 
	value="<%= notice.getN_title() %>">
	</td>
</tr>
<tr style="height: 40px;">
<th style="text-align: center; font-size: 16px;">작성자</th>
	<td>
	<input style="border: 1px solid #666;" type="text" name="ngwriter" 
	value="<%= notice.getA_id() %>" readonly>
	</td>
</tr>

	<tr style="height: 40px;">
			<th style="text-align: center; font-size: 16px;">등급</th>
			<td>
			 	<select name="grade" >
			 	<% if(notice.getN_grade().equals("easy")){ %>
 	 			<option value="easy" selected="selected">보통</option>
 	 			<option value="hard" >중요</option>
 	 			<option value="hell" >매우중요</option>
 	 			<% }else if(notice.getN_grade().equals("hard")){ %>
 				<option value="hard" selected="selected">중요</option>
 				<option value="easy" >보통</option>
 				<option value="hell" >매우중요</option>
 				<% }else{ %>
  	 			<option value="hell" selected="selected">매우중요</option>
  	 			<option value="easy" >보통</option>
  	 			<option value="hard" >중요</option>
  	 		
  	 			<% } %>
	</select>
				
			</td>
			</tr>



<tr style="height: 40px;">
	<th style="text-align: center; font-size: 15px;">첨부파일변경</th>
<td>
		<input type="file" id="gupfile" name="gupfile" onchange="readURL(this);" 
		style="margin-bottom: 5px; padding: 3px; border: 1px solid #666;">
		<input type="file" id="gupfile2" name="gupfile2" onchange="readURL2(this);" 
		style="padding: 3px; border: 1px solid #666;">
	</td>
</tr>

<tr style="height: 10px;">
<td colspan="2"></td>
</tr>

<tr>
	<th style="text-align: center; font-size: 16px;">내용</th>
	<td style="height:100%; width:600px; overflow-y:scroll; border: 1px solid #666; ">
	<div contentEditable="true"  style="height:300px; " 	 >
	<textarea style="width:100%; resize:none; font-size: 16px;" rows="7" name="ngcontent">
	<%= notice.getN_content() %>
	</textarea>
	<%-- <input type="text" name="ngcontent"  
	value="<%= notice.getN_content() %>" style="border:none; width:100%; height:200px;" > --%>
	<% if(notice.getN_file1() != null ) { %>
	<img  id="blah" style=" border:0px; " src="/semi/semi/ngupfiles/
	<%= notice.getN_file1()%>" >
	<% }else{ %>
	<img  id="blah" style=" border:0px;" >
	<% } %>
	<br><br>
	<% if(notice.getN_file2() != null) { %>
	<img  id="blah2" style=" border:0px;" src="/semi/semi/ngupfiles/
	<%= notice.getN_file2()%>">
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
<%-- <tr  align="right">
   <th colspan="2">
      <div style="float: right; width: 350px; height: 30px; ">
        <input type="submit" value="등록">&nbsp;
       <input type="reset" value="취소"> &nbsp;
       <a href="/second/nglist?page=<%= currentPage %>">목록</a>
    </div>
    
   </th>
</tr> --%>
</table>

<div 
style="margin: 0 auto; padding-top: 15px; width: 950px; height: 30px; text-align: right;">
<input  class="btn border-btn more-black"
 type="submit" value="등록" 
 style=" text-align:center; width:78px; height: 43px; border-color: #2E2E2E; color:black;">&nbsp;
<a href="/semi/nglist?page=<%= currentPage %>" class="btn border-btn more-black" 
style=" text-align:center; width:78px; height: 43px; border-color: #2E2E2E; color:black;">
목록
</a>

</div>


</form>
<br><br><br><br><br><br><br><br>
<%@ include file="../../footer.jsp" %>
   

























