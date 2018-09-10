<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	page import="semi.products.model.vo.Product, java.util.ArrayList" %>
<%
	ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("list");
	System.out.print(list.toString());
	//Product p = new Product();
	/* for(int i = 0; i < list.size(); i++){
		list.get(i);
	} */
		
	
	//String p_main_image = (String)request.getAttribute("p_main_image");
	//String p_name = (String)request.getAttribute("p_name");
	//int p_price = Integer.parseInt((String)request.getAttribute("p_price"));
	//String p_state = (String)request.getAttribute("p_state");
	//System.out.print("뷰 ㅂㄴ" + p_main_image + ", " + p_name + ", " + p_price);
	/* Product c1 = new Product();
	for(Product c : list){
		c = list.get(0);
		System.out.print(c);
		c1 = c;
	} */
%>

<%@ include file="../../header.jsp" %>
        
<style>
	.left-box {
	float: left;
 	width: 60%;
 	 float:left;
	}
	.right-box {
 	float: right;
	width: 40%;
	}
	
	.bottomright {
    position: absolute;
    bottom: 8px;
    right: 16px;
    font-size: 18px;
	}
</style>        
 
<script type="text/javascript"> 
$(function(){ 
	//전체선택 체크박스 클릭 
	$("#allCheck").click(function(){ 

	//만약 전체 선택 체크박스가 체크된상태일경우 
	if($("#allCheck").prop("checked")) { 
		//해당화면에 전체 checkbox들을 체크해준다
		$("input[type=checkbox]").prop("checked",true); 
		// 전체선택 체크박스가 해제된 경우 
		} else { 
			//해당화면에 모든 checkbox들의 체크를해제시킨다. 
			$("input[type=checkbox]").prop("checked",false);
		}
		});
	}); 
	</script>

<body>
<h2>장바구니</h2>
<div style="width:1250px; height:700px;">
<!-- 왼쪽 화면 -->
<div class='left-box' style="overflow-y: scroll; height:100%;">
<form  name="checkTest">
<table border="0" cellspacing="0" width="100%" >

<!-- <input type="button" value="전체선택/삭제"> &nbsp; &nbsp; &nbsp; -->
<input type="button" value="선택물품삭제" style="width: 120px; height: 30px;" class="btn btn-default"> 

<tr>
<td align="center" style="width: 20px; height: 20px;" >
<input type="checkbox" rowspan="2" colspan="0" style="width: 20px; height: 20px;" id="allCheck" ><hr>
</td></tr>
<% for(int i = 0; i < list.size(); i++){
	//p = list.get(i);
	// Product p = list.get(i);%>
<tr>
<td rowspan="3" colspan="2" align="center">
<input type="checkbox" name="listChk[]" style="width: 15px; height: 15px;">
</td>

<td rowspan="3" colspan="0" width="200">

<img src="" width="200" height="150" vaule="되라">
</td>
<th>대여 물품명</th>
<td> 
<input type="text" style="width:200px; height:30px; text-align:right; border-radius: 4px;" class="form-control" 
			<%-- value="<%= p.getP_name() %>" --%> readonly>
</td></tr>
<tr>
<th>대여 물품가격</th>
<td>
<input type="text" style="width: 200px; height: 30px; text-align: right; border-radius: 4px;" class="form-control"
			 <%-- value="<%= list.get(i).getP_price() %>" --%> readonly name=mae[]>
</td></tr>
<tr>
<th>대여 가능일</th>
<td>
<input type="text" style="width: 200px; height: 30px; text-align: right; border-radius: 4px;" class="form-control"
			 <%-- value="<%= list.get(i).getP_state() %>" --%> readonly>
</td></tr>
 <% } %> 

<!-- 두번째 정보 -->
<%-- <tr id="list_area_1">
<td rowspan="3" colspan="2" align="center">
<input type="checkbox" name="listChk[]" style="width: 15px; height: 15px;">
</td>
<td rowspan="3" colspan="0" width="200">
<img src="/semi/resources/images/main/02_1.jpg" width="200" height="110" value="<%= c1.getP_main_image() %>">
</td>
<th>대여 물품명</th>
<td> 
<input type="text" style="width:200px; height:30px; text-align:right; border-radius: 4px;" class="form-control" 
			value="<%= c1.getP_name() %>" readonly >
</td></tr>
<tr>
<th>대여 물품가격</th>
<td>
<input type="text" style="width: 200px; height: 30px; text-align: right; border-radius: 4px;" class="form-control"
			 value="<%= c1.getP_price() %>" readonly name=mae[]>
</td></tr>
<tr>
<th>대여 가능일</th>
<td>
<input type="text" style="width: 200px; height: 30px; text-align: right; border-radius: 4px;" class="form-control"
			 value="<%= c1.getP_state() %>" readonly>
</td></tr> --%>
</table>
</form><br>
</div>

<script type="text/javascript">
	function chkProduct(){
		alert("선택하신 물품 개수는 "+$("input[name='listChk[]']:checked").length + " 개 입니다.");
	}
</script>

<script type="text/javascript">
function totalSum(){ 
	var sum = 0; 
	var obj = document.getElementsByName("mae[]"); 
	for(i=0;i<obj.length;i++){ 
	sum += parseInt(obj[i].value); 
	} 
	document.getElementById("tot_mae").value = sum; 
	} 
</script>

<!-- 오른쪽화면 -->
<div class="right-box">
<table width="100%" height="100%">

<tr height="100" ><td></td></tr>
<tr><td></td></tr>
<tr><th style="font-size:20px">선택 물품 갯수 : </th><td>
<input type="button" style="width: 150px; height: 35px;  border-radius: 4px; " onclick="chkProduct();"
		value="물품 갯수 확인" class="btn btn-default">
</td></tr>

<tr height="100"><td></td></tr>
<tr><th style="font-size:20px">선택 물품 총 가격 : </th><td>
<!-- <input type="button" style="width: 150px; height: 35px; border-radius: 4px;" onClick="itemSum(this.form);"
		value="물품 가격 확인" class="btn btn-default"> -->
<input type="text" style="width: 150px; height: 35px; border-radius: 4px; text-align: right;"
		  name=tot_mae>
<input type='button' value='click' onClick='totalSum();'>
</td></tr>
<tr height="300"><td></td></tr>
<tr><td colspan="4"></td></tr>

<!-- <tr>
<td></td>
<td colspan="0" align="center">
	<input type="button" value="결제하기" style="width: 100px; height: 30px;" onclick=""> &nbsp; &nbsp;
	<input type="reset" value="취 소" onclick="location.href='/gonggu/index.jsp'" style="width: 100px; height: 30px; ">
</td></tr> -->
<tr>
<td colspan="3" align="center">
<!-- <button style="position:absolute; right:0px; bottom:0px;">결제하기</button> -->
<button style="width: 150px; height: 40px; border-radius: 4px;" 
		class="btn btn-default" >결제하기</button> &nbsp; &nbsp;
<button style="width: 150px; height: 40px; border-radius: 4px;" onclick="location.href='/semi/index.jsp'" 
		class="btn btn-default">취소</button>
</td>
</tr>
</table>
<br><br>
</div>
<br><br>
</body>
</div>
<%@ include file="../../footer.jsp" %>