<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "semi.rental.model.vo.Rental, java.util.*, semi.member.model.vo.Member, semi.products.model.vo.Product" %>
 <%

	ArrayList<Rental> rental = (ArrayList<Rental>)request.getAttribute("list");
 	ArrayList<Product> product = (ArrayList<Product>)request.getAttribute("list2");
%>     
    
<%@ include file = "../../header.jsp" %>
<script type="text/javascript">
function moveboard(){
	location.href = "/semi/myboard?mid=<%=mId %>"
}
function movehome(){
	location.href = "/semi/myinfo?mid=<%= mId %>"
}
function movepoint(){
	location.href = "/semi/mypoint?mid=<%= mId %>"
}
function moverent(){
	location.href = "/semi/myrent?mid=<%= mId %>"
}  
</script>

<div class="page-head"> 
            <div class="container">
                <div class="row">
                    <div class="page-head-content">
                        <h1 class="page-title">09 : <span class="orange strong">My board</span></h1>               
                    </div>
                </div>
            </div>
        </div>
<br>

<button class="btn btn-default btn-lg"
		style="background: white; margin-left: 670px;" onclick="movehome();">
		<a style="color: black;">Home</a>
	</button>
	<button class="btn btn-default btn-lg" style="background: white;" onclick="movepoint();">
		<a style="color: black;">포인트 내역</a>
	</button>
	<button class="btn btn-default btn-lg" style="background: white;" onclick="moverent();">
		<a style="color: black;">최근 대여목록</a>
	</button>
	<button class="btn btn-default btn-lg" style="background: white;" onclick="moveboard();">
		<a style="color: black;">나의 게시글</a>
	</button>
	
	<h1 align="center"><b><%= mId %></b> 님의 대여목록</h1><br>

<table align="center" border="1" cellspacing="0" 
width="700">
<tr> 
	<th align="center">대여번호</th><th align="center">물품번호</th>
	<th align="center">상품명</th><th align="center">대여일자</th><th align="center">수량</th><th align="center">가격</th>
</tr>
<% for(Rental r : rental){ %>
<% for(Product p : product){ %>
<tr>
<td align="center"><%= r.getrNo()  %></td> 
<td align="center"><%= r.getpNo() %></td>
<td align="center"><%= p.getP_name() %></td>
<td align="center"><%= r.getrStartDate() %></td>

<td align="center"><%= r.getpCount() %></td>


<td align="center"><%= p.getP_price() %></td>


</tr>
<%} %> 
<%} %> 
</table>




<br>
	






<%@ include file = "../../footer.jsp" %>