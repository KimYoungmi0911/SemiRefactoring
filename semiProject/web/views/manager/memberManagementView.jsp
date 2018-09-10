<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../../header.jsp" %>
	<style type="text/css">
		
	</style>
	<script type="text/javascript">

	//회원 조회용 변수
	var currentPage;
	var maxPage;
	var startPage;
	var endPage;
	var listCount;
	var pwdList = [];
	var memberInfo = new Object();
	var filter;
	
	$(function(){
		$("#table1 input").attr("readonly", true);
		$("body").append("<td>");
		$("#table1 input").css("font-size", "14px");		
		$("#idchkBtn").attr("style", "display:none");

	}); //document.ready

	//-------------------------------------------------------------
	//등록 radio
	function enrollChecked(){
		$("#enrollBtn").attr("style", "display:inline");
		$("#editBtn").attr("style", "display:none");
		$("#deleteBtn").attr("style", "display:none");
		
		$("#idchkBtn").attr("style", "display:block");
		
		$("#table1 input").attr("readonly", false);
		$("#table1 input").val("");

		return false;
	}
	//수정 radio
	function editChecked(){
		$("#enrollBtn").attr("style", "display:none");
		$("#editBtn").attr("style", "display:inline");
		$("#deleteBtn").attr("style", "display:none");
		
		$("#table1 input").attr("readonly", false);
		$("#mid1").attr("readonly", true);
		$("#idchkBtn").attr("style", "display:none");
		return false;
	}
	//삭제 radio
	function deleteChecked(){
		$("#enrollBtn").attr("style", "display:none");
		$("#editBtn").attr("style", "display:none");
		$("#deleteBtn").attr("style", "display:inline");
		
		$("#table1 input").attr("readonly", true);
		$("#idchkBtn").attr("style", "display:none");
		
		return false;
	}
	//-------------------------------------------------------------
	
	//회원 등록
	function enrollClick(){
		if(confirm("입력한 정보로 신규 회원을 등록 하시겠습니까?")){
			var birth1 = $("#birth1").val();
			var birth2 = $("#birth2").val();
			var b2;
			if(birth2 < 10)
				b2 = "0" + birth2;
			else
				b2 = birth2; 
			var birth3 = $("#birth3").val();
			 var b3;
			if(birth3 < 10)
				b3 = "0" + birth3;
			else
				b3 = birth3; 
			memberInfo.mname = $("#mname").val();
			memberInfo.mid = $("#mid1").val();
			memberInfo.mnick = $("#mnick").val();
			memberInfo.mpwd = $("#mpwd").val();
			memberInfo.msno = (birth1 + "-" + b2 + "-" + b3);
			if($("#T").is(":checked") == true)
				memberInfo.mgender = "남";
			else
				memberInfo.mgender = "여";
	
			memberInfo.mphone = $("#mphone").val();
			memberInfo.memail = $("#memail").val();
			
			var adr1 = $("#sample4_postcode").val();
			var adr2 = $("#sample4_roadAddress").val();
			memberInfo.maddress = (adr1 + ", " + adr2);
			
			memberInfo.mpoint = $("#mpoint").val(); 
			
			var jsonStr = JSON.stringify(memberInfo);
	
			$.ajax({
				url : "/semi/mmenroll",
				type : "post",
				data : {"jsonStr" : jsonStr},
				success : function(data){
					if(data >= 1)
						alert("회원 등록 완료");
					else
						alert("회원 등록 실패");
					paging(currentPage);
				}, //success
				error : function(jqXHR, textstatus, errorThrown){
					console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
				} //error
			});//ajax
			paging(1);
		}else{
			alert("회원 등록을 취소합니다.");
		}
		return false;
	} //enrollClick
	
	//회원 수정
	function editClick(memberInfo){
		
		if(confirm("변경된 정보로 정말 수정하시겠습니까?")){
			var birth1 = $("#birth1").val();
			var birth2 = $("#birth2").val();
			var birth3 = $("#birth3").val();
			var b2;
			/*if(birth2 < 10)
				b2 = "0" + birth2;
			else
				b2 = birth2;
			
			var b3;
			if(birth3 < 10)
				b3 = "0" + birth3;
			else
				b3 = birth3;*/
			memberInfo.mname = $("#mname").val();
			memberInfo.mid = $("#mid1").val();
			memberInfo.mnick = $("#mnick").val();
			memberInfo.mpwd = $("#mpwd").val();
			memberInfo.msno = (birth1 + "-" + birth2 + "-" + birth3);
			
			if($("#T").is(":checked") == true)
				memberInfo.mgender = "남";
			else
				memberInfo.mgender = "여";
	
			memberInfo.mphone = $("#mphone").val();
			memberInfo.memail = $("#memail").val();
			
			var adr1 = $("#sample4_postcode").val();
			var adr2 = $("#sample4_roadAddress").val();
			memberInfo.maddress = (adr1 + ", " + adr2);
			
			memberInfo.mpoint = $("#mpoint").val(); 
			
			var jsonStr = JSON.stringify(memberInfo);
	
			$.ajax({
				url : "/semi/mmedit",
				type : "post",
				data : {"jsonStr" : jsonStr},
				success : function(data){
					if(data >= 1)
						alert("회원 정보 수정 완료");
					else
						alert("회원 정보 수정 실패");
					paging(currentPage);
				}, //success
				error : function(jqXHR, textstatus, errorThrown){
					console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
				} //error
				
			});//ajax
		}else{
			alert("변경사항을 저장하지 않고 수정을 취소합니다.");
		}
		paging(currentPage);
		return false;
	}
	
	//회원 삭제
	function deleteClick(memberInfo){
		if(confirm(memberInfo.mid + " 회원을 정말 삭제하시겠습니까?")){
			$.ajax({
				url : "/semi/mmdelete",
				type : "post",
				data : {"mid" : memberInfo.mid},
				success : function(data){
					if(data >= 1)
						alert("회원 정보 삭제 완료");
					else
						alert(memberInfo.mid + "님의 회원 정보 삭제 실패");
					$("#table1 input").val("");
					paging(currentPage);
					
				}, //success
				error : function(jqXHR, textstatus, errorThrown){
					console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
				} //error
				
			}); //ajax
		}else{
			alert("회원 삭제를 취소합니다.");
		}
		paging(currentPage);
		return false;
	} //deleteClick
	
	//-------------------------------------------------------------
	
	//회원 선택시 table1 필드에 회원 정보 출력과 memberInfo에 회원 정보 담기
	function radioCheck(num){	
		//select한 회원 정보로 memberInfo 초기화
		memberInfo.mname = $("#nametd" + num).text();
		memberInfo.mid = $("#idtd" + num).text();
		memberInfo.mnick = $("#nicktd" + num).text();
		memberInfo.mpwd = pwdList[num];
		memberInfo.msno = $("#snotd" + num).text();
		memberInfo.mgender = $("#gendertd" + num).text();
		memberInfo.mphone = $("#phonetd" + num).text();
		memberInfo.memail = $("#emailtd" + num).text();
		memberInfo.maddress = $("#addresstd" + num).text();
		memberInfo.mpoint = $("#pointtd" + num).text();

		//table1에 회원 정보 출력
		//$("#mname").val($("#nametd" + num).text());
		$("#mname").val(memberInfo.mname);
		$("#mid1").val(memberInfo.mid);
		$("#mnick").val(memberInfo.mnick);
		$("#mpwd").val(memberInfo.mpwd);
		$("#mpwd2").val(memberInfo.mpwd);
		
		var sno = memberInfo.msno;	
		var year = sno.substring(0, 4);
		var month = sno.substring(6, 7);
		var day = sno.substring(9, 10);
		$("#birth1").val(year);
		$("#birth2").val(month);
		$("#birth3").val(day);

		if(memberInfo.mgender == "남"){
			$("#T").prop("checked", true);
			
		}else/*  if(memberInfo.mgender == "여") */{
			$("#F").prop("checked", true);
		}
		
		$("#mphone").val(memberInfo.mphone);
		$("#memail").val(memberInfo.memail);
		
		var adrSplit = memberInfo.maddress.split(",");
		$("#sample4_postcode").val(adrSplit[0]);
		$("#sample4_roadAddress").val(adrSplit[1]);
		
		$("#mpoint").val(memberInfo.mpoint);
		
		return false;
	}
	//-------------------------------------------------------------
	
	//회원 리스트 전체 조회
	function paging(page){
		$.ajax({
			url : "/semi/mmlist",
			type : "get",
			data : {"page" : page},
			dataType : "json",
			success : function(data){
				//회원 리스트 직렬화
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);
				
				//페이지 값 추출
				currentPage = json.currentPage;
				maxPage = json.maxPage;
				startPage = json.startPage;
				endPage = json.endPage;
				listCount = json.listCount;
				jsonListSize = json.list.length;
				jsonPwd = json.list
				
				var values = "";
				for(var i in json.list){
					for(var j = 0; j < json.list[i].maddress.length; j++){
					json.list[i].maddress = json.list[i].maddress.replace("+", " ");
					}
					values += "<tr><td><input type='radio' onclick='radioCheck(" + i + ")' name='choice' id='choice" + i + "'></td>"
					+ "<td id='nametd" + i + "'>" + decodeURIComponent(json.list[i].mname) + "</td>"
					+ "<td id='idtd" + i + "'>" + decodeURIComponent(json.list[i].mid) + "</td>"
					+ "<td id='nicktd" + i + "'>" + decodeURIComponent(json.list[i].mnick) + "</td>"
					+ "<td id='snotd" + i + "'>" + decodeURIComponent(json.list[i].msno) + "</td>"
					+ "<td id='gendertd" + i + "'>" + decodeURIComponent(json.list[i].mgender) + "</td>"
					+ "<td id='phonetd" + i + "'>" + decodeURIComponent(json.list[i].mphone) + "</td>"
					+ "<td id='emailtd" + i + "'>" + decodeURIComponent(json.list[i].memail) + "</td>"
					+ "<td id='addresstd" + i + "'>" + decodeURIComponent(json.list[i].maddress) + "</td>"
					+ "<td id='pointtd" + i + "'>" +  json.list[i].mpoint + "</td></tr>";
					
					pwdList.push(json.list[i].mpwd);
					
				} //for
				$("#t1").html(values); 
				
				//페이징 처리
				var firstP = "<li><a id='firstBtn' href='#' onclick='paging(" + startPage + ")'>[처음으로]</a></li>";
				var finalP = "<li><a id='finalBtn' href='#' onclick='paging(" + endPage + ")'>[끝으로]</a></li>";
				
				for(var p = startPage; p <= endPage; p++){ 
					if(p == startPage)
						$("#domain").html(firstP);
					
					//if(p > startPage)
					$("#domain").append("<li><a href='#' onclick='paging(" + p + ")'>" + p + "</a></li>");
					
					if(p == endPage)
						$("#domain").append(finalP);	
				} //paging for
				
			}, //success
				error : function(jqXHR, textstatus, errorThrown){
					console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
			} //error
		
		}); //ajax

		return false;
	}
	
	//-------------------------------------------------------------
	
	//필터 선택 조회 
	function selectBtnClick(){
			
		filter = $("#filter").val();
		value = $("#searchTF").val();

		$.ajax({
			url : "/semi/mmselect",
			type : "post",
			data : {"filter" : filter, "value" : value},
			dataType : "json",
			success : function(data){
				//회원 리스트 직렬화
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);
				
				var values = "";
				for(var i in json.list){
					for(var j = 0; j < json.list[i].maddress.length; j++){
						json.list[i].maddress = json.list[i].maddress.replace("+", " ");
						}
					values += "<tr><td><input type='radio' onclick='radioCheck(" + i + ")' name='choice' id='choice" + i + "'></td>"
					+ "<td id='nametd" + i + "'>" + decodeURIComponent(json.list[i].mname) + "</td>"
					+ "<td id='idtd" + i + "'>" + decodeURIComponent(json.list[i].mid) + "</td>"
					+ "<td id='nicktd" + i + "'>" + decodeURIComponent(json.list[i].mnick) + "</td>"
					+ "<td id='snotd" + i + "'>" + decodeURIComponent(json.list[i].msno) + "</td>"
					+ "<td id='gendertd" + i + "'>" + decodeURIComponent(json.list[i].mgender) + "</td>"
					+ "<td id='phonetd" + i + "'>" + decodeURIComponent(json.list[i].mphone) + "</td>"
					+ "<td id='emailtd" + i + "'>" + decodeURIComponent(json.list[i].memail) + "</td>"
					+ "<td id='addresstd" + i + "'>" + decodeURIComponent(json.list[i].maddress) + "</td>"
					+ "<td id='pointtd" + i + "'>" +  json.list[i].mpoint + "</td></tr>";
					
					pwdList.push(json.list[i].mpwd);
				} //for

				$("#t1").html(values); 
				
			}, //success
			error : function(jqXHR, textstatus, errorThrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
			} //error
		}); //ajax
		return false;	
	}
	
	//아이디 중복확인
	function dupIdCheck() {
		$.ajax({
			url : "/semi/idchk",
			type : "post",
			data : {
				m_id : $("#mid1").val()},
			success : function(data) {
				console.log("success : " + data);
				if (data == "ok") {
					alert("사용 가능한 아이디입니다.");
				} else {
					alert("이미 사용중인 아이디입니다.\n" + "다시 입력하십시오.");
				}
			},
			error : function(jqXHR, textstatus, errorthrown) {
				console.log("error : " + jqXHR + ", " + textstatus + ", "
						+ errorthrown);
			}
		});
		return false;
	}

