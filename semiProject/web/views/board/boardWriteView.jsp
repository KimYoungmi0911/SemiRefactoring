<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ page import="	semi.rental.model.vo.Rental, java.util.ArrayList "%>
<%

%>

<%@ include file="../../header.jsp"%>


<title>후기게시판 글쓰기</title>
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
	function writebbb(){
		alert("글 작성완료");
	}


</script>

<div class="s-property-title"
 style="text-align:center; ">
<h2>후기글 작성</h2>
</div>

<br>
<form action="/semi/binsert" method="post" enctype="multipart/form-data">
<table class="search-form wow pulse animated"
style="width:50%; height:80%; border-color: #FACC2E;"
id="table" align="center" cellspacing="0" >

<col width="80">
<col width="">

<tr style="height: 40px">
	<th style="text-align: center; font-size: 16px;">제목</th>
	<td><input type="text" name="btitle" style="border: 1px solid #666; width:35%;"></td>
</tr>

<tr style="height: 40px">
	<th style="text-align: center; font-size: 16px;">작성자</th>
	<td>
	<input type="text" name="bwriter" value="<%=  mId %>" style="border: 1px solid #666;" readonly>
	</td>
</tr>


<tr style="height: 40px">
	<th style="text-align: center; font-size: 16px;">첨부파일</th>
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

<tr style="height: 10px;">
<td colspan="2"></td>
</tr>

<tr >
	<th style="text-align: center; font-size: 16px;">내용</th>
	<td style=" height:100%; width: 600px; overflow-y:scroll; border: 1px solid #666; ">
	<div contentEditable="true"  style="height:300px; ">
	<textarea style="width:100%; resize:none; font-size: 16px;" rows="7" name="bcontent">
	</textarea>
	<!-- <input type="text" name="ngcontent" > -->
	<img  style="border:0px;" id="blah"/>
	<img style="border:0px;" id="blah2"/>

	</div>
	</td>

</tr>

</table>

<!-- <script type="text/javascript">
		function aclick(board){
			alert("ajax시작");
			var board = new Object();
			//var form = $('form')[0];
			//var formData = new FormData(form); 
			
			var bcontent = $("#bcontent").text();
			board.bcontent = $("#bcontent").text();
			board.btitle = $("#btitle").val();	
			board.bwriter = $("#bwriter").val();
			board.bupfile = $("#bupfile").val();
			board.bupfile2 = $("#bupfile2").val();  
			board.pro = $("#pro").val();
			
			var jsonStr = JSON.stringify(board);
			alert("ajax 전");
			$.ajax({
				url : "/semi/binsert",
				enctype: "multipart/form-data",
				type : "post",
				dataType : "json",
				data : {"jsonStr" : jsonStr },
				success : function(data){
					alert("성공");
				}, //success
				error : function(jqXHR, textstatus, errorThrown){
					console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
				} //error
		
			}); 
			alert("빠져나옴");
			$("#frm1").ajaxForm(jsonStr).submit();
		}

	
	</script> -->


<div 
style="margin: 0 auto; padding-top: 15px; width: 950px; height: 30px; text-align: right;">
<input class="btn border-btn more-black"
type="submit" value="등록" onclick="writebbb();"
style=" text-align:center; width:78px; height: 43px; border-color: #2E2E2E; color:black;">&nbsp;
<!-- <a href="/semi/blist" type="submit" onclick="aclick();" style="background-color: #fff; width:100px; height: 30px;">등록</a> &nbsp; -->
<input class="btn border-btn more-black"
type="reset" value="취소" 
style=" text-align:center; width:78px; height: 43px; border-color: #2E2E2E; color:black;">&nbsp;
<a href="/semi/blist" class="btn border-btn more-black"
style=" text-align:center; width:78px; height: 43px; border-color: #2E2E2E; color:black;">목록 </a>
    </div>
</form>


<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%@ include file="../../footer.jsp" %>
















