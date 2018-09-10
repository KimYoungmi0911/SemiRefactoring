<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String mName = (String)session.getAttribute("m_Name");
	String mId = (String)session.getAttribute("m_Id");

	String mPassword = (String)session.getAttribute("m_password");
	String loginmod = (String) session.getAttribute("loginmode");
%>
   <%@ include file="head.jsp" %>
		<script type="text/javascript">
			$(function(){
				
				$("#btnLogin").click(function(){
					var mid = $("#mid").val();
					var mpassword = $("#mpassword").val();
					var exp = /[a-z0-9]$/;
					
					if(mid == ""){
						alert("아이디를 입력해주세요");
						$("#mid").focus();
						return;
					}
					if(!exp.test(mid)){
						alert("영문자와 숫자만 입력가능합니다.");
						$("#mid").focus();
						return;
					}
				    if (mpassword == "") {
		                alert("비밀번호를 입력해주세요");
		                $("#mpassword").focus();
		                return;
		            }

				});
				
			});
			


		</script>
		
        <style type="text/css">
        input::placeholder {
  			color: #aaaaaa;
			font-size : 10px;
		} 
        </style>
        <!-- Body content -->

        <div class="header-connect">
            <div class="container">
                <div class="row">
                    <div class="col-md-5 col-sm-8  col-xs-12">
                        <div class="header-half header-call">
                            <p>
                                <span><i class="pe-7s-call"></i> 1544-9970</span>
                                <span><i class="pe-7s-mail"></i> Project09@iei.or.kr</span>
                            </p>
                        </div>
                    </div>
                    <div class="col-md-2 col-md-offset-5  col-sm-3 col-sm-offset-1  col-xs-12" style="margin-left: 50%;">
                        <div class="header-half header-social">
                            <ul class="list-inline">
                              <input type="button" id="loginradio" name="loginradio" value="관리자로 전환" onclick="goPage();" style="border : 0px; color : white; background : white;">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>        
        <!--End top header -->

        <nav class="navbar navbar-default ">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navigation">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/semi/index.jsp">
                    <img src="/semi/resources/images/common/mainlogo.png" alt="" width="150px;">
                    </a>
                </div>
				
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse yamm" id="navigation">
                    <div class="button navbar-right" id="ajax_load_indicator">
                       <% if(mName == null){ %>
                   <form id="loginForm" action="/semi/login.cp" method="post">

                        <!-- <input type="button" id="loginradio" name="loginradio" value="관리자로 전환" onclick="goPage();"> -->
                        <input type="radio" name="loginmode" id="loginmode" value="회원">회원
                        <input type="radio" name="loginmode" id="loginmode" value="관리자">관리자
                        
                        

                        <table width="250" height="75" cellspacing="0" cellpadding="0">
					<tr><td width="200">
					
					  <input type="text"  id="mid" name="mid" size="15" required placeholder="아이디를 입력하세요" style="border : 0px;">
					</td>
					<td width="50" rowspan="2">
					<input type="submit" value="로그인" class= "navbar-btn nav-button wow bounceInRight login" id="btnLogin" name="btnLogin">
					</td></tr>
				<tr><td>
					<!-- <input type="password" name="userpwd" size="15"> -->
					<input type="password" id="mpassword" name="m_password1" size="15" required placeholder="비밀번호를 입력하세요" style="border : 0px;">
				   </td></tr>
			
				   <tr><td colspan="2">
					<a href="/semi/views/member/memberEnroll.jsp">회원가입</a> &nbsp;
					<a href="/semi/views/member/idAndPasswordSearchView.jsp">아이디/암호조회</a> 
					
				</td>
				</tr>
				</table> 
				<br>
				</form>
			
				<%}else{ %>
				<table width="250" height="75" cellspacing="0" cellpadding="0">
				<tr><td width="150">
					<%= mName %> 님. 
					</td>
					<td width="100">
					<a href="/semi/logout">로그아웃</a>
					</td></tr>
				<tr><td colspan="2">
				<a href="/semi/myinfo?mid=<%= mId %>">내 정보보기</a>
				
				
					
				</td></tr>
				</table>
				<%} %>
				
                    </div>
                    <ul class="main-nav nav navbar-nav navbar-right">
                        <li class="wow fadeInDown" data-wow-delay="0.1s">
                           <a class="" href="/semi/index.jsp"> Home </a>
                        </li>

                        <li class="dropdown ymm-sw " data-wow-delay="0.2s">
                        	<a href="index.html" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="200">대여품목 <b class="caret"></b></a>
                        	<ul class="dropdown-menu navbar-nav">
                                <li>
                                    <a href="/semi/plist">전체보기</a>
                                </li>
                                <li>
                                    <a href="/semi/cutoff">- 절단공구</a>
                                </li>
                                <li>
                                    <a href="/semi/drill">- 드릴공구</a>
                                </li>
                                <li>
                                    <a href="/semi/charge">- 충전공구</a>
                                </li>
                                <li>
                                    <a href="/semi/etc">- 기타공구</a>
                                </li>
                                <li>
                                    <a href="/semi/lifeetc">- 기타생활공구</a>
                                </li>

                            </ul>
                        </li>
                        <li class="dropdown ymm-sw " data-wow-delay="0.3s">
                        	<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="200">게시판 <b class="caret"></b></a>
                        	<ul class="dropdown-menu navbar-nav">
                                <li>
                                    <a href="/semi/nglist">- 공지사항</a>
                                </li>
                                <li>
                                    <a href="/semi/qlist">- 문    의</a>
                                </li>
                                <li>
                                    <a href="/semi/blist">- 후    기</a>
                                </li>
                            </ul>
                        </li>
                        <li class="wow fadeInDown" data-wow-delay="0.4s">

                            <a href="/semi/views/location/locationMapView.jsp" class="dropdown-toggle">오시는길</a><!-- /semi/maplist -->

                        </li>
						<% if(loginmod != null && loginmod.equals("관리자")) { %>
						<li class="dropdown ymm-sw " data-wow-delay="0.2s">
                        	<a href="index.html" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="200">관   리 <b class="caret"></b></a>
                        <ul class="dropdown-menu navbar-nav">
                                <li>
                                    <a href="/semi/views/manager/memberManagementView.jsp">- 회원관리</a>
                                </li>
                                <li>
                                    <a href="/semi/views/manager/toolManagementView.jsp">- 재고관리</a>
                                </li>
                                <li>
                                    <a href="/semi/views/manager/rentalManagementView.jsp">- 대여관리</a>
                                </li> 

                            </ul>
                        </li>
                        <% } else { %>
                        <li class="wow fadeInDown" data-wow-delay="0.5s"><a href="/semi/views/notice/InformationUseView.jsp">이용안내</a></li>
                        <% } %>
                      
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <!-- End of nav bar -->