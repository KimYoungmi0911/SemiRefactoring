<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="	semi.products.model.vo.Product, java.util.ArrayList "%>
<%
String mno = (String)session.getAttribute("m_No");
%>

    
<%@ include file="../../header.jsp" %> 



 <title>문의게시판 글쓰기</title>
        <!-- 이미지 업로드시 미리보기 -->
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
function writeddd(){
	alert("글 작성완료");
}
</script>


<div class="s-property-title"
 style="text-align:center; ">
<h2>문의글 작성</h2>
</div>

<br>
<form action="/semi/qinsert" method="post" enctype="multipart/form-data">
<table class="search-form wow pulse animated"
style="width:50%; height:80%; border-color: #FACC2E;"
id="table" align="center" cellspacing="0" >

<col width="80">
<col width="">

<tr style="height: 40px">
	<th style="text-align: center; font-size: 16px;">제목</th>
	<td><input type="text" name="qtitle" style="border: 1px solid #666; width:35%;"></td>
</tr>
<tr style="height: 40px">
	<th style="text-align: center; font-size: 16px;">작성자</th>
	<td>
	<input type="text" name="qwriter" 
	value="<%= mId %>" style="border: 1px solid #666;" readonly>
	</td>
</tr>


<tr style="height: 40px">
	<th style="text-align: center; font-size: 16px;">첨부파일</th>
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

<tr  style="height: 40px;">
<th style="text-align: center; font-size: 16px;">물품명</th>
<td>
  	<select id="pro" name="pro">
  	<option >물품명</option>
	</select>
</td>
</tr>

<tr style="height: 10px;">
<td colspan="2"></td>
</tr>

<tr >
	<th style="text-align: center; font-size: 16px;">내용</th>
	<td style=" height:100%; width: 600px; overflow-y:scroll; border: 1px solid #666; ">
	<div contentEditable="true"  style="height:300px; " >
	<textarea style="width:100%; resize:none; font-size: 16px;" rows="7" name="qcontent">
	</textarea>
	<!-- <input type="text" name="ngcontent" > -->
	<img  style="border:0px;" id="blah"/>
	<img style="border:0px;" id="blah2"/>

	</div>
	</td>

</tr>

</table>



<div style="margin: 0 auto; padding-top: 15px; width: 950px; height: 30px; text-align: right;">
<input class="btn border-btn more-black"
 type="submit" value="등록" onclick="writeddd();"
 style=" text-align:center; width:78px; height: 43px; border-color: #2E2E2E; color:black; ">&nbsp;
<input class="btn border-btn more-black"
 type="reset" value="취소" 
 style=" text-align:center; width:78px; height: 43px; border-color: #2E2E2E; color:black;">&nbsp;
<a href="/semi/qlist"  class="btn border-btn more-black" 
style=" text-align:center; width:78px; height: 43px; border-color: #2E2E2E; color:black;">목록 </a>
    </div>
</form>


<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%@ include file="../../footer.jsp" %>















