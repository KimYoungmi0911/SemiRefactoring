<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript">
 var stmnLEFT = 10; // 오른쪽 여백 
 var stmnGAP1 = 0; // 위쪽 여백 
 var stmnGAP2 = 150; // 스크롤시 브라우저 위쪽과 떨어지는 거리 
 var stmnBASE = 150; // 스크롤 시작위치 
 var stmnActivateSpeed = 25; //스크롤을 인식하는 딜레이 (숫자가 클수록 느리게 인식)
 var stmnScrollSpeed = 20; //스크롤 속도 (클수록 느림)
 var stmnTimer; 
 
 function RefreshStaticMenu() { 
  var stmnStartPoint, stmnEndPoint; 
  stmnStartPoint = parseInt(document.getElementById('STATICMENU').style.top, 10); 
  stmnEndPoint = Math.max(document.documentElement.scrollTop, document.body.scrollTop) + stmnGAP2; 
  if (stmnEndPoint < stmnGAP1) stmnEndPoint = stmnGAP1; 
  if (stmnStartPoint != stmnEndPoint) { 
   stmnScrollAmount = Math.ceil( Math.abs( stmnEndPoint - stmnStartPoint ) / 15 ); 
   document.getElementById('STATICMENU').style.top = parseInt(document.getElementById('STATICMENU').style.top, 10) 
   							+ ( ( stmnEndPoint<stmnStartPoint ) ? -stmnScrollAmount : stmnScrollAmount ) + 'px'; 
   stmnRefreshTimer = stmnScrollSpeed; 
   }
  stmnTimer = setTimeout("RefreshStaticMenu();", stmnActivateSpeed); 
  } 
 function InitializeStaticMenu() {
  document.getElementById('STATICMENU').style.right = stmnLEFT + 'px';  //처음에 오른쪽에 위치. left로 바꿔도.
  document.getElementById('STATICMENU').style.top = document.body.scrollTop + stmnBASE + 'px'; 
  RefreshStaticMenu();
  }
 
 $(function(){
	$("#topBtn").click(function(){
		$("html,body").animate({"scrollTop" : "0"}, 300);
	}); 
 });
 $(function(){
		$("#downBtn").click(function(){
			$("html,body").scrollTop($("html,body").height());
		}); 
	 });
 
 
</script>

<style type="text/css">
#STATICMENU { margin: 15pt; padding: 0pt;  position: absolute; right: 0px; top: 0px; width: 120px; height : 160px; }  
#bannerRightId{ background: black; color: white;}



</style>


</head>
<body id="본래 설정" onload="InitializeStaticMenu();">

</body>
<div id="STATICMENU" style="border : 0px solid black; width : 85px; height : 300px; border-radius: 5px 5px 5px 5px;">

<div>
<!-- <button id="topBtn" style="width : 83px; border-radius: 5px 5px 5px 5px;">TOP</button>  -->
<img src="/semi/resources/images/updown/up icon.svg" id="topBtn" style="cursor : pointer; ">
<img src="/semi/resources/images/updown/down icon.svg" id="downBtn" style="cursor : pointer; ">

</div>


</div>

</html>






<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript">
 var stmnLEFT = 10; // 오른쪽 여백 
 var stmnGAP1 = 0; // 위쪽 여백 
 var stmnGAP2 = 150; // 스크롤시 브라우저 위쪽과 떨어지는 거리 
 var stmnBASE = 150; // 스크롤 시작위치 
 var stmnActivateSpeed = 25; //스크롤을 인식하는 딜레이 (숫자가 클수록 느리게 인식)
 var stmnScrollSpeed = 20; //스크롤 속도 (클수록 느림)
 var stmnTimer; 
 
 function RefreshStaticMenu() { 
  var stmnStartPoint, stmnEndPoint; 
  stmnStartPoint = parseInt(document.getElementById('STATICMENU').style.top, 10); 
  stmnEndPoint = Math.max(document.documentElement.scrollTop, document.body.scrollTop) + stmnGAP2; 
  if (stmnEndPoint < stmnGAP1) stmnEndPoint = stmnGAP1; 
  if (stmnStartPoint != stmnEndPoint) { 
   stmnScrollAmount = Math.ceil( Math.abs( stmnEndPoint - stmnStartPoint ) / 15 ); 
   document.getElementById('STATICMENU').style.top = parseInt(document.getElementById('STATICMENU').style.top, 10) 
   							+ ( ( stmnEndPoint<stmnStartPoint ) ? -stmnScrollAmount : stmnScrollAmount ) + 'px'; 
   stmnRefreshTimer = stmnScrollSpeed; 
   }
  stmnTimer = setTimeout("RefreshStaticMenu();", stmnActivateSpeed); 
  } 
 function InitializeStaticMenu() {
  document.getElementById('STATICMENU').style.right = stmnLEFT + 'px';  //처음에 오른쪽에 위치. left로 바꿔도.
  document.getElementById('STATICMENU').style.top = document.body.scrollTop + stmnBASE + 'px'; 
  RefreshStaticMenu();
  }
 
 $(function(){
	$("#topBtn").click(function(){
		$("html,body").animate({"scrollTop" : "0"}, 300);
	}); 
 });
 
</script>

<style type="text/css">
#STATICMENU { margin: 15pt; padding: 0pt;  position: absolute; right: 0px; top: 0px; width: 120px; height : 160px; }  
#bannerRightId{ background: black; color: white;}

</style>


</head>
<body id="본래 설정" onload="InitializeStaticMenu();">

</body>
<div id="STATICMENU" style="border : 0px solid black; width : 85px; height : 300px; border-radius: 5px 5px 5px 5px;">
<a id="bannerRightId">오늘 본 상품</a>
<div align="center"><img src="/semi/resources/images/main/01_1.jpg"></div>
<div align="center"><img src="/semi/resources/images/main/02_1.jpg"></div>
<div align="center"><img src="/semi/resources/images/main/03_1.jpg"></div>
<div><button id="topBtn" style="width : 83px; border-radius: 5px 5px 5px 5px;">TOP</button></div>



</div>

</html> --%>




