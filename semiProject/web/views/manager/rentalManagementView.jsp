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
	var rentalInfo = new Object();
	var filter;
	
	//select box default select 설정용 Date 변수
	var sysDate = new Date();
	var sysYear = sysDate.getFullYear();
	var sysMonth = sysDate.getMonth() + 1;
	var sysDay = sysDate.getDate();
	
	var localFilter;	//지역명
	var localNameFilter;	//동사무소명
	var productName; //물품명
	var radioFilter;	//table2 radio filter
	
	//table 2 data
	var value;
	var mainFilter;
	var sFilter;
	
	var rNo;
	
	
	$(function(){
		$("#table1 input").attr("readonly", true);
		$("#table1 input").css("font-size", "14px");	
		
		//지역명 select 이벤트 발생시
		$("#local1").on("change", function(){
			localFilter = $("#local1 :selected").val();
			$("#local2").html("");
			localNamePrint(localFilter);
			return false;
		});
		//동사무소명 select 이벤트 발생시
		$("#local2").on("change", function(){
			$("#pname").html("");
			localNameFilter = $("#local2 :selected").val();
			productPtint(localNameFilter);	//물품명 출력함수 호출	
			return false;
		});
	}); //document.ready
	
	//-------------------------------------------------------------function
	//등록 radio
	function enrollChecked(){	
		$("#saveBtn").attr("style", "display:inline");
		$("#editSaveBtn").attr("style", "display:none");
		$("#returnBtn").attr("style", "display:none");	
		$("#table1 input").not("#pitem").not($("#rnum")).not($("#rdate")).not($("#rstartdate")).not("#pnum").not("#pcount").not("#rprice").not("#rstate").attr("readonly", false);
		$("#rnum").attr("readonly", true);
		$("#daydiv1").attr("style", "display:none");
		$("#daydiv2").attr("style", "display:none");
		$("#table1 input").val("");
		$("#idCheck").attr("style", "display:inline-block");

		//대여/반납접수일자, 대여실행일자 기본 값
		var sysMonth1 = 0;
		var sysDay1 = 0;
		
		if(sysMonth < 10){
			sysMonth1 = "0" + sysMonth;
		}else{
			sysMonth1 = sysMonth;
		}
		if(sysDay < 10){
			sysDay1 = "0" + sysDay;
		}else{
			sysDay1 = sysDay;
		}		
		$("#rdate").val(sysYear + "-" + sysMonth1 + "-" + sysDay1);
		$("#rreturndate").val(sysYear + "-" + sysMonth1 + "-" + (sysDay1+1));
		$("#rstartdate").val(sysYear + "-" + sysMonth1 + "-" + sysDay1);
		
		localPrint(1);	//지역명 출력 함수 호출

		return false;
	}
	
	//수정 radio
	function editChecked(){
		$("#saveBtn").attr("style", "display:none");
		$("#editSaveBtn").attr("style", "display:inline-block");
		$("#returnBtn").attr("style", "display:inline-block");	
		$("#rstartdate").attr("readonly", false);
		$("#rrastdate").attr("readonly", true);		
		$("#rstartdate").attr("readonly", true);
		$("#rreturndate").attr("readonly", false);
		//$("#rlastdate").attr("readonly", true);
		$("#idchkBtn").attr("style", "display:none");
		$("#daydiv1").attr("style", "display:inline-block");
		$("#daydiv2").attr("style", "display:inline-block");
		$("#idCheck").attr("style", "display:none");
		return false;
	}
	
	//지역명 출력 함수
	function localPrint(){
		$.ajax({
			url : "/semi/rmlselect",
			type : "post",
			dataType : "json",
			success : function(data){
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);
				
				for(var i = 0; i < json.list.length; i++){
						$("#local1").append("<option>" + decodeURIComponent(json.list[i].lloca) + "</option>");
				} //for
			}, //success
			error : function(jqXHR, textstatus, errorThrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
			} // error
		}); //ajax
		
		return false;
	} //localPrint end-

	//동사무소명 출력 함수
	function localNamePrint(localFilter){
		$.ajax({
			url : "/semi/rmlnselect",
			type : "post",
			data : {"local" : localFilter},
			dataType : "json",
			success : function(data){
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);
				
					$("#local2").append("<option>동사무소선택</option>");				
				for(var i = 0; i < json.list.length; i++){
					json.list[i].lname = json.list[i].lname.replace("+", " ");
					//if(num == 1)
						$("#local2").append("<option>" + decodeURIComponent(json.list[i].lname) + "</option>");
				} //for
			}, //success
			error : function(jqXHR, textstatus, errorThrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
			} // error
		}); //ajax
		return false;
	}	
	
	//물품명 출력함수
	function productPtint(localNameFilter){
		$.ajax({
			url : "/semi/rmpnselect",
			type : "post",
			data : {"lname" : localNameFilter},
			dataType : "json",
			success : function(data){
				
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);
				
					$("#pname").append("<option>물품선택</option>");
				for(var i = 0; i < json.list.length; i++){
						$("#pname").append("<option>" + decodeURIComponent(json.list[i].pname) + "</option>");
				}
			}, //success
			error : function(jqXHR, textstatus, errorThrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
			}
		}); //ajax	
		return false;
	} 
	
	//table1 물품명 select 이벤트 발생시
	function productNameSelectEvent(){
		//$("#pname").html("");
		productName = $("#pname :selected").val();
		productInfoPtint(productName, localNameFilter); //물품정보 출력함수 호출
		return false;
	}	
	
	//tabl1 물품정보 출력함수
	function productInfoPtint(productName, localNameFilter){	
		$.ajax({
			url : "/semi/rmpiselect",
			type : "post",
			data : {"pname" : productName, "lname" : localNameFilter},
			dataType : "json",
			success : function(data){
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);				
				
				$("#pnum").val(json.pno);
				$("#rprice").val(json.pprice);
				$("#pcount").val(json.pcount);
				$("#rstate").val(decodeURIComponent(json.pstate));	
				$("#pitem").val(decodeURIComponent(json.pitem));
			}, //success
			error : function(jqXHR, textstatus, errorThrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
			}
		}); //ajax		
		return false;
	} //productPrint2 end-

	//-----------------------------------------------------------------------table1 function end--

	//--------------------------------------------------table2 function start
	
	//대여목록 전체 조회
	function allselectClick(page){
		$.ajax({
			url : "/semi/rmslist",
			type : "post",
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
				jsonPwd = json.list;
				
				var jRstartdate;
				var jRlastdate;
				
				var values = "";
				for(var i in json.list){
					if(decodeURIComponent(json.list[i].rstartdate) == "2011-11-11")
						jRstartdate = " ";
					else
						jRstartdate = decodeURIComponent(json.list[i].rstartdate);
					
					if(decodeURIComponent(json.list[i].rlastdate) == "2011-11-11")
						jRlastdate = " ";
					else
						jRlastdate = decodeURIComponent(json.list[i].rlastdate);
					
					values += "<tr><td><input type='radio' onclick='radioCheck(" + i + ")' name='choice' id='choice" + i + "'></td>"
					+ "<td id='rnotd" + i + "'>" + decodeURIComponent(json.list[i].rno) + "</td>"
					+ "<td id='pnotd" + i + "'>" + decodeURIComponent(json.list[i].pno) + "</td>"
					+ "<td id='pcounttd" + i + "'>" + json.list[i].pcount + "</td>"
					+ "<td id='rpricetd" + i + "'>" + json.list[i].rprice + "</td>"
					+ "<td id='midtd" + i + "'>" + decodeURIComponent(json.list[i].mid) + "</td>"
					+ "<td id='rdatetd" + i + "'>" + decodeURIComponent(json.list[i].rdate) + "</td>"
					+ "<td id='rstartdatetd" + i + "'>" + jRstartdate + "</td>"
					+ "<td id='rreturntd" + i + "'>" + decodeURIComponent(json.list[i].rreturndate) + "</td>"
					+ "<td id='rlasttd" + i + "'>" + jRlastdate + "</td>"
					+ "<td id='pstatetd" + i + "'>" +  decodeURIComponent(json.list[i].pstate) + "</td></tr>";
										
				} //for
				$("#t1").html(values); 
				
				//페이징 처리
				var firstP = "<li><a id='firstBtn' href='#' onclick='allselectClick(" + startPage + ")'>[처음으로]</a></li>";
				var finalP = "<li><a id='finalBtn' href='#' onclick='allselectClick(" + endPage + ")'>[끝으로]</a></li>";
				
				for(var p = startPage; p <= endPage; p++){ 
					if(p == startPage)
						$("#domain").html(firstP);
					
					$("#domain").append("<li><a href='#' onclick='allselectClick(" + p + ")'>" + p + "</a></li>");
					
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
	//아이디 유효성검사
	function dupIdCheck() {
		$.ajax({
			url : "/semi/idchk",
			type : "post",
			data : {
				m_id : $("#mid1").val()},
			success : function(data) {
				console.log("success : " + data);
				
				if (data == "ok") {		
					alert("존재하지 않는 회원입니다");
				} else {
					alert("회원 인증 완료");	
				}
			},
			error : function(jqXHR, textstatus, errorthrown) {
				console.log("error : " + jqXHR + ", " + textstatus + ", "
						+ errorthrown);
			}
		});
		return false;
	}
	
	//조회 버튼 클릭시 
	function selectBtnClick(){

		sFilter = $("#searchfilter :selected").val();
		if($("#searchTF").val() == ""){
			value = "no";
		}else{
			value = $("#searchTF").val();
		}	
		if($("#allselect").is(":checked") == true && value == "no" && sFilter == "전체")// && $("#searchTF").val() == null && )
			allselectClick(1);
		else if($("#allselect").is(":checked") == true && sFilter == "전체" && value != "no")
			alert("조회항목을 선택하고 조회내용을 입력하십시오.");
		else if($("#dayselect").is(":checked") == true && $("#dayselectinput").val() == "")
			alert("조회할 날짜를 선택하십시오.");
		else if($("#monthselect").is(":checked") == true && $("#monthselectinput").val() == "")
			alert("조회할 달을 선택하십시오.");
		else// if($("#dayselect").is(":checked") == true || $("#monthselect").is(":checked") == true)
			filterSearch();
		return false;
	}
	
	//필터 선택 조회 
	function filterSearch(){
	//value, sFilter
		var fVal;
	
		if($("#dayselect").is(":checked") == true){		
			mainFilter = "day";
			fVal = $("#dayselectinput").val();
			
		}else if($("#monthselect").is(":checked") == true){
			mainFilter = "month";
			fVal = $("#monthselectinput").val();
		}else{
			mainFilter = "all";
			fVal = "all";
		}
		
		$.ajax({
			url : "/semi/rmfselect",
			type : "post",
			data : { 
				"value" : value, 
				"mainFilter" : mainFilter,
				"fVal" : fVal, 
				"sFilter" : sFilter},
			dataType : "json",
			success : function(data){
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);
				
				if(json.result > 0){
				
				var jRstartdate;
				var jRlastdate;
				
				var values = "";
				for(var i in json.list){
					if(decodeURIComponent(json.list[i].rstartdate) == "2011-11-11")
						jRstartdate = " ";
					else
						jRstartdate = decodeURIComponent(json.list[i].rstartdate);
					
					if(decodeURIComponent(json.list[i].rlastdate) == "2011-11-11")
						jRlastdate = " ";
					else
						jRlastdate = decodeURIComponent(json.list[i].rlastdate);
					
					values += "<tr><td><input type='radio' onclick='radioCheck(" + i + ")' name='choice' id='choice" + i + "'></td>"
					+ "<td id='rnotd" + i + "'>" + decodeURIComponent(json.list[i].rno) + "</td>"
					+ "<td id='pnotd" + i + "'>" + decodeURIComponent(json.list[i].pno) + "</td>"
					+ "<td id='pcounttd" + i + "'>" + json.list[i].pcount + "</td>"
					+ "<td id='rpricetd" + i + "'>" + json.list[i].rprice + "</td>"
					+ "<td id='midtd" + i + "'>" + decodeURIComponent(json.list[i].mid) + "</td>"
					+ "<td id='rdatetd" + i + "'>" + decodeURIComponent(json.list[i].rdate) + "</td>"
					+ "<td id='rstartdatetd" + i + "'>" + jRstartdate + "</td>"
					+ "<td id='rreturntd" + i + "'>" + decodeURIComponent(json.list[i].rreturndate) + "</td>"
					+ "<td id='rlasttd" + i + "'>" + jRlastdate + "</td>"
					+ "<td id='pstatetd" + i + "'>" +  decodeURIComponent(json.list[i].pstate) + "</td></tr>";
										
				} //for
				$("#t1").html(values); 
				}else{
					alert("조회 조건에 맞는 대여정보가 존재하지 않습니다.");
				}
			}, //success
			error : function(jqXHR, textstatus, errorThrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
			} //error
		}); //ajax
		
		return false;	
	}
	
	//대여정보 선택시 table1 필드에 출력
	function radioCheck(num){	
		//$("#pstatediv").html("<div style='display:none'><input id='pstateinput' value='" + num + "'></div>");
		//선택한 대여정보 담기
		rentalInfo.rno = $("#rnotd" + num).text();
		rentalInfo.pno = $("#pnotd" + num).text();
		rentalInfo.pcount = $("#pcounttd" + num).text();
		rentalInfo.rprice = $("#rpricetd" + num).text();
		rentalInfo.mid = $("#midtd" + num).text();
		rentalInfo.rdate = $("#rdatetd" + num).text();
		rentalInfo.rstartdate = $("#rstartdatetd" + num).text();
		rentalInfo.rreturn = $("#rreturntd" + num).text();
		rentalInfo.rlast = $("#rlasttd" + num).text();
		rentalInfo.pstate = $("#pstatetd" + num).text();

		//table1에 대여 정보 출력
		$("#rnum").val(rentalInfo.rno);
		$("#pnum").val(rentalInfo.pno);
		$("#pcount").val(rentalInfo.pcount);
		$("#rprice").val(rentalInfo.rprice);
		$("#mid1").val(rentalInfo.mid);
		$("#rdate").val(rentalInfo.rdate);
		if(rentalInfo.rstartdate == "2011-11-11")
			$("#rstartdate").val();
		else
			$("#rstartdate").val(rentalInfo.rstartdate);
		
		$("#rreturndate").val(rentalInfo.rreturn);
		if(rentalInfo.rlast == "2011-11-11")
			$("#rlastdate").val();
		else
			$("#rlastdate").val(rentalInfo.rlast);
		$("#rstate").val(rentalInfo.pstate);
		
		$.ajax({
			url : "/semi/rmtp",
			type : "post",
			data : {"pno" : rentalInfo.pno},
			dataType : "json",
			success : function(data){
				
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);		
				
				$("#local1").html("<option>" + decodeURIComponent(json.local1) + "</option>");
				json.local2 = json.local2.replace("+", " ");
				$("#local2").html("<option>" + decodeURIComponent(json.local2) + "</option>");	
				$("#pname").html("<option>" + decodeURIComponent(json.pname) + "</option>");		
				$("#pitem").val(decodeURIComponent(decodeURIComponent(json.pitem)));
				if(decodeURIComponent(json.rbookdate) == "2011-11-11")
					$("#rbookdate").val();
				else
					$("#rbookdate").val(decodeURIComponent(json.rbookdate));
			},	//success
			error : function(jqXHR, textstatus, errorThrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
			}
		}); 
		return false;
	}
	
	//반납 처리
	function returnClick(){
		rentalInfo.rno = $("#rnum").val();
		rentalInfo.pno = $("#pnum").val();
		if($("#rstate").val() == "대여중"){
			if(confirm("정말 반납처리 하시겠습니까?")){
				$.ajax({
					url : "/semi/rmpreturn",
					type : "post",
					data : {"rno" : rentalInfo.rno, 
						"pno" : rentalInfo.pno},
					success : function(data){
						if(data > 0)
							alert("반납 처리 완료");
						else
							alert("반납 처리 실패");
					}, 
					error : function(jqXHR, textstatus, errorThrown){
						console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
					}
				});
			}else{
				alert("반납이 취소되었습니다.");
			}
			allselectClick(1);
		}else{
			alert("이미 반납된 물품입니다.");
		}
		allselectClick(1);
		return false;
	}
	
	//대여기간 연장
	function updateReturnDate(){
		rentalInfo.rno = $("#rnum").val();
		rentalInfo.rreturn = $("#rreturndate").val();
		//alert("return : " + rentalInfo.rreturn);
		
		if(confirm("대여기간을 " + rentalInfo.rreturn + " 까지 연장하시겠습니까?")){
		$.ajax({
			url : "/semi/rmupreturn",
			type : "post",
			data : {"rno" : rentalInfo.rno, 
				"returndate" : rentalInfo.rreturn},
			success : function(data){
				if(data > 0){
					alert("대여기간 " + rentalInfo.rreturn + " 까지 연장 완료");
					allselectClick(1);
				}else{
					alert("대여기간 연장 실패");}
			},
			error : function(jqXHR, textstatus, errorThrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
			}
		});
		}else{
			alert("대여기간 연장을 취소합니다.");
		}
		allselectClick(1);
		return false;
	}
	
	
	//대여목록 등록
	function insertClick(){
		//rentalInfo.rno = $("#rnum").val();
		rentalInfo.pno = $("#pnum").val();
		rentalInfo.mid = $("#mid1").val();
		rentalInfo.pcount = $("#pcount").val();
		rentalInfo.rprice = $("#rprice").val();
		rentalInfo.rreturn = $("#rreturndate").val();
		
		if(confirm("입력한 정보로 대여 등록 하시겠습니까?")){
			var jsonStr = JSON.stringify(rentalInfo);
			$.ajax({
				url : "/semi/mrlinsert",
				type : "post",
				data : {"jsonStr" : jsonStr},
				//dataType : "json",
				success : function(data){
					//alert("석세스?");
					if(data != null){
						rentalInfo.rno = data;
						insertRental(rentalInfo);	
					}else{
						alert("대여 목록 등록 실패");
					}
				}, //success
				error : function(jqXHR, textstatus, errorThrown){
					console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
				} //error
			});//ajax
		}else{
			alert("대여 등록을 취소합니다.");
		}	
		return false;		
	} //enrollClick
	
	function insertRental(rentalInfo){
		var jsonStr = JSON.stringify(rentalInfo);
		$.ajax({
			url : "/semi/rmrinsert",
			type : "post",
			data : {"jsonStr" : jsonStr},
			//dataType : "json",
			success : function(data){
				if(data >= 1)
					alert("대여 등록 완료");
				else
					alert("대여 등록 실패");
			}, //success
			error : function(jqXHR, textstatus, errorThrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
			} //error
		});//ajax
		allselectClick(1);
	return 1;
	}
	
	//-------------------------------------------------------------

</script>

<body>
<div class="properties-area recent-property" style="background-color: #FFF;">
	<div class="container" style="width:1400px;height:1400px;">  
                <div class="row">              
                <div class="col-md-3 p0 padding-top-40" style="width:300px;height:800;">
                    <div class="blog-asside-right pr0" id="pstatediv">
                        <div class="panel panel-default sidebar-menu wow fadeInRight animated animated" style="visibility: visible; animation-name: fadeInRight;">
                            <div class="panel-heading">
                                <h3 class="panel-title">대여관리</h3>
                            </div>
                            <div class="panel-body search-widget" id="table1">
                                <!-- <form action="" class=" form-inline">  -->
                                    <fieldset>
                                    	<div class="row">
                                            <div class="col-xs-12">
                                            	<input type="radio" id="radio1" name="radioC" onclick="enrollChecked();">등록
												<input type="radio" id="radio2" name="radioC" onclick="editChecked();">수정
                                            </div>
                                        </div>
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>대여번호</label>
                                            </div>
                                            <div class="col-xs-12">
                                            	<input type="text" id="rnum" placeholder="대여번호" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>대여소</label>
                                            </div>
                                            <div class="col-xs-12">
                                            	<select class="btn dropdown-toggle btn-default" id="local1">
													<option>지역전체</option>
												</select> 
												<select class="btn dropdown-toggle btn-default" id="local2">
													<option>동사무소전체</option>
												</select> 
                                            </div>
                                        </div>                                       
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>물품명</label>
                                            </div>
                                            <div class="col-xs-12">
                                                <select onChange="productNameSelectEvent();" class="btn dropdown-toggle btn-default" id="pname" style="width:223px;">
													<option>물품전체</option>
												</select> 
                                            </div>                    
                                        </div>
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>품목</label>
                                            </div>
                                            <div class="col-xs-12">
												<input type="text" id="pitem" placeholder="품목" class="form-control" readonly>
                                            </div>                    
                                        </div>   
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>물품번호</label>
                                            </div>
                                            <div class="col-xs-12">
												<input type="text" id="pnum" placeholder="물품번호" class="form-control" readonly>
                                            </div>                    
                                        </div>                    
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>수량</label>
                                            </div>                                            
                                            <div class="col-xs-12" style="width:53px;">
												<input type="number" id="pcount" placeholder="수량" class="form-control" readonly>
                                            </div>    
                                            <div class="col-xs-12" style="width:55px;">
                                            	<label>대여금액</label>
                                            </div>
                                            <div class="col-xs-12">
                                                <input type="text" id="rprice" placeholder="대여금액" class="form-control" style="width: 100px;" readonly>                                           	
                                            </div>            
                                        </div>
                                         <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>물품상태</label>
                                            </div>
                                            <div class="col-xs-12">
                                            	<input type="text" id="rstate" class="form-control" readonly>
                                            </div>
                                        </div>                                        
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:90px;">
                                            	<label>대여접수일자</label>
                                            </div>
                                            <div class="col-xs-12">
                                                <input type="date" id="rdate" class="form-control" >
                                            </div>
                                        </div>
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:90px;">
                                            	<label>대여실행일자</label>
                                            </div>
                                            <div class="col-xs-12">
                                                <input type="date" id="rstartdate" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:90px;">
                                            	<label>반납접수일자</label>
                                            </div>
                                            <div class="col-xs-12">
												<input type="date" id="rreturndate" class="form-control">                                
                                            </div>
                                        </div>
                                        <div class="row" id="daydiv1">
                                        	<div class="col-xs-12" style="width:90px;" >
                                            	<label>반납실행일자</label>
                                            </div>
                                            <div class="col-xs-12">
                                                <input type="date" id="rlastdate" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row" id="daydiv2">
                                        	<div class="col-xs-12" style="width:90px;">
                                            	<label>예약일자</label>
                                            </div>
                                            <div class="col-xs-12">
                                                <input type="date" id="rbookdate" class="form-control">
                                            </div>
                                        </div>                                      
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:90px;">
                                            	<label>대여자아이디</label>
                                            	<button id="idCheck" onclick="dupIdCheck()">유효성검사</button>
                                            </div>
                                            <div class="col-xs-12" style="width:170px;">
                                                <input type="text" id="mid1" placeholder="대여자아이디" class="form-control">
                                            </div>                                           
                                        </div>                                      
                                        <div class="row" style="display:flex;align-items:center;justify-content:center;">
                                            <div class="col-xs-12">
                                                <button type="button" id="saveBtn" name="saveBtn" onclick="insertClick();" style="display:none;" class="btn btn-finish btn-primary">저장</button>
												<button type="button" id="editSaveBtn" name="editSaveBtn" onclick="updateReturnDate();" style="display:none;" class="btn btn-finish btn-primary">연장</button>
												<button type="button" id="returnBtn" name="returnBtn" onclick="returnClick();" style="display:none;" class="btn btn-finish btn-primary">반납</button>
												<button type="reset" id="cancleBtn" name="cancleBtn" class="btn btn-finish btn-primary">취소</button>
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
                                	<input type="radio" id="dayselect" name="filter">일별조회                                	                                  
                                </li>
                                <li>
                                	<input type="date" id="dayselectinput" class="form-control">  
                                </li>
                                <li class="active">
                                    <input type="radio" id="monthselect" name="filter">월별조회	                                    
                                </li>
                                <li>
                                	<script type="text/javascript">	                                    	
	                                    	for(var i = 1; i < 13; i++){
	                                    		if(i == 1)
	                                    			document.write("<select id='monthselectinput' class='form-control'>");
	                                    		document.write("<option>" + i + "<option>");
	                                    		if(i == 12)
	                                    			document.write("</select>");
	                                    	}
	                            	</script>
                                </li>
                                <li>
                                	<input type="radio" id="allselect" name="filter" checked>전체조회
                                </li>                         
                            </ul>
                        </div>
                    </div>
                    
					<div class="col-md-12 clear"> 
                        <div class="col-xs-10 page-subheader sorting pl0">
                           <ul class="sort-by-list">
                           		<li class="active">
                                	<select class="btn dropdown-toggle btn-default" id="searchfilter">
										<option>전체</option>
										<option>대여번호</option>
										<option>아이디</option>
									</select>                                      
                                </li>
                                <li class="">
                                    <input style="width:550px;" class="form-control" type="search" id="searchTF" name="search">
                                </li>
                                <li class="">
                                    <button class="btn btn-finish btn-primary" type="button" id="selecthBtn" name="selecthBtn" onclick="selectBtnClick();">조회</button>
                                </li>                               
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-12 clear"> 
                    	<table class="table" id="table2">
                    		<thead>
                            <tr>
								<th width="25">선택</th>
								<th width="180">대여번호</th>
								<th width="180">물품번호</th>
								<th width="100">수량</th>
								<th width="100">금액</th>
								<th width="150">대여자 ID</th>
								<th width="150">대여접수일자</th>
								<th width="150">대여실행일자</th>
								<th width="150">반납접수일자</th>
								<th width="150">반납실행일자</th>
								<th width="150">상태</th>
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