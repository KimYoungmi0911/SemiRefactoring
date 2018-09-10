<%@page import="semi.rentalList.model.vo.RentalList"%>
<%@page import="semi.rental.model.vo.Rental"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>

<%
	String start = (String)request.getAttribute("startday");
	String end = (String) request.getAttribute("endday");
	RentalList list = (RentalList) request.getAttribute("rlist");
	String imagecru = (String) request.getAttribute("imagee");
	String pName = (String) request.getAttribute("pName");
	String pricesum = (String) request.getAttribute("pricesum");
	String payselect = (String) request.getAttribute("payselect");
%>

<script>
	function gomain() {
		location.href="/semi/index.jsp";
	}
</script>

<div>
	<div style="width: 70%; margin: 30px auto 80px auto; display: block;">
	<ul style="padding: 0px; margin: 0px;">
			<li style="list-style: none; float: left;">
				<span style="display: block; font-size: 22px; font-weight: 700; padding-right: 25px; margin-top: 8px;">장바구니</span>
				<span style="display: block; font-size: 9px; font-weight: 700;">SHOPPING CART</span></li>
			<li style="list-style: none; float: left; margin-right: 20px;">
				<span style="font-weight: 700; font-size: 33px;"> > </span>
			</li>
			<li style="list-style: none; float: left;">
				<span style="display: block; font-size: 22px; font-weight: 700; padding-right: 25px; margin-top: 8px;">주문/결제</span>
				<span style="display: block; font-size: 9px; font-weight: 700;">ORDER SHEET</span></li>
			<li style="list-style: none; float: left; margin-right: 20px;">
				<span style="font-weight: 700; font-size: 33px;"> > </span>
			</li>
			<li style="list-style: none; float: left;">
				<span style="font-size: 30px; display: block; font-weight: 700; padding-right: 25px; margin-top: 8px; color: rgb(253, 198, 0);">주문완료</span>
				<span style="display: block; font-size: 9px; font-weight: 700; color: rgb(253, 198, 0);">COMPLETION</span>
			</li>
		</ul>
		
		<div>
			<h4 style="width: 20%; font-size: 17px; color: #333; margin: 40px 0 5px 0;">주문상품 확인</h4>
		</div>
		
		<table style="width: 100%;">
			<tr>
				<th style="width: 60%; height: 42px; border-bottom: 1px solid rgb(221, 221, 221); border-top: 2px solid rgb(221, 221, 221); margin: 0; text-align: center;">상품/옵션정보</th>
				<th style="width: 40%; height: 42px; border-bottom: 1px solid rgb(221, 221, 221); border-top: 2px solid rgb(221, 221, 221); border-left: 1px solid rgb(221, 221, 221); text-align: center;">대여기간</th>
			</tr>
			
			<tr>
				<td style="border-bottom: 1px solid #dcdcdc;  margin: 0px; padding: 10px;">
				<figure style="margin: 0px;">
					<div style="width: 125px; float: left; margin: 0px;"><a href="/semi/pdetail.bd?pname=<%=pName%>"><img src="<%= imagecru %>"></a></div>
					<figcaption style="float: left; margin-left: 15px; width: 70%; margin: 0px;">
						<dl style="margin: 0px;">
							<dt style="line-height: 44px; border-top: 1px solid #e6e6e6; border-bottom: 1px solid #e6e6e6; padding: 0 10px; color: #333; font-size: 16px; margin: 0px; padding: 0px; background: #f8f8f8;">
								<a href="/semi/pdetail.bd?pname=<%=pName%>"><%=pName%></a>
							</dt>
						</dl>
					</figcaption>
				</figure>
				</td>
				<td style="border-bottom: 1px solid #dcdcdc; border-left: 1px solid #dcdcdc; font-size: 15px; text-align: center;">
					<span style="color: #3c72bc; font-weight: 700;">시작일</span> : <input type="hidden" name="startday" id="startday" value="<%= start %>"><%= start %> ~ 
					<span style="color: #3c72bc; font-weight: 700;">종료일</span> : <input type="hidden" name="endday" id="endday" value="<%= end %>"><%= end %>
				</td>
			</tr>
		</table>
		<div style="padding: 20px; border-top: 2px solid rgb(221, 221, 221); border-bottom: 2px solid rgb(221, 221, 221); ">
			<ul style="text-align: center;">
				<li style="list-style: none; display: inline-block; margin: 0 15px; font-size: 17px; color: #000;">
					<span style="display: block; text-align: left; margin: 5px 0;">(상품 * 기간)</span>
					<span style="display: block; text-align: left; margin: 5px 0;"><b style="color: #ff6e2b; font-weight: 700; font-size: 20px;"><%= pricesum %>원</b></span>
				</li>
				
				<li style="list-style: none; line-feight: 55px; verical-align: top; display: inline-block; margin: 0 15px; font-size: 17px; color: #000;">
					<span style="font-weight: 700; font-size: 50px;"> - </span>
				</li>
				
				<li style="list-style: none; display: inline-block; margin: 0 15px; font-size: 17px; color: #000;">
					<span style="display: block; text-align: left; margin: 5px 0;">포인트</span>
					<span style="display: block; text-align: left; margin: 5px 0;"><b style="color: #3c72bc; font-weight: 700; font-size: 20px;">0 P</b></span>
				</li>
				
				<li style="list-style: none; line-feight: 55px; verical-align: top; display: inline-block; margin: 0 15px; font-size: 17px; color: #000;">
					<span style="font-weight: 700; font-size: 50px;"> = </span>
				</li>
				
				<li style="list-style: none; display: inline-block; margin: 0 15px; font-size: 17px; color: #000;">
					<span style="display: block; text-align: left; margin: 5px 0;">최종결제금액</span>
					<span style="display: block; text-align: left; margin: 5px 0;"><b style="color: #ff6e2b; font-weight: 700; font-size: 20px;"><%= pricesum %>원</b></span>
				</li>
			</ul>
		</div>
	<br>
	
	<% if(payselect.equals("card")) { %>
	
	<% } else { %>
	<div style="margin: 0px; padding: 0px;">
		<h4 style="font-size: 17px; color: #333; margin: 40px 0 5 px 0;">은행 정보</h4>
	</div>s
	<table id="tablepay" name="tablepay" style="border-collapse: collapse; width: 100%;">
		<tr>
			<th style="height: 36px; border: 1px solid #dcdcdc; color: #26267f; text-align: center;">송금하실 은행</th>
			<td style="padding-right: 10px; text-align: center; border: 1px solid #dcdcdc; color: #26267f;">국민은행</td>
			<th style="height: 36px; border: 1px solid #dcdcdc; color: #26267f; text-align: center;">계좌번호</th>
			<td style="padding-right: 10px; text-align: center; border: 1px solid #dcdcdc; color: #26267f;">464646-01-282828</td>
			<th style="height: 36px; border: 1px solid #dcdcdc; color: #26267f; text-align: center;">계좌주</th>
			<td style="padding-right: 10px; text-align: center; border: 1px solid #dcdcdc; color: #26267f;">홍길동</td>
		</tr>
	</table>
	<% } %>
	
	
	<div align="center">
		<input type="button" value="메인으로" onclick="gomain();" style="color: #fff; background-color: #fdc600; border-color: fdc600; border-radius: 1px; padding: 10px 20px; font-weight: 600; margin-right: 5px; margin-top: 20px;">
	</div>
</div>
</div>
<%@ include file="../../footer.jsp" %>