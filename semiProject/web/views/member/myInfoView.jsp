<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="semi.member.model.vo.Member, java.util.*" %>  
<%
	Member member = (Member)request.getAttribute("member");
	String mid = (String)session.getAttribute("m_id");
%>
<%@ include file="../../header.jsp" %>

<style type="text/css">
	#allbutton { width : 68px; height : 30px; font : 7px Sans-Serif; position : absolute; right : 10px; bottom : 160px;}
	#recentTable th{align : center;}
</style>

<script type="text/javascript">
	function moveboard(){
		location.href = "/semi/myboard?mid=<%= member.getmId() %>"
	}
	function movehome(){
		location.href = "/semi/myinfo?mid=<%= member.getmId() %>"
	}
	function movepoint(){
		location.href = "/semi/mypoint?mid=<%= member.getmId() %>"
	}
	function moverent(){
		location.href = "/semi/myrent?mid=<%= member.getmId() %>"
	}
	
	$(function(){
		$("#upBtn").click(function(){
			var pwd = $("#pwd2").val();
			if(pwd == null || pwd == ""){
				alert("암호확인을 입력해주세요.");
				return false;
				
			}
		})
	});
	
	
	 
	$(function(){
		//암호 입력 상자에서 포커스가 사라질 때 암호확인 처리함
		$("#pwd2").blur(function(){
			console.log("포커스가 사라짐...");
			var pwd1 = $("#pwd1").val();
			var pwd2 = $("#pwd2").val();
			
		
			
			if(pwd1 != pwd2){
				alert("암호와 암호확인이 일치하지 않습니다.");
				$("#pwd1").select();
			}			
		});
	});
	
	$(function(){
		var mid2 = "<%=mId%>";
		$.ajax({
			url : "/semi/btop3",
			type : "get",
			data : {"mid" : mid2},
			dataType : "json",
			success : function(data){				
				//console.log("success : " + data);			
				var jsonStr = JSON.stringify(data);
				//console.log(jsonStr);
				var json = JSON.parse(jsonStr);
				//console.log(json);
				
				var values = "";
				for(var i in json.list){
					values += "<tr><td>" + json.list[i].rbno
					 + "</td><td><a style='color : grey;'>"
					 + decodeURIComponent(json.list[i].rbtitle).replace("+", " ").replace("+", " ").replace("+", " ").replace("+", " ").replace("+", " ")
					 .replace("+", " ").replace("+", " ").replace("+", " ").replace("+", " ").replace("+", " ").replace("+", " ").replace("+", " ").replace("+", " ")
					 + "</a></td><td>" + json.list[i].rbdate 
					 + "</td><td>" + json.list[i].rbcount 
					 + "</td><td>" + decodeURIComponent(json.list[i].rbf1)
					 + "</td><td>" + decodeURIComponent(json.list[i].rbf2)
					 + "</td></tr>";
				}
				
				$("#toplist").html(values);
			},
			error : function(jqXHR, textstatus, errorThrown){
				console.log("error : " + jqXHR + ", " + 
						textstatus + ", " + errorThrown);
			}
		});  //ajax close
	});
	
	<%-- var mid1 = <%=mId%>;
	alert("mid1 : " + mId); --%>
	$(function(){
		var mid1 = "<%=mId%>";
		$.ajax({
			url : "/semi/rtop3",
			type : "get",
			data : {"mid" : mid1},
			dataType : "json",
			success : function(data){
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);
				
				var values = "";
				for(var i in json.list){
					
					values += "<tr><td>" + json.list[i].rpno
					 + "</td><td><a style='color : grey;'>"
					 + decodeURIComponent(json.list[i].rpname).replace("+", " ")
					 + "</a></td><td>" + json.list[i].rpcount
					 + "</td><td>" + json.list[i].rprice
					 + "</td></tr>"
					
				}
				$("#toplist2").html(values);
			},
			error : function(jqXHR, textstatus, errorThrown){
				console.log("error : " + jqXHR + ", " + 
						textstatus + ", " + errorThrown);
			}
		})
	});


	
</script>

<%@ include file="../../aside2.jsp" %>
<div class="page-head"> 
            <div class="container">
                <div class="row">
                    <div class="page-head-content">
                        <h1 class="page-title">09 : <span class="orange strong">My Page</span></h1>               
                    </div>
                </div>
            </div>
        </div>
        <!-- End page header --> 

<h1 align="center"><b><%= member.getmName() %></b> 님의 정보</h1><br>

<form action="/semi/mupdate.cp" method="post" style="margin-left : 15%;" onsubmit="pwd2Chk();"> 
<table width="600" align="center" cellspacing="5" 
bgcolor="#3399ff">
<tr><td><label>아이디</label></td>
<td>
	<input type="text" name="mid" 
	value="<%= member.getmId() %>" readonly>
</td></tr>
<tr><td><label>이 름</label></td>
<td>
	<input type="text" name="mname" 
	value="<%= member.getmName() %>" readonly>
