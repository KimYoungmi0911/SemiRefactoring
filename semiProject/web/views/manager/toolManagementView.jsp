<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>

<script type="text/javascript">
	var toolInfo = new Object();
	var gulo = "<option>지역선택</option><option>강남구</option><option>강동구</option><option>강북구</option><option>강서구</option>"
			+ "<option>관악구</option><option>광진구</option><option>구로구</option><option>금천구</option><option>노원구</option>"
			+ "<option>도봉구</option><option>동대문구</option><option>동작구</option><option>마포구</option><option>서대문구</option>"
			+ "<option>서초구</option><option>성동구</option><option>성북구</option><option>송파구</option><option>양천구</option>"
			+ "<option>영등포구</option><option>용산구</option><option>은평구</option><option>종로구</option><option>중구</option><option>중랑구</option>"
	
	function paging(page) {
		var currentPage;
		var maxPage;
		var startPage;
		var endPage;
		
		$.ajax({
			url : "/semi/propage",
			type : "get",
			data : {"page" : page},
			dataType : "json",
			success : function(data) {
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);
				
				currentPage = json.currentPage;
				maxPage = json.maxPage;
				startPage = json.startPage;
				endPage = json.endPage;
				
				var values = "";
				for(var i in json.list){
					values += "<tr><td><input type='radio' onclick='radioCheck(" + i + ")' name='choice' id='choice" + i + "'></td>"
					+ "<td id='itemtd" + i + "'>" + decodeURIComponent(json.list[i].item) + "</td>"
					+ "<td id='nametd" + i + "'>" + decodeURIComponent(json.list[i].name) + "</td>"
					+ "<td id='pricetd" + i + "'>" + json.list[i].price + "</td>"
					+ "<td id='counttd" + i + "'>" + json.list[i].count + "</td>"
					+ "<td id='localtd" + i + "'>" + decodeURIComponent(json.list[i].local).replace("+", " ").replace("+", " ") + "</td>"
					+ "<td id='statetd" + i + "'>" + decodeURIComponent(json.list[i].state) + "</td></tr>"
					+ "<input type='hidden' id='main" + i + "' name='main" + i + "' value='" + decodeURIComponent(json.list[i].main) + "'>"
					+ "<input type='hidden' id='detail" + i + "' name='detail" + i + "' value='" + decodeURIComponent(json.list[i].detail) + "'>"
					+ "<input type='hidden' id='gu" + i + "' name='gu" + i + "' value='" + decodeURIComponent(json.list[i].gu) + "'>";
					
				} //for
				$("#t1").html(values);
				
				$("#domain").html("");
				if(currentPage <= 1) {	
				} else {
					$("#domain").append("<li><a href='#' onclick='paging(1)'><<</a></li>");
				}
				
				if(currentPage == 1) {
				} else {
					$("#domain").append("<li><a href='#' onclick='paging(" + currentPage + " - 1)'><</a></li>");
				}
				
				for (var p = startPage; p <= endPage; p++) { 
					if (p == currentPage) {
						$("#domain").append("<li><a href='#'><font color='red'>" + p + "</font></a></li>");
					} else {
						$("#domain").append("<li><a href='#' onclick='paging(" + p + ")'>" + p + "</a></li>");
					}
				}
				
				if (currentPage == maxPage) {
				} else {
					$("#domain").append("<li><a href='#' onclick='paging(" + currentPage + " + 1)'> ></a></li>");
				}

				if (currentPage >= maxPage) {
				} else {
					$("#domain").append("<li><a href='#' onclick='paging(" + maxPage + ")'> >> </a></li>");
				}
				
			}, error : function(jqXHR, textstatus, errorThrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
			}
		});
	}
	
	function selectBtnClick(page) {
		var filter = $("#filter").val();
		var searchTF = $("#searchTF").val();
		
		var currentPage;
		var maxPage;
		var startPage;
		var endPage;
		
		var datainput;
		
		if(searchTF == "") {
			datainput = {"filter" : filter, "page" : page};
		} else {
			datainput = {"filter" : filter, "searchTF" : searchTF, "page" : page};
		}
		
		$.ajax({
			url : "/semi/mproselect",
			type : "post",
			data : datainput,
			dataType : "json",
			success : function(data){
				
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);
				
				currentPage = json.currentPage;
				maxPage = json.maxPage;
				startPage = json.startPage;
				endPage = json.endPage;
				
				var values = "";
				for(var i in json.list){
					values += "<tr><td><input type='radio' onclick='radioCheck(" + i + ")' class='radiochoice' name='choice' id='choice" + i + "'></td>"
					+ "<td id='itemtd" + i + "'>" + decodeURIComponent(json.list[i].item) + "</td>"
					+ "<td id='nametd" + i + "'>" + decodeURIComponent(json.list[i].name) + "</td>"
					+ "<td id='pricetd" + i + "'>" + json.list[i].price + "</td>"
					+ "<td id='counttd" + i + "'>" + json.list[i].count + "</td>"
					+ "<td id='localtd" + i + "'>" + decodeURIComponent(json.list[i].local).replace("+", " ").replace("+", " ") + "</td>"
					+ "<td id='statetd" + i + "'>" + decodeURIComponent(json.list[i].state) + "</td></tr>"
					+ "<input type='hidden' id='main" + i + "' name='main" + i + "' value='" + decodeURIComponent(json.list[i].main) + "'>"
					+ "<input type='hidden' id='detail" + i + "' name='detail" + i + "' value='" + decodeURIComponent(json.list[i].detail) + "'>"
					+ "<input type='hidden' id='detail" + i + "' name='detail" + i + "' value='" + decodeURIComponent(json.list[i].detail) + "'>";
					
				} //for
				$("#t1").html(values);
				
				$("#domain").html("");
				if(currentPage <= 1) {	
				} else {
					$("#domain").append("<li><a href='#' onclick='selectBtnClick(1)'><<</a></li>");
				}
				
				if(currentPage == 1) {
				} else {
					$("#domain").append("<li><a href='#' onclick='selectBtnClick(" + currentPage + " - 1)'><</a></li>");
				}
				
				for (var p = startPage; p <= endPage; p++) { 
					if (p == currentPage) {
						$("#domain").append("<li><a href='#'><font color='red'>" + p + "</font></a></li>");
					} else {
						$("#domain").append("<li><a href='#' onclick='selectBtnClick(" + p + ")'>" + p + "</a></li>");
					}
				}
				
				if (currentPage == maxPage) {
				} else {
					$("#domain").append("<li><a href='#' onclick='selectBtnClick(" + currentPage + " + 1)'> ></a></li>");
				}

				if (currentPage >= maxPage) {
				} else {
					$("#domain").append("<li><a href='#' onclick='selectBtnClick(" + maxPage + ")'> >> </a></li>");
				}
				
			}, error : function(jqXHR, textstatus, errorThrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
			}
		});
	}
	
	function localSelectEvent() {
		var local1 = $("#local1").val();
		
		$.ajax({
			url : "/semi/localselect",
			type : "post",
			data : {"local1" : local1},
			dataType : "json",
			success : function(data){
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);
				
				var values = "";
				for(var i in json.list){
					values += "<option>" + decodeURIComponent(json.list[i].local).replace("+", " ").replace("+", " ") + "</option>"
				}
				
				$("#local2").html(values);
			}, error : function(jqXHR, textstatus, errorThrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
			}
		});
	}
	
	function radioCheck(num){	
		
		toolInfo.tname = $("#nametd" + num).text();
		toolInfo.tprice = $("#pricetd" + num).text();
		toolInfo.tcount = $("#counttd" + num).text();
		toolInfo.tlocal = $("#localtd" + num).text();
		toolInfo.titem = $("#itemtd" + num).text();
		toolInfo.tstate = $("#statetd" + num).text();

		$("#tname").val(toolInfo.tname);
		$("#tprice").val(toolInfo.tprice);
		$("#tcount").val(toolInfo.tcount);
		
		var opSelect = "";
		if(toolInfo.titem == "절단공구") {
			opSelect = "<option selected>절단공구</option><option>충전공구</option><option>드릴공구</option><option>기타공구</option><option>기타생활용품</option>";
		} else if(toolInfo.titem == "충전공구") {
			opSelect = "<option selected>절단공구</option><option selected>충전공구</option><option>드릴공구</option><option>기타공구</option><option>기타생활용품</option>";
		} else if(toolInfo.titem == "드릴공구") {
			opSelect = "<option selected>절단공구</option><option>충전공구</option><option selected>드릴공구</option><option>기타공구</option><option>기타생활용품</option>";
		} else if(toolInfo.titem == "기타공구") {
			opSelect = "<option selected>절단공구</option><option>충전공구</option><option>드릴공구</option><option selected>기타공구</option><option>기타생활용품</option>";
		} else {
			opSelect = "<option selected>절단공구</option><option>충전공구</option><option>드릴공구</option><option>기타공구</option><option selected>기타생활용품</option>";
		}
		$("#titem").html(opSelect);
		
		$("#maindiv").html($("#main" + num).val());
		$("#detaildiv").html($("#detail" + num).val());
		
		$("#local1").html("<option>" + $("#gu" + num).val() + "</option>");
		$("#local2").html("<option>" + $("#localtd" + num).text() + "</option>");
	}
	
	//등록 radio
	function enrollChecked(){
		
		$("#enrollBtn").attr("style", "display:inline");
		$("#editBtn").attr("style", "display:none");
		$("#deleteBtn").attr("style", "display:none");
		
		$("#table1 input").attr("readonly", false);
		$("#table1 input").val("");
		
		$("#enrollBtn").attr("value", "저장");
		
		$("#maindiv").html("<input type='file' name='mainupfile' id='mainupfile'>");
		$("#detaildiv").html("<input type='file' name='detailupfile' id='detailupfile'>");
		$("#local1").html(gulo);
		$("#local2").html("<option>동사무소선택</option>");
		
		return false;
	}
	
	//수정 radio
	function editChecked(){
		$("#enrollBtn").attr("style", "display:none");
		$("#editBtn").attr("style", "display:inline");
		$("#deleteBtn").attr("style", "display:none");
		
		$("#table1 input").attr("readonly", false);
		
		$("#tname").attr("readonly", true);
		$("#editBtn").attr("value", "수정");
		
		$("#mainupfile").attr("style", "display:none");
		$("#detailupfile").attr("style", "display:none");
		$("#local1").html(gulo);
		$("#local2").html("<option>동사무소선택</option>");
		
		return false;
	}
	
	//삭제 radio
	function deleteChecked(){
		$("#enrollBtn").attr("style", "display:none");
		$("#editBtn").attr("style", "display:none");
		$("#deleteBtn").attr("style", "display:inline");
		
		$("#table1 input").attr("readonly", true);
		$("#deleteBtn").attr("value", "삭제");
		
		$("#mainupfile").attr("style", "display:none");
		$("#detailupfile").attr("style", "display:none");
		$("#local1").html(gulo);
		$("#local2").html("<option>동사무소선택</option>");
		
		return false;
	}
	
	/* function enrollClick() {		
	$.ajax({
			url : "/semi/pmenroll",
			contentType : false,
			enctype : "multipart/form-data",
			success : function(data) {
				if (data >= 1) {
					alert("물품 등록 완료");
				} else {
					alert("물품 등록 실패");
				}
			}, //success
			error : function(jqXHR, textstatus, errorThrown) {
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
			} //error
		});//ajax
		
		return false;
	} */
	
	function editClick() {
		var name = $("#tname").val();
		var price = $("#tprice").val();
		var count = $("#tcount").val();
		var local = toolInfo.tlocal;
		
		$.ajax({
			url : "/semi/pmupdate",
			type : "post",
			data : {"price" : price, "count" : count, "name" : name, "local" : local},
			success : function(data) {
				if(data >= 1) {
					alert("수정 완료");
				} else {
					alert("수정 실패");
				}
				
				paging(1);
				enrollChecked();
			}, //success
			error : function(jqXHR, textstatus, errorThrown) {console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
			} //error
		});
	}
	
	function deleteClick() {
		var name = $("#tname").val();
		var local = toolInfo.tlocal;
		
		$.ajax({
			url : "/semi/pmdelete",
			type : "post",
			data : {"name" : name, "local" : local},
			success : function(data) {
				if(data >= 1) {
					alert("삭제 완료");
				} else {
					alert("삭제 실패");
				}
				
				paging(1);
				enrollChecked();
			}, //success
			error : function(jqXHR, textstatus, errorThrown) {console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
			} //error
		});
	}
