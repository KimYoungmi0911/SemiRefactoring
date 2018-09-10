<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String pName = (String) request.getAttribute("pName");
	String pLocal = (String) request.getAttribute("pLocal");
	String startDay = (String) request.getAttribute("startDay");
	String endDay = (String) request.getAttribute("endDay");
	String rentalDate = (String) request.getAttribute("rentalDate");
	String totalPrice = (String) request.getAttribute("totelPrice");
	String imagecru = (String) request.getAttribute("imagecru");
%>

<%@ include file="../../header.jsp" %>

<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<script type="text/javascript">
	//취소버튼 클릭시
	function backmain() {
		alert("주문이 취소되었습니다.");
		location.href="/semi/index.jsp";
	}	
	
	// 카드 또는 무통장입금 체크시
	$(function() {
		$("input[type=radio]").change(function() {
			if($(this).is(":checked")) {
				if($(this).val() == "card") {
					$("#payselect").html("<label style='font-size: 14px; font-weight: 700; color: #333; margint: 0 5px 0 10px;'>카드선택</label>");
					
					/* $("#btndiv").html("<input type='button' id='checkcard' value='카드결제하기' style='color: #fff; background-color: #fdc600; border-color: fdc600; border-radius: 1px; padding: 10px 20px; font-weight: 600; margin-right: 5px; margin-top: 10px; margin-bottom: 10px;'>"
					+ "<input type='button' onclick='backmain();' value='취소하기' style='color: #333; background-color: rgb(221, 221, 221); border-color: fdc600; border-radius: 1px; padding: 10px 20px; font-weight: 600; margin-right: 5px; margin-top: 10px; margin-bottom: 10px;'>");  */
				} else {
					$("#payselect").html("<label style='font-size: 14px; font-weight: 700; color: #333; margint: 0 5px 0 10px;'>은행선택</label>" + 
										"<select style='height: 30px; padding: 0 10px;'>" + 
										"<option>국민은행 464646-01-282828 예금주 : 홍길동</option>" +
										"</select>" + 
										"<label style='font-size: 14px; font-weight: 700; color: #333; margin: 0 5px 0 10px;'>입금자명</label>" +
										"<input type='text' name='sellname' id='sellname' style='height: 30px; width: 150px; padding: 0 10px;'>");
					
					/* $("#btndiv").html("<input type='submit' id='checkcard' value='결제하기' style='color: #fff; background-color: #fdc600; border-color: fdc600; border-radius: 1px; padding: 10px 20px; font-weight: 600; margin-right: 5px; margin-top: 10px; margin-bottom: 10px;'>"
					+ "<input type='button' onclick='backmain();' value='취소하기' style='color: #333; background-color: rgb(221, 221, 221); border-color: fdc600; border-radius: 1px; padding: 10px 20px; font-weight: 600; margin-right: 5px; margin-top: 10px; margin-bottom: 10px;'>");  */
				}
			}
		});
	});
