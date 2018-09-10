<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="semi.products.model.vo.Product, java.util.ArrayList" %> 
    <%
	ArrayList<Product> localList = (ArrayList<Product>)request.getAttribute("localList");	
	/* int listCount = ((Integer)request.getAttribute("listCount")).intValue();
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue(); */
	//ArrayList<Product> localList = (ArrayList<Product>)request.getAttribute("localList");
	
	
	 
%>
<%@ include file = "../../header.jsp" %>
<script type="text/javascript">
	

</script>


  <div class="page-head"> 
            <div class="container">
                <div class="row">
                    <div class="page-head-content">
                        <h1 class="page-title">물품 목록</h1>               
                    </div>
                </div>
            </div>
        </div>
        <!-- End page header -->
 
        <div class="properties-area recent-property" style="background-color: #FFF;">
            <div class="container"> 
                <div class="row  pr0 padding-top-40 properties-page">
                    <div class="col-md-12 padding-bottom-40 large-search"> 
                        <div class="search-form wow pulse">
                            <form action="/semi/psearch" class=" form-inline">
                                <div class="col-md-12">
                                
                                    <div class="col-md-4">                                   
                                        <select id="lunchBegins" class="selectpicker" data-live-search="true" data-live-search-style="begins" title="지역"
                                        id="localSelect" name="localselect">
                                        <option value="1">종로구</option>
                                        <option value="2">중구</option>
                                        <option value="3">용산구</option>
                                        <option value="4">성동구</option>
                                        <option value="5">광진구</option>
                                        <option value="6">동대문구</option>
                                        <option value="7">중랑구</option>
                                        <option value="8">성북구</option>
                                        <option value="9">강북구</option>
                                        <option value="10">도봉구</option>
                                        <option value="11">노원구</option>
                                        <option value="12">은평구</option>
                                        <option value="13">서대문구</option>
                                        <option value="14">마포구</option>
                                        <option value="15">양천구</option>
                                        <option value="16">강서구</option>
                                        <option value="17">구로구</option>
                                        <option value="18">금천구</option>
                                        <option value="19">영등포구</option>
                                        <option value="20">동작구</option>
                                        <option value="21">관악구</option>
                                        <option value="22">서초구</option>
                                        <option value="23">강남구</option>
                                        <option value="24">송파구</option>
                                        <option value="25">강동구</option>
                                        <option value="26">그 외</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4" style="width : 300px;">                                     
                                        <!-- <select id="basic" class="selectpicker show-tick form-control" title="분류">
                                           
                                        <option>절단공구 </option>
                                        <option>드릴공구</option>
                                        <option>충전공구</option>  
                                        <option>기타공구</option>
                                        <option>기타생활용품</option>    

                                        </select> -->
                                    </div>
                                     <div class="col-md-4">
                                        <input type="text" class="form-control" placeholder="Key word" name="keyword">
                                    </div>
                                      <button class="btn search-btn" type="submit"><i class="fa fa-search"></i></button>    
                                </div>
                                
                            </form>
                        </div>
                    </div>

                    <div class="col-md-12  clear"> 
                        <div class="col-xs-10 page-subheader sorting pl0">
                            <ul class="sort-by-list">
                                <li class="active">
                                    <a href="javascript:void(0);" class="order_by_date" data-orderby="property_date" data-order="ASC">
                                        Property Date <i class="fa fa-sort-amount-asc"></i>					
                                    </a>
                                </li>
                                <li class="">
                                    <a href="javascript:void(0);" class="order_by_price" data-orderby="property_price" data-order="DESC">
                                        Property Price <i class="fa fa-sort-numeric-desc"></i>						
                                    </a>
                                </li>
                            </ul><!--/ .sort-by-list-->

                            <div class="items-per-page">
                                <label for="items_per_page"><b>Property per page :</b></label>
                                <div class="sel">
                                    <select id="items_per_page" name="per_page">
                                        <option value="3">3</option>
                                        <option value="6">6</option>
                                        <option value="9">9</option>
                                        <option selected="selected" value="12">8</option>
                                        <option value="15">15</option>
                                        <option value="30">30</option>
                                        <option value="45">45</option>
                                        <option value="60">60</option>
                                    </select>
                                </div><!--/ .sel-->
                            </div><!--/ .items-per-page-->
                        </div>

                        <div class="col-xs-2 layout-switcher">
                            <a class="layout-list" href="javascript:void(0);"> <i class="fa fa-th-list"></i>  </a>
                            <a class="layout-grid active" href="javascript:void(0);"> <i class="fa fa-th"></i> </a>                          
                        </div><!--/ .layout-switcher-->
                    </div>

                    <div class="col-md-12 clear "> 
                        <div id="list-type" class="proerty-th">
                        <% for (Product p : localList) { %>
                        	
                            <div class="col-sm-6 col-md-3 p0">
                                <div class="box-two proerty-item">
                                    <div class="item-thumb">
                                        <a href="/semi/pdetail.bd?pname=<%= p.getP_name() %>"><img src="<%=p.getP_main_image().split(",")[0]%>"></a>
                                    </div>

                                    <div class="item-entry overflow">
                                        <h5><a><%=p.getP_name() %></a></h5>
                                        <div class="dot-hr"></div>                                   
                                        <span class="pull-left"><b> 가격 :</b></span>
                                        <span class="proerty-price pull-right"><%=p.getP_price()%>원</span>
                                        <p style="display: none;">Suspendisse ultricies Suspendisse ultricies Nulla quis dapibus nisl. Suspendisse ultricies commodo arcu nec pretium ...</p>
                                       <div class="property-icon">
                                       
                                          
                                           
                                        </div>
                                    </div>


                                </div>
                            </div> 
					
                	<%} %>
                            
                        </div>
                    </div>



					<%-- <div class="col-md-12 clear"> 
                        <div class="pull-right">
                            <div class="pagination">
                                <ul>
                                	<% if (currentPage <= 1 || currentPage >= maxPage ){ %>
										<li><a href="/semi/plist?page=1">Prev</a><li>
                                    <% } %> 
                                    
                                     <% if ((currentPage - 8) < startPage && (currentPage - 8) > 1) { %>
                                    	<li><a href="/semi/plist?page=<%=startPage - 8%>">Prev</a></li>
                                    <% } else { %>
										
									<% } %> 
									
									
									<% for (int p = startPage; p <= endPage; p++) {
									if (p == currentPage) { %>
									<li><a href="/semi/plist?page=<%=p%>"><%=p%></a></li>
									<% } else { %>
									<li><a href="/semi/plist?page=<%=p%>"><%=p%></a></li>
									<%
										}
										}
									%>
									
									
									
									<% if ((currentPage + 8) > endPage && (currentPage + 8) < maxPage) { %>
										<li><a href="/semi/plist?page=<%=endPage + 8%>">Next</a></li>
										
										<% } %>
								
										<% if (currentPage >= maxPage) { %>
										
										
									<% } %>
									
									
									<% if (currentPage >= maxPage) { %>
										
									<% } else { %>
									<a href="/semi/plist?page=<%=maxPage%>">Next</a>
									<% } %>
 
                                </ul>
                            </div>
                        </div>                
                    </div> --%>
                   
                
                </div>                
            </div>
        </div>
<%@ include file="../../footer.jsp" %>