</script>

<div class="properties-area recent-property" style="background-color: #FFF;">
	<div class="container" style="width:1400px;height:1400px;">  
                <div class="row">              
                <div class="col-md-3 p0 padding-top-40" style="width:20%;height:800; margin-right: 10px">
                    <div class="blog-asside-right pr0">
                        <div class="panel panel-default sidebar-menu wow fadeInRight animated animated" style="visibility: visible; animation-name: fadeInRight;">
                            <div class="panel-heading">
                                <h3 class="panel-title">재고관리</h3>
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
                                        <form action="/semi/pmenroll" method="post" enctype = "multipart/form-data">
                                        <div class="row">
                                        	<div class="col-xs-12"> 
                                            </div>
                                            <div class="col-xs-12">
                                            	<select onChange="localSelectEvent();" class="btn dropdown-toggle btn-default" class="lo" id="local1" name="local1" style="padding: 10px 5px;">
													<option>지역선택</option>
													<option>강남구</option>
													<option>강동구</option>
													<option>강북구</option>
													<option>강서구</option>
													<option>관악구</option>
													<option>광진구</option>
													<option>구로구</option>
													<option>금천구</option>
													<option>노원구</option>
													<option>도봉구</option>
													<option>동대문구</option>
													<option>동작구</option>
													<option>마포구</option>
													<option>서대문구</option>
													<option>서초구</option>
													<option>성동구</option>
													<option>성북구</option>
													<option>송파구</option>
													<option>양천구</option>
													<option>영등포구</option>
													<option>용산구</option>
													<option>은평구</option>
													<option>종로구</option>
													<option>중구</option>
													<option>중랑구</option>
												</select> 
												<select class="btn dropdown-toggle btn-default" id="local2" name="local2" style="padding: 10px 10px;">
													<option>동사무소선택</option>
												</select> 
                                            </div>
                                        </div>
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>물품품목</label>
                                            </div>
                                            <div class="col-xs-12">
                                                <select class="btn dropdown-toggle btn-default" id="titem" name="titem">
													<option>절단공구</option>
													<option>충전공구</option>
													<option>드릴공구</option>
													<option>기타공구</option>
													<option>기타생활용품</option>
												</select> 
                                            </div>                    
                                        </div>
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>상품명</label>
                                            </div>
                                            <div class="col-xs-12">
                                            	<input type="text" id="tname" name="tname" placeholder="상품명" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>상품가격</label>
                                            </div>
                                            <div class="col-xs-12">
                                                <input type="text" id="tprice" name="tprice" placeholder="상품가격" class="form-control">
                                            </div>                    
                                            <div id="idchkBtn" class="col-xs-12" style="display:none;">
                                            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            	<button type="button" id="idcheck" name="idcheck" class="btn btn-finish btn-primary" onclick="dupIdCheck();">중복확인</button>
                                            </div>
                                        </div>
                                        <div class="row">
                                        	<div class="col-xs-12" style="width:53px;">
                                            	<label>상품재고</label>
                                            </div>
                                            <div class="col-xs-12">
                                                <input type="text" id="tcount" name="tcount" placeholder="상품재고" class="form-control">
                                            </div>
                                        </div>
                                        
                                        <div class="row">
                                        	<div class="col-xs-12">
                                            	<label>메인 이미지</label>
                                            </div>
                                            <div class="col-xs-12" name="maindiv" id="maindiv">
                                                <input type="file" name="mainupfile" id="mainupfile">
                                            </div>
                                        </div>
                                        <div class="row">
                                        	<div class="col-xs-12">
                                            	<label>상세 이미지</label>
                                            </div>
                                            <div class="col-xs-12" name="detaildiv" id="detaildiv">
                                                <input type="file" name="detailupfile" id="detailupfile">
                                            </div>
                                        </div>
                                       
                                        <div class="row" style="display:flex;align-items:center;justify-content:center;">
                                            <div class="col-xs-12">
                                               <input class="btn btn-finish btn-primary" type="submit" id="enrollBtn" name="enrollBtn" style="display:none;" value="저장">
												<input class="btn btn-finish btn-primary" type="button" id="editBtn" name="editBtn" onclick="editClick();" style="display:none;" value="수정">
												<input class="btn btn-finish btn-primary" type="button" id="deleteBtn" name="deleteBtn" onclick="deleteClick();" style="display:none;" value="삭제">
												<button class="btn btn-finish btn-primary" type="reset" id="cancleBtn" name="cancleBtn">취소</button>
                                            </div>
                                        </div>
                                         </form>
                                    </fieldset>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-9  pr0 padding-top-40 properties-page" style="width:75%;height:1000px;">
                    <div class="col-md-12 clear"> 
                        <div class="col-xs-10 page-subheader sorting pl0">
                           <ul class="sort-by-list">
                                <li class="active">
                                	<select class="btn dropdown-toggle btn-default" id="filter" name="filter">
		                              <!-- <select class="selectpicker" id="filter" name="filter"> -->
										<option>선택</option>
										<option>지역</option>	
										<option>동사무소</option>		
										<option>물품품목</option>	
										<option>대여상태</option>	
									</select>                                      
                                </li>
                                <li class="">
                                    <input style="width:400px;" class="form-control" type="search" size="80px" id="searchTF" name="searchTF">
                                </li>
                                <li class="">
                                    <input type="button" onclick="selectBtnClick(1);" value="조회" style="color: #fff; background-color: #fdc600; border-color: fdc600; border-radius: 1px; padding: 10px 20px; font-weight: 600; margin-right: 5px; margin-top: 10px; margin-bottom: 10px;">
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
								<th width="5%">선택</th>
								<th width="15%">물품품목</th>
								<th width="25%">상품명</th>
								<th width="10%">상품가격</th>
								<th width="10%">상품수량</th>
								<th width="20%">보유지역</th>
								<th width="10%">대여상태</th>
							</tr>
							</thead>
							<tbody id="t1">
							</tbody>
						</table>
                    </div>
                    <div class="col-md-12" style="min-width: 200px;"> 
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