</script>
<div>
	<form action="/semi/rlist" id="fsubmit" method="post">
	<div style="width: 70%; margin: 30px auto 80px auto; display: block;">
	<input type="hidden" value="<%= pLocal %>">
		<ul style="padding: 0px; margin: 0px;">
			<li style="list-style: none; float: left;"><span
				style="display: block; font-size: 22px; font-weight: 700; padding-right: 25px; margin-top: 8px;">장바구니</span>
				<span style="display: block; font-size: 9px; font-weight: 700;">SHOPPING
					CART</span></li>
			<li style="list-style: none; float: left; margin-right: 20px;">
				<span style="font-weight: 700; font-size: 33px;"> > </span>
			</li>
			<li style="list-style: none; float: left;"><span
				style="font-size: 30px; display: block; font-weight: 700; padding-right: 25px; margin-top: 8px; color: rgb(253, 198, 0);">주문/결제</span>
				<span
				style="display: block; font-size: 9px; font-weight: 700; color: rgb(253, 198, 0);">ORDER
					SHEET</span></li>
			<li style="list-style: none; float: left; margin-right: 20px;">
				<span style="font-weight: 700; font-size: 33px;"> > </span>
			</li>
			<li style="list-style: none; float: left;"><span
				style="display: block; font-size: 22px; font-weight: 700; padding-right: 25px; margin-top: 8px;">주문완료</span>
				<span style="display: block; font-size: 9px; font-weight: 700;">COMPLETION</span>
			</li>
		</ul>

		<!-- 결제할 상품 출력 -->		

		<!-- 서블릿에 전달해야하느 값들 -->
		<input type="hidden" name="loginId" id="loginId" value=" <%=mId%>">
		<input type="hidden" name="pname" id="pname" value="<%= pName %>">
		<input type="hidden" name="pricesum" id="pricesum" value="<%= totalPrice %>">
		<input type="hidden" name="imagee" id="imagee" value="<%=imagecru%>">
		

		<div>
			<h4 style="width: 20%; font-size: 17px; color: #333; margin: 40px 0 5px 0;">주문상품 확인</h4>
		</div>
		
		<table style="width: 100%;">
			<tr>
				<th style="width: 50%; height: 42px; border-bottom: 1px solid rgb(221, 221, 221); border-top: 2px solid rgb(221, 221, 221); margin: 0; text-align: center;">상품/옵션정보</th>
				<th style="width: 10%; height: 42px; border-bottom: 1px solid rgb(221, 221, 221); border-top: 2px solid rgb(221, 221, 221); border-left: 1px solid rgb(221, 221, 221); text-align: center;">대여가</th>
				<th style="width: 40%; height: 42px; border-bottom: 1px solid rgb(221, 221, 221); border-top: 2px solid rgb(221, 221, 221); border-left: 1px solid rgb(221, 221, 221); text-align: center;">대여일</th>
			</tr>
			<tr>
				<td style="border-bottom: 1px solid #dcdcdc;  margin: 0px; padding: 10px;">
				<figure style="margin: 0px;">
					<div style="width: 125px; float: left; margin: 0px;"><a href="/semi/pdetail.bd?pname=<%= pName %>"><img src="<%=imagecru%>"></a></div>
					<figcaption style="float: left; margin-left: 15px; width: 70%; margin: 0px;">
						<dl style="margin: 0px;">
							<dt style="line-height: 44px; border-top: 1px solid #e6e6e6; border-bottom: 1px solid #e6e6e6; padding: 0 10px; color: #333; font-size: 16px; margin: 0px; padding: 0px; background: #f8f8f8;">
								<a href="/semi/pdetail.bd?pname=<%= pName %>"><%= pName %></a>
							</dt>
						</dl>
					</figcaption>
				</figure>
				</td>
				<td style="border-bottom: 1px solid #dcdcdc; border-left: 1px solid #dcdcdc; font-size; text-align: center;"><span style="color: #ff6e2b; font-weight: 700;"><%= totalPrice %>원</span></td>
				<td style="border-bottom: 1px solid #dcdcdc; border-left: 1px solid #dcdcdc; font-size: 15px; text-align: center;">
					<span style="color: #3c72bc; font-weight: 700;">시작일</span> : <input type="hidden" name="startday" id="startday" value="<%= startDay %>"><%= startDay %> ~ 
					<span style="color: #3c72bc; font-weight: 700;">종료일</span> : <input type="hidden" name="endday" id="endday" value="<%= endDay %>"><%= endDay %>
				</td>
			</tr>
		</table>

		<!-- 결제 금액과 결제 방법 선택 -->
		<div>
			<h4 style="font-size: 17px; color: #333; margin: 40px 0 5px 0;">결제 정보</h4>
		</div>
		<table style="width: 100%;">
			<tr>
				<td style="width: 15%; height: 42px; border: 1px solid #dcdcdc; color: #26267f; text-align: center;"><label>결제금액</label></td>
				<td style="width: 35%; padding-right: 10px; text-align: right; border: 1px solid #dcdcdc; color: #26267f;"><input type="hidden" name="orderpay" id="orderpay"value="<%= totalPrice %>"><%= totalPrice %>원</td>
				<td style="width: 15%; height: 42px; border: 1px solid #dcdcdc; color: #26267f; text-align: center;"><label>총 결제금액</label></td> 
				<td style="width: 35%; padding-right: 10px; text-align: right; border: 1px solid #dcdcdc; color: #26267f;"><%= totalPrice %>원</td>
			</tr>
		</table>

		<div>
			<h4 style="font-size: 17px; color: #333; margin: 40px 0 5px 0;">결제선택</h4>
		</div>
		
		<div style="padding: 20px; border-top: 2px solid rgb(221, 221, 221); border-bottom: 2px solid rgb(221, 221, 221);">
			<table id="pay" style="width: 100%;">
				<tr>
					<td>
						<span style="display: block; margin-bottom: 10px; text-align: center;">
							<input name="payselect" type="radio" value="card"><label style="font-size: 17px; color: #333; font-weight: 700; margint: 0 20px 0 8px;">신용카드</label> 
							<input name="payselect" type="radio" value="money" checked><label style="font-size: 17px; color: #333; font-weight: 700; margint: 0 20px 0 8px;">무통장입금</label>
						</span>
						<div style="text-align: center; margin: 0px; padding: 0px;">
							<span id="payselect" style="display: inline-block; width: 95%; background: #f5f5f5; border-radius: 50px; border: 1px solid #cecece; height: 50px; padding: 10px 0 0 0;">
								<label style="font-size: 14px; font-weight: 700; color: #333; margin: 0 5px 0 10px;">은행선택</label>
								<select style="height: 30px; padding: 0 10px;">
									<option>국민은행 464646-01-282828 예금주 : 홍길동</option>
								</select> 
								<label style="font-size: 14px; font-weight: 700; color: #333; margin: 0 5px 0 10px;">입금자명</label>
								<input type="text" name="sellname" id="sellname" style="height: 30px; width: 150px; padding: 0 10px;">
							</span>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>

	<div id="btndiv" name="btndiv" style="margin-top: 10px; margin-bottom: 20px; text-align: center;">
		<input type="button" id="checkcard" value="결제하기" style="color: #fff; background-color: #fdc600; border-color: fdc600; border-radius: 1px; padding: 10px 20px; font-weight: 600; margin-right: 5px; margin-top: 10px; margin-bottom: 10px;">
		<input type="button" onclick="backmain();" value="취소하기" style="color: #333; background-color: rgb(221, 221, 221); border-color: fdc600; border-radius: 1px; padding: 10px 20px; font-weight: 600; margin-right: 5px; margin-top: 10px; margin-bottom: 10px;">
	</div>
	</form>
