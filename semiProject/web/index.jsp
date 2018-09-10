<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="semi.products.model.vo.Product, java.util.ArrayList" %> 
<%

	ArrayList<Product> productList = (ArrayList<Product>)request.getAttribute("list");
	
	
%>

	<!-- 0911 git -->
	<!-- 0911. 2 : 반영? ?-->
	<!-- 0911 반영ㅇㅇ_3 ?? -->
	<!-- zz?? -->
	<%@ include file="header.jsp" %>
	<script type="text/javascript">
	function move(){
		location.href = "/semi/plist";
	};
	/* $(function(){
		%.ajax({
			url : "/semi/ptop9",
			type : "get",
			dataType : "json",
			success : function(data){
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);
				
				var values = "";
				for(var i in json.list){
					values += "<div class='col-sm-6 col-md-3 p0'>" +
					"<div class='box-two proerty-item'>" +
					"<div class='item-thumb'>" + 
					"<a href='/semi/pdetail.bd?pname='>"+json.list[i].pname +
					"</a>"		
				}
			}
		})
	}); */
	
	
	</script>
	
	<%@ include file="aside2.jsp" %>

        <div class="slider-area">
            <div class="slider">
                <div id="bg-slider" class="owl-carousel owl-theme">

                    <div class="item"><img src="/semi/resources/images/slider/slider1.jpg" alt="GTA V"></div>
                    <div class="item"><img src="/semi/resources/images/slider/slider2.jpg" alt="The Last of us"></div>
                    <div class="item"><img src="/semi/resources/images/slider/slider4.jpg" alt="GTA V"></div>

                </div>
            </div>
            <div class="slider-content">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12" style="margin-left : 29%;">
                        <h2>Product SEARCHING JUST GOT SO EASY</h2>
                        <p>지역 및 키워드 검색을 통해 보다 빠르게 원하는 상품을 찾을 수 있습니다.</p>
                        <div class="search-form wow pulse" data-wow-delay="0.8s">
						
                            <form action="/semi/psearch" class=" form-inline" method="post" style="margin-left : 50px;"> 

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
                </div>
            </div>
        </div>

        <!-- property area -->
        <div class="content-area home-area-1 recent-property" style="background-color: #FCFCFC; padding-bottom: 55px;">
            <div class="container">
                <div class="row">
                    <div class="col-md-10 col-md-offset-1 col-sm-12 text-center page-title" style = "margin-left : 43%">
                        <!-- /.feature title -->
                        <br>
                        <h2>제품소개</h2>
                        <p>product description</p>
                    </div>
                </div>
	
                <div class="row" style="padding-left : 9%">
                    <div class="proerty-th">
                        <div class="col-sm-6 col-md-3 p0">
                            <div class="box-two proerty-item">
                                <div class="item-thumb">
                                    <a href="/semi/ipdetail1.bd?pname=<%= "포터블그라인더" %>" >
                                    <img src="/semi/resources/images/main/01_1.jpg">
                                  
                                    </a>
                                </div>
                                <div class="item-entry overflow">
                                    <h5><a href="/semi/ipdetail1.bd?pname=<%= "포터블그라인더" %>" >포터블그라인더</a></h5> <!-- 이미지 크기 304 * 248 -->
                                    <div class="dot-hr"></div>
                                 <span class="pull-left"><b>가격 :</b></span>
                                    <span class="proerty-price pull-right"> 11000원</span>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-6 col-md-3 p0">
                            <div class="box-two proerty-item">
                                <div class="item-thumb">
                                    <a href="/semi/ipdetail1.bd?pname=<%= "폴리셔" %>" ><img src="/semi/resources/images/main/02_1.jpg"></a>
                                </div>
                                <div class="item-entry overflow">
                                    <h5><a href="/semi/ipdetail1.bd?pname=<%= "폴리셔" %>" >폴리셔 </a></h5>
                                    <div class="dot-hr"></div>
                                    <span class="pull-left"><b>가격 :</b></span>
                                    <span class="proerty-price pull-right">6000원</span>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-6 col-md-3 p0">
                            <div class="box-two proerty-item">
                                <div class="item-thumb">
                                    <a href="/semi/ipdetail1.bd?pname=<%= "핸드그라인더" %>" " ><img src="/semi/resources/images/main/03_1.jpg"></a>

                                </div>
                                <div class="item-entry overflow">
                                    <h5><a href="/semi/ipdetail1.bd?pname=<%= "핸드그라인더" %>" >핸드그라인더</a></h5>
                                    <div class="dot-hr"></div>
                                    <span class="pull-left"><b>가격 :</b></span>
                                    <span class="proerty-price pull-right">5000원</span>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-6 col-md-3 p0">
                            <div class="box-two proerty-item">
                                <div class="item-thumb">
                                    <a href="/semi/ipdetail1.bd?pname=<%= "햄머드릴(PHD-3800)" %>" ><img src="/semi/resources/images/main/04_1.jpg"></a>
 
                                </div>
                                <div class="item-entry overflow">
                                    <h5><a href="/semi/ipdetail1.bd?pname=<%= "햄머드릴(PHD-3800)" %>" >햄머드릴(PHD-3800)</a></h5>
                                    <div class="dot-hr"></div>
                                    <span class="pull-left"><b>가격 :</b></span>
                                    <span class="proerty-price pull-right">10000원</span>
                                </div>
                            </div>
                        </div>


                        <div class="col-sm-6 col-md-3 p0">
                            <div class="box-two proerty-item">
                                <div class="item-thumb">
                                    <a href="/semi/ipdetail1.bd?pname=<%= "U랜턴" %>" ><img src="/semi/resources/images/main/05_1.jpg"></a>
                                </div>
                                <div class="item-entry overflow">
                                    <h5><a href="/semi/ipdetail1.bd?pname=<%= "U랜턴" %>" >U랜턴</a></h5>
                                    <div class="dot-hr"></div>
                                    <span class="pull-left"><b>가격 :</b></span>
                                    <span class="proerty-price pull-right">1000원</span>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-6 col-md-3 p0">
                            <div class="box-two proerty-item">
                                <div class="item-thumb">
                                    <a href="/semi/ipdetail1.bd?pname=<%= "건토치" %>" ><img src="/semi/resources/images/main/06_1.jpg"></a>
                                </div>
                                <div class="item-entry overflow">
                                    <h5><a href="/semi/ipdetail1.bd?pname=<%= "건토치" %>" >건토치</a></h5>
                                    <div class="dot-hr"></div>
                                    <span class="pull-left"><b>가격 :</b></span>
                                    <span class="proerty-price pull-right">1000원</span>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-6 col-md-3 p0">
                            <div class="box-two proerty-item">
                                <div class="item-thumb">
                                    <a href="/semi/ipdetail1.bd?pname=<%= "에어타가" %>" ><img src="/semi/resources/images/main/07_1.jpg"></a>
                                </div>
                                <div class="item-entry overflow">
                                    <h5><a href="/semi/ipdetail1.bd?pname=<%= "에어타가" %>" >에어타가</a></h5>
                                    <div class="dot-hr"></div>
                                    <span class="pull-left"><b>가격 :</b></span>
                                    <span class="proerty-price pull-right">1000원</span>
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-sm-6 col-md-3 p0">
                            <div class="box-two proerty-item">
                                <div class="item-thumb">
                                    <a href="/semi/ipdetail1.bd?pname=<%= "AC컴프레셔-직결식" %>" ><img src="/semi/resources/images/main/08_1.jpg"></a>
                                </div>
                                <div class="item-entry overflow">
                                    <h5><a href="/semi/ipdetail1.bd?pname=<%= "AC컴프레셔-직결식" %>" >AC컴프레셔-직결식</a></h5>
                                    <div class="dot-hr"></div>
                                    <span class="pull-left"><b>가격 :</b></span>
                                    <span class="proerty-price pull-right">3000원</span>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-6 col-md-3 p0">
                            <div class="box-tree more-proerty text-center"  style="margin-left : 96px; margin-top : 50px;"> 
                                <div class="item-tree-icon">
                                    <i class="fa fa-th" onclick="move();"></i>
                                </div>
                                <div class="more-entry overflow">
                                    <h5><a href="/semi/plist" >CAN'T DECIDE ? </a></h5>
                                    <h5 class="tree-sub-ttl">Show all properties</h5>
                                    <button class="btn border-btn more-black" value="All properties" onclick="move();">전체보기</button>
                                </div>
                            </div>
                        </div>
                       
   
                    </div>
                </div>
                
            </div>
        </div>
	
<%@ include file="footer.jsp" %>