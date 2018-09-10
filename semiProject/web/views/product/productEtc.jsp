<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="./productsError.jsp"%>
<%@ page import="semi.products.model.vo.Product, java.util.ArrayList"%>
<%
	ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("list");	
	/* int listCount = ((Integer)request.getAttribute("listCount")).intValue();
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue(); */
%>
<%@ include file="../../header.jsp"%>

<!-- property area -->
<div class="properties-area recent-property"
	style="background-color: #FFF;">
	<div class="container">
		<div class="row  pr0 padding-top-40 properties-page">
			<div class="col-md-12 padding-bottom-40 large-search">
				<div class="search-form wow pulse" style="width:195%;">
					<form action="/semi/psearch" class=" form-inline" method="post" style="margin-left : 20%;"> 

                                <div class="form-group">                                   
                                    <select id="lunchBegins" name="localselect" class="selectpicker" data-live-search="true" data-live-search-style="begins" title="지역">

                                        <option value="종로구">종로구</option>
                                        <option value="중구">중구</option>
                                        <option value="용산구">용산구</option>
                                        <option value="성동구">성동구</option>
                                        <option value="광진구">광진구</option>
                                        <option value="동대문구">동대문구</option>
                                        <option value="중랑구">중랑구</option>
                                        <option value="성북구">성북구</option>
                                        <option value="강북구">강북구</option>
                                        <option value="도봉구">도봉구</option>
                                        <option value="노원구">노원구</option>
                                        <option value="은평구">은평구</option>
                                        <option value="서대문구">서대문구</option>
                                        <option value="마포구">마포구</option>
                                        <option value="양천구">양천구</option>
                                        <option value="강서구">강서구</option>
                                        <option value="구로구">구로구</option>
                                        <option value="금천구">금천구</option>
                                        <option value="영등포구">영등포구</option>
                                        <option value="동작구">동작구</option>
                                        <option value="관악구">관악구</option>
                                        <option value="서초구">서초구</option>
                                        <option value="강남구">강남구</option>
                                        <option value="송파구">송파구</option>
                                        <option value="강동구">강동구</option>

                                    </select>
                                </div>
                                <!-- <div class="form-group">                                     
                                    <select id="basic" class="selectpicker show-tick form-control">
                                        <option>분류</option>
                                        <option>절단공구 </option>
                                        <option>드릴공구</option>
                                        <option>충전공구</option>  
                                        <option>기타공구</option>
                                        <option>기타생활용품</option>    
                                        

                                    </select>
                                </div> -->
                                 <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Key word" name="keyword" id="keyword">
                                </div>
                                <button class="btn search-btn" type="submit"><i class="fa fa-search"></i></button>
                        
                            </form>
				</div>
			</div>
<br><br><br><br>
			 <div class="col-md-12  clear">
				

				<div class="col-xs-2 layout-switcher">
					<a class="layout-list" href="javascript:void(0);"> <i
						class="fa fa-th-list"></i>
					</a> <a class="layout-grid active" href="javascript:void(0);"> <i
						class="fa fa-th"></i>
					</a>
				</div>
				
			</div> 

			<div class="col-md-12 clear ">
				<div id="list-type" class="proerty-th">
					<% for (Product p : list) { %>
					<div class="col-sm-6 col-md-3 p0" style="margin-right: 30px;">
						<div class="box-two proerty-item">
							<div class="item-thumb">
								<a
									href="/semi/pdetail.bd?pname=<%=p.getP_name()%>"><img
									src="<%=p.getP_main_image().split(",")[0]%>" style="max-height: 248px; max-width: 304px;"></a>
							</div>

							<div class="item-entry overflow">
								<h5>
									<a href="/semi/pdetail.bd?pname=<%=p.getP_name()%>"><%=p.getP_name() %></a>
								</h5>
								<div class="dot-hr"></div>
								<span class="pull-left"><b> 가격 :</b></span> <span
									class="proerty-price pull-right"><%=p.getP_price()%>원</span>
								<p style="display: none;"></p>
								
							</div>

 
						</div>
					</div>
					<% } %>


				</div>
			</div>


			
		</div>
	</div>
</div>


<%@ include file="../../footer.jsp"%>