</div>

<!-- 카드 결제시 작동하는 API -->
<script type="text/javascript">

		$("#checkcard").click(function() {
			$("input[type=radio]").each(function() {
				if ($(this).is(":checked")) {
					if ($(this).val() == "card") {
						var IMP = window.IMP;
						IMP.init("imp10675025");

						IMP.request_pay({
							pg : "inicis",
							pay_method : "card",
							merchant_uid : "merchant_" + new Date().getTime(),
							name : $("#pname").val(),
							amount : $("#orderpay").val(),

							buyer_email : "iamport@siot.do",
							buyer_name : $("#sellname").html(),
							buyer_tel : "010-1234-5678", // session 으로 받기
							buyer_addr : "서울특별시 강남구 삼성동",
							buyer_postcode : "123-456",

						}, function(rsp) {
							console.log(rsp);
							if (rsp.success) {
								var msg = "결제가 완료되었습니다.";
								msg += "고유ID : " + rsp.imp_uid;
								msg += "상점 거래ID : " + rsp.merchant_uid;
								msg += "결제 금액 : " + rsp.paid_amount;
								msg += "카드 승인번호 : " + rsp.apply_num;
							} else {
								var msg = "결제에 실패하였습니다.";
								msg += "에러내용 : " + rsp.error_msg;
							}
							alert(msg);
							$("#fsubmit").submit();
						});
					} else {
						$("#fsubmit").submit();
					}
				}
			}); 
		});
</script>
<%@ include file="../../footer.jsp" %>