</td></tr>
<tr><td><label>암 호</label></td>
<td>
	<input type="password" name="m_password1" id="pwd1"
	value="<%= member.getmPassword() %>">
</td></tr>
<tr><td><label>암호확인</label></td>
<td>
	<input type="password" id="pwd2" name="m_password2">
</td></tr>
<tr><td><label>닉네임</label></td>
<td>
	<input type="text" name="mnickname"
	value="<%= member.getmNickname() %>">
</td></tr>
<tr><td><label>포인트</label></td>
<td>
	<input type="text" name="mpoint"
	value="<%= member.getmPoint() %>" readonly>
</td></tr>
<tr><td><label>성 별</label></td>
<td>
	<% if(member.getmGender().equals("M")){ %>
	<input type="radio" name="mgender" value="M" checked> 남 &nbsp;
	<input type="radio" name="mgender" value="F"> 여
	<% }else{ %>
	<input type="radio" name="mgender" value="M"> 남 &nbsp;
	<input type="radio" name="mgender" value="F" checked> 여
	<% } %>
</td></tr>
<tr><td><label>주 소</label></td>
<td>
	<input style="width : 350px;" type="text" name="maddress" 
	value="<%= member.getmAddress() %>">
</td></tr>
<tr><td><label>전화번호</label></td>
<td>
	<input style="width : 350px;" type="tel" name="mphone" 
	value="<%= member.getmPhone() %>">
</td></tr>
<tr><td><label>이메일</label></td>
<td>
	<input style="width : 350px;" type="email" name="memail" 
	value="<%= member.getmEmail() %>">
</td></tr>	
<tr>
<tr><td><label>생년월일</label></td>
<td>
	<input style="width : 350px;" type="text" name="msno" 
	value="<%= member.getmSno() %>" readonly>
</td></tr>	
<tr>

	<td colspan="2" align="center">
		<br><input type="submit" value="수정하기" class="btn btn-default" style="margin-left : -45%;" id="upBtn">
		&nbsp; &nbsp;
		<a href="/semi/mdelete?mid=<%= member.getmId() %>">탈퇴하기</a>
	</td>
</tr>

</table>
<br>
</form>
<div class="clear">
	<br>
	<hr>
	<br>

	<button class="btn btn-default btn-lg"
		style="background: white; margin-left: 670px;" onclick="movehome();">
		<a style="color: black;">Home</a>
	</button>
	<!-- <button class="btn btn-default btn-lg" style="background: white;" onclick="movepoint();">
		<a style="color: black;">포인트 내역</a>
	</button> -->
	<button class="btn btn-default btn-lg" style="background: white;" onclick="moverent();">
		<a style="color: black;">최근 대여목록</a>
	</button>
	<button class="btn btn-default btn-lg" style="background: white;" onclick="moveboard();">
		<a style="color: black;">나의 게시글</a>
	</button>
	<br>
	<br>

	<div class="col-sm-5 col-sm-offset-1">
		<!-- 최근 대여목록 -->
		<div class="form-group">
			<table align="center" border="1" cellspacing="0" width="700"
				style="position: relative;" id="recentTable">
				<a>최근 대여 목록
				<button "id="allbutton" onclick = "moverent();" style="margin-left : 550px;"><a style="color : #777; font-size : 12px;">전체보기</a></button></a>
				 
			<!-- 	<button id="allbutton" onclick = "moverent();">
					<a style="color: #777;">전체보기</a>
					
				</button> -->
				<hr>

				 <thead>
					
					<th width="150px">물품번호</th>
					<th width="250px">상품명</th>
					<th width="100px">수량</th>
					<th width="200px">가격</th>
				</thead>
					<tbody id="toplist2"></tbody>
				<!-- <tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>

				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>

				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>

				</tr> -->
			</table> 
		</div>

	</div>


	
<div class="col-sm-5 col-sm-offset-1">
		<!-- 나의 게시글 -->
		<div class="form-group">
			<table align="center" border="1" cellspacing="0" width="700"
				style="position: relative;" id="">
				<a>나의 게시글
				<button "id="allbutton" onclick = "moveboard();" style="margin-left : 550px;"><a style="color : #777; font-size : 12px;">전체보기</a></button></a>
				<br>
				<!-- <button id="allbutton" onclick="moveboard();">
					<a style="color: #777;">전체보기</a>
				</button>  -->
				<hr>

				 
				 <thead>
					<th width="50px" >번호</th>
					<th width="200px">제목</th>
					<th width="100px">작성일자</th>
					<th width="50px">조회수</th>
					<th width="100px">첨부파일1</th>
					<th width="100px">첨부파일2</th>
				</thead>
				<tbody id="toplist"></tbody>
				<!-- <tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
				

				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					

				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					

				</tr> -->
			</table>
		</div>

	</div>

</div>

<hr>

<%@ include file="../../footer.jsp" %>