</script>

<body>
<div class="properties-area recent-property" style="background-color: #FFF;">
	<div class="container" style="width:1400px;height:1400px;">  
                <div class="row">              
                <div class="col-md-3 p0 padding-top-40" style="width:300px;height:800;">
                    <div class="blog-asside-right pr0">
                        <div class="panel panel-default sidebar-menu wow fadeInRight animated animated" style="visibility: visible; animation-name: fadeInRight;">
                            <div class="panel-heading">
                                <h3 class="panel-title">회원관리</h3>
                            </div>
                            <div class="panel-body search-widget" id="table1">
                                <!-- <form action="" class=" form-inline">  -->
                                    <fieldset>
                                    	<div class="row">
                                            <div class="col-xs-12">
                                            	<input type="radio" id="radio1" name="radioC" onclick="enrollChecked();">등록
												<input type="radio" id="radio2" name="radioC" onclick="editChecked();">수정
												<input type="radio" id="radio3" name="radioC" onclick="deleteChecked();">삭제
                                            </div>
                                        </div>
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>이름</label>
                                            </div>
                                            <div class="col-xs-12">
                                            	<input type="text" id="mname" name="mname" placeholder="이름" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>아이디</label>
                                            </div>
                                            <div class="col-xs-12">
                                                <input type="text" id="mid1" name="mid1" placeholder="아이디" class="form-control">
                                            </div>                    
                                            <div id="idchkBtn" class="col-xs-12" style="display:none;">
                                            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            	<button type="button" id="idcheck" name="idcheck" class="btn btn-finish btn-primary" onclick="dupIdCheck();">중복확인</button>
                                            </div>
                                        </div>
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>닉네임</label>
                                            </div>
                                            <div class="col-xs-12">
                                                <input type="text" id="mnick" name="mnick" placeholder="닉네임" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>암호</label>
                                            </div>
                                            <div class="col-xs-12">
                                                <input type="password" id="mpwd" name="mpwd" placeholder="Password" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>암호확인</label>
                                            </div>
                                            <div class="col-xs-12">
                                                <input type="password" id="mpwd2" name="mpwd2" placeholder="Password2" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>생년월일</label>
                                            </div>
                                            <div class="col-xs-12">
                                                <script type="text/javascript">
												//생년월일
												var today = new Date();
												var toyear = parseInt(today.getFullYear());
												var start = toyear;
												var end = toyear - 70;
										
												document.write("<font size=2><select id=birth1 name=birth1>");
												document.write("<option value='' selected>");
												for (i=start;i>=end;i--) document.write("<option>"+i);
												document.write("</select>년  "); 
										
												document.write("<select id=birth2 name=birth2>");
												document.write("<option value='' selected>");
												for (i=1;i<=12;i++) document.write("<option>"+i);
												document.write("</select>월  ");
										
												document.write("<select id=birth3 name=birth3>");
												document.write("<option value='' selected>");
												for (i=1;i<=31;i++) document.write("<option>"+i); 
												document.write("</select>일   </font>");
												</script>
                                            </div>
                                        </div>
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>성별</label>
                                            </div>
                                            <div class="col-xs-12">
                                                <input type="radio" id="T" name="mgender" value="T">남
												<input type="radio" id="F" name="mgender" value="F">여
                                            </div>
                                        </div>
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>전화번호</label>
                                            </div>
                                            <div class="col-xs-12">
                                                <input type="phone" id="mphone" name="mphone" placeholder="전화번호" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>이메일</label>
                                            </div>
                                            <div class="col-xs-12">
                                                <input type="email" id="memail" name="memail" placeholder="Email" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>주소</label>
                                            </div>
                                            <div class="col-xs-12">
                                                <input type="text" id="sample4_postcode" placeholder="우편번호" style="width: 200px; height: 25px; border-radius: 4px;" class="form-control">
                                                <button type="button" onclick="sample4_execDaumPostcode()" class="btn btn-finish btn-primary" style="width: 120px; height: 30px; border-radius: 4px;">우편번호 찾기</button>                                               
                                                <input type="text" id="sample4_roadAddress" placeholder="도로명주소" name="m_address" class="form-control" style="width: 200px; height: 25px; border-radius: 4px;">
                                                <input type="text" id="sample4_jibunAddress" placeholder="지번주소" class="form-control" class="form-control" style="width: 200px; height: 25px; border-radius: 4px;">
												<span id="guide" style="color: #999"></span> 
                                            </div>
                                            <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script> 
											<script charset="UTF-8" type="text/javascript" src="http://t1.daumcdn.net/postcode/api/core/180619/1529384927473/180619.js"></script>
											<br>
											<script>
												//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
												function sample4_execDaumPostcode() {
													new daum.Postcode(
															{
																oncomplete : function(data) {
																	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
						
																	// 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
																	// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
																	var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
																	var extraRoadAddr = ''; // 도로명 조합형 주소 변수
						
																	// 법정동명이 있을 경우 추가한다. (법정리는 제외)
																	// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
																	if (data.bname !== ''
																			&& /[동|로|가]$/g
																					.test(data.bname)) {
																		extraRoadAddr += data.bname;
																	}
																	// 건물명이 있고, 공동주택일 경우 추가한다.
																	if (data.buildingName !== ''
																			&& data.apartment === 'Y') {
																		extraRoadAddr += (extraRoadAddr !== '' ? ', '
																				+ data.buildingName
																				: data.buildingName);
																	}
																	// 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
																	if (extraRoadAddr !== '') {
																		extraRoadAddr = ' ('
																				+ extraRoadAddr + ')';
																	}
																	// 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
																	if (fullRoadAddr !== '') {
																		fullRoadAddr += extraRoadAddr;
																	}
						
																	// 우편번호와 주소 정보를 해당 필드에 넣는다.
																	document
																			.getElementById('sample4_postcode').value = data.zonecode; //5자리 새우편번호 사용
																	document
																			.getElementById('sample4_roadAddress').value = fullRoadAddr;
																	document
																			.getElementById('sample4_jibunAddress').value = data.jibunAddress;
						
																	// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
																	if (data.autoRoadAddress) {
																		//예상되는 도로명 주소에 조합형 주소를 추가한다.
																		var expRoadAddr = data.autoRoadAddress
																				+ extraRoadAddr;
																		document
																				.getElementById('guide').innerHTML = '(예상 도로명 주소 : '
																				+ expRoadAddr + ')';
						
																	} else if (data.autoJibunAddress) {
																		var expJibunAddr = data.autoJibunAddress;
																		document
																				.getElementById('guide').innerHTML = '(예상 지번 주소 : '
																				+ expJibunAddr + ')';
						
																	} else {
																		document
																				.getElementById('guide').innerHTML = '';
																	}
																}
															}).open();
												}
											</script>
                                        </div>
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>포인트</label>
                                            </div>
                                            <div class="col-xs-12">
                                               <input type="text" id="mpoint" name="mpoint" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row" style="display:flex;align-items:center;justify-content:center;">
                                            <div class="col-xs-12">
                                               <button class="btn btn-finish btn-primary" type="button" id="enrollBtn" name="enrollBtn"  onclick="enrollClick();" style="display:none;">저장</button>
												<button class="btn btn-finish btn-primary" type="button" id="editBtn" name="editBtn" onclick="editClick(memberInfo);" style="display:none;">완료</button>
												<button class="btn btn-finish btn-primary" type="button" id="deleteBtn" name="deleteBtn" onclick="deleteClick(memberInfo);" style="display:none;">삭제</button>
												<button class="btn btn-finish btn-primary" type="reset" id="cancleBtn" name="cancleBtn">취소</button>
                                            </div>
                                        </div>
                                        
                                    </fieldset>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-9  pr0 padding-top-40 properties-page" style="width:950px;height:1000px;">
                    <div class="col-md-12 clear"> 
                        <div class="col-xs-10 page-subheader sorting pl0">
                           <ul class="sort-by-list">
                                <li class="active">
                                	<select class="btn dropdown-toggle btn-default" id="filter" name="filter">
		                              <!-- <select class="selectpicker" id="filter" name="filter"> -->
										<option>선택</option>
										<option>이름</option>	
										<option>아이디</option>		
										<option>닉네임</option>	
										<option>전화번호</option>	
									</select>                                      
                                </li>
                                <li class="">
                                    <input style="width:400px;" class="form-control" type="search" size="80px" id="searchTF" name="search">
                                </li>
                                <li class="">
                                    <button class="btn btn-finish btn-primary" type="button" id="selecthBtn" name="selecthBtn" onclick="selectBtnClick();">조회</button>
                                </li>
                                <li class="">
                                    <button class="btn btn-finish btn-primary" type="button" id="listBtn" name="listBtn" onclick="paging(1);">전체조회</button>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-md-12 clear"> 
                    	<table class="table" id="table2">
                    		<thead>
                            <tr>
								<th width="25">선택</th>
								<th width="100">이름</th>
								<th width="100">아이디</th>
								<th width="100">닉네임</th>
								<th width="140">생년월일</th>
								<th width="70">성별</th>
								<th width="100">전화번호</th>
								<th width="100">이메일</th>
								<th width="300">주소</th>
								<th width="100">포인트</th>
							</tr>
							</thead>
							<tbody id="t1">
							</tbody>
						</table>
                    </div>
                    <div class="col-md-12"> 
                        <div class="pull-right">
                            <div class="pagination">
                                <ul id="domain">
        
                                </ul>
                            </div>
                        </div>                
                    </div>    
                </div>  
                </div>              
            </div>
</div><!-- properties-area -->


<%@ include file="../../footer.jsp"%>