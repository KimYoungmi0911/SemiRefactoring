<%@page import="semi.locationInfo.controller.LocationListServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="semi.locationInfo.model.vo.LocationInfo, java.util.ArrayList" %>

<%@ include file="../../header.jsp" %>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b07804eb6c910b861023a656cfa85814&libraries=services"></script>
<style type="text/css">
	#astyle {
		float: left;
		padding: 0 14px;
		line-height: 38px;
		text-decoration: none;
		background-color: #ffffff;
		border: 1px solid #dddddd;
		color: #d27e04;
		outline: 0;
		
	}
</style>

<div>
	<!-- 검색구역 -->
	<div align="center" margin-top="10px">
		<select id="selectlocation" name="selectlocation" style="border-radius: 1px; padding: 10px 20px; border: 1px solid #fdc600; color: #000; background-color: #fdc600; border-color:#f0f0f0; font-weight: 600; margin-right: 5px; margin-top: 10px; margin-bottom: 10px;">
			<option selected>전체</option>
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
		<input type="search" id="searchinput" name="searchinput" placeholder="주민센터" size="80px" style="width: 400px; height:44px; box-sizing: content-box; font-size: 20; margin-right: 5px; margin-top: 10px; margin-bottom: 10px;">
		<input type="button" onclick="selectAddress(1);" value="찾기" style="color: #fff; background-color: #fdc600; border-color: fdc600; border-radius: 1px; padding: 10px 20px; font-weight: 600; margin-right: 5px; margin-top: 10px; margin-bottom: 10px;">
	</div>

	<!-- 지도 출력 -->
	<div id="map" style="width: 90%; height: 550px; margin-left: 5%"></div>

	<script>
		$(function() {
			var currentPage;
			var maxPage;
			var startPage;
			var endPage;
			
			$.ajax({
				url : "/semi/mappage",
				type : "get",
				data : {"page" : 1},
				dataType : "json",
				success : function(data) {
					mapView(data);
					
					var jsonStr = JSON.stringify(data);
					var json = JSON.parse(jsonStr);
					
					console.log("aaa");
					currentPage = json.currentPage;
					maxPage = json.maxPage;
					startPage = json.startPage;
					endPage = json.endPage;
					
					$(".page").html("");
					if(currentPage <= 1) {	
					} else {
						$(".page").append("<li><a id='astyle' href='#' onclick='paging(1)'><<</a></li>");
					}
					
					if(currentPage == 1) {
					} else {
						$(".page").append("<li><a id='astyle' href='#' onclick='paging(" + currentPage + " - 1)'><</a></li>");
					}
					
					for (var p = startPage; p <= endPage; p++) { 
						if (p == currentPage) {
							$(".page").append("<li><a id='astyle' href='#'><font color='red'>" + p + "</font></a></li>");
						} else {
							$(".page").append("<li><a id='astyle' href='#' onclick='paging(" + p + ")'>" + p + "</a></li>");
						}
					}
					
					if (currentPage == maxPage) {
					} else {
						$(".page").append("<li><a id='astyle' href='#' onclick='paging(" + currentPage + " + 1)'> ></a></li>");
					}

					if (currentPage >= maxPage) {
					} else {
						$(".page").append("<li><a id='astyle' href='#' onclick='paging(" + maxPage + ")'> >> </a></li>");
					}
				},
					error : function(jqXHR, textstatus, errorThrown){
						console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
					}
			});	
		});
		
		function paging(page) {
			var currentPage;
			var maxPage;
			var startPage;
			var endPage;
			
			$.ajax({
				url : "/semi/mappage",
				type : "get",
				data : {"page" : page},
				dataType : "json",
				success : function(data) {
					mapView(data);
					
					var jsonStr = JSON.stringify(data);
					var json = JSON.parse(jsonStr);
					
					console.log("aaa");
					currentPage = json.currentPage;
					maxPage = json.maxPage;
					startPage = json.startPage;
					endPage = json.endPage;
					
					$(".page").html("");
					if(currentPage <= 1) {	
					} else {
						$(".page").append("<li><a href='#' onclick='paging(1)'><<</a></li>");
					}
					
					if(currentPage == 1) {
					} else {
						$(".page").append("<li><a href='#' onclick='paging(" + currentPage + " - 1)'><</a></li>");
					}
					
					for (var p = startPage; p <= endPage; p++) { 
						if (p == currentPage) {
							$(".page").append("<li><a href='#'><font color='red'>" + p + "</font></a></li>");
						} else {
							$(".page").append("<li><a href='#' onclick='paging(" + p + ")'>" + p + "</a></li>");
						}
					}
					
					if (currentPage == maxPage) {
					} else {
						$(".page").append("<li><a href='#' onclick='paging(" + currentPage + " + 1)'> ></a></li>");
					}

					if (currentPage >= maxPage) {
					} else {
						$(".page").append("<li><a href='#' onclick='paging(" + maxPage + ")'> >> </a></li>");
					}
				},
					error : function(jqXHR, textstatus, errorThrown){
						console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
					}
			});
		}
		
		function mapView(data) {
			var jsonStr = JSON.stringify(data);
			var json = JSON.parse(jsonStr);

			var values = "<tr><th style='width: 15%; border-bottom: 2px solid rgb(221, 221, 221);'>구 이름</th><th style='width: 65%; border-bottom: 2px solid rgb(221, 221, 221);'>상세 주소</th><th style='width: 20%; border-bottom: 2px solid rgb(221, 221, 221);'>주민센터</th></tr>";
			for(var i in json.list){
				values += "<tr><td style='border-bottom: 2px solid rgb(221, 221, 221); padding: .75rem;'>" + 
				decodeURIComponent(json.list[i].local) + "</td><td class='address' style='border-bottom: 2px solid rgb(221, 221, 221); padding: .75rem;'>" + 
				decodeURIComponent(json.list[i].address).replace("+", " ").replace("+", " ").replace("+", " ").replace("+", " ").replace("+", " ").replace("+", " ").replace("+", " ").replace("+", " ") + 
				"</td><td class='servicecenter' style='border-bottom: 2px solid rgb(221, 221, 221); padding: .75rem;'>" + 
				decodeURIComponent(json.list[i].name).replace("+", " ") + "</td></tr>";
			}
			 
			$(".listtable").html(values);
			
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			mapOption = {
				center : new daum.maps.LatLng(37.499257, 127.032909), // 지도의 중심좌표
				level : 7
			// 지도의 확대 레벨
			};
			// 지도를 생성합니다    
			var map = new daum.maps.Map(mapContainer, mapOption);

			// 주소-좌표 변환 객체를 생성합니다
			var geocoder = new daum.maps.services.Geocoder();
			var address = $(".address");
			var sc = $(".servicecenter");
			
			console.log(address);
			var coords;
			var marker;
			var infowindow;
			// 지도를 재설정할 범위정보를 가지고 있을 LatLngBounds 객체를 생성합니다
			var bounds = new daum.maps.LatLngBounds();
			
			var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
			
			$.each(address,function(i, o) {
				var imageSize = new daum.maps.Size(24, 35); 
				var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize); 
				
				geocoder.addressSearch(o.innerText, function(result, status) {
					if(status == daum.maps.services.Status.OK) {
						coords = new daum.maps.LatLng(result[0].y, result[0].x);
						itemEl = $(".address");
						
						marker = new daum.maps.Marker({
				            map: map,
				            position: coords,
				            image : markerImage
				        });
						
						// LatLngBounds 객체에 좌표를 추가합니다
					    bounds.extend(coords);
						
						// LatLngBounds 객체에 추가된 좌표들을 기준으로 지도의 범위를 재설정합니다
				        // 이때 지도의 중심좌표와 레벨이 변경될 수 있습니다
				        map.setBounds(bounds);
						
						infowindow = new daum.maps.InfoWindow({
					        content: sc[i].innerText // 인포윈도우에 표시할 내용
					    });
						
						daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
					    daum.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
					    
					    $(this).onmouseover =  function () {
			                displayInfowindow(marker, sc[i].innerText);
			            };

			            itemEl.onmouseout =  function () {
			                infowindow.close();
			            };
					}
				});
			});
		}
		
		function displayInfowindow(marker, title) {
		    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

		    infowindow.setContent(content);
		    infowindow.open(map, marker);
		}
		
		function makeOverListener(map, marker, infowindow) {
		    return function() {
		    	console.log("info");
		        infowindow.open(map, marker);
		    };
		}
		
		function makeOutListener(infowindow) {
		    return function() {
		        infowindow.close();
		    };
		}
		
		function selectAddress(page) {
			var selectGu = $("#selectlocation").val();
			var inputText = $("#searchinput").val();
			
			var currentPage;
			var maxPage;
			var startPage;
			var endPage;
			
			var selectText;
				
			if(inputText == "") {
				selectText = {"selectGu" : selectGu, "page" : page};
			} else {
				selectText = {"selectGu" : selectGu, "inputText" : inputText, "page" : page};
			}
			
			$.ajax({
				url : "/semi/lSelect",
				type : "get",
				data : selectText,
				dataType : "json",
				success : function(data) {
					mapView(data);
					
					var jsonStr = JSON.stringify(data);
					var json = JSON.parse(jsonStr);
					
					console.log("aaa");
					currentPage = json.currentPage;
					maxPage = json.maxPage;
					startPage = json.startPage;
					endPage = json.endPage;
					listCount = json.listCount;
					
					$(".page").html("");
					if(currentPage <= 1) {	
					} else {
						$(".page").append("<li><a href='#' onclick='selectAddress(1)'><<</a></li>");
					}
					
					if(currentPage == 1) {
					} else {
						$(".page").append("<li><a href='#' onclick='selectAddress(" + currentPage + " - 1)'><</a></li>");
					}
					
					for (var p = startPage; p <= endPage; p++) { 
						if (p == currentPage) {
							$(".page").append("<li><a href='#'><font color='red'>" + p + "</font></a></li>");
						} else {
							$(".page").append("<li><a href='#' onclick='selectAddress(" + p + ")'>" + p + "</a></li>");
						}
					}
					
					if (currentPage == maxPage || listCount == 0) {
						if(listCount == 0) {
							alert("입력하신 주민센터를 찾을 수 없습니다.");
							paging(1);
						}
					} else {
						$(".page").append("<li><a href='#' onclick='selectAddress(" + currentPage + " + 1)'> ></a></li>");
					}

					if (currentPage >= maxPage) {
					} else {
						$(".page").append("<li><a id='astyle' href='#' onclick='selectAddress(" + maxPage + ")'> >> </a></li>");
					}
				},
				error : function(jqXHR, textstatus, errorThrown){
							console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
				}
			});
		}
	</script>
	<!-- 주소 리스트 -->	
	<div align="center">
		<table class="listtable" style="width: 70%; min-width:50%; margin-top: 20px; margin-bottom: 15px;">
		</table>
		<div style="padding: 0; width: 70%; font-family: 'Open Sans', sans-serif;">
			<div class="pagination">
				<ul class="page" style="padding: 0px; margin-top: 0px;">
				</ul>
			</div>
		</div>
	</div>
</div>
<%@ include file="../../footer.jsp" %>