<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../../header.jsp" %>
<script type="text/javascript">
function cancle(){
	location.href = "/semi/index.jsp";
};
$(function(){
	$("#enter2").click(function(){
		var name = $("#name2").val();
		var email = $("#email").val();
		var exp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		
		if(name == ""){
			alert("이름을 입력해주세요.");
			$("#name2").focus();
			return false;
		}
		if(email == ""){
			alert("email을 입력해주세요.");
			$("#email").focus();
			return false;
		}
		if(!exp.test(email)){
			alert("올바른 email을 입력해주세요!");
			$("#email").focus();
			return false;
		} 
		 
	});
});

</script>
 <table width="1330px" height="430px" align="center">
    <tr>
     <td>
     <form action="/semi/findpw" method="post">
      <table width="600px" align="center" border="0"
       style="color: black; background-color: white; font-size: 20px;">
       <tr>
        <td>
         <table width="750px" align="center" border=0;
          style="background-color: white; margin-top: 3%">
          <tr>
           <td align="center"></td> 
           <td width = "750px"><h2 style="color : #777;">비밀번호 찾기</h2></td><br>

          </tr>
         </table>
         <hr>
        </td>
       </tr>

       <tr>
        <td>
         <table width="800px" align="center"
          style="border: dotted 5px none;">
 
          <tr>
           <td>
            <table width="100%"  border="0"
             style="margin-top: 3%; font-size: 18px;">
             
             <tr>
              <td>
              </td>
              <td align = "center" style="color : #777;">본인 확인용 정보로 찾기</td>
             </tr>
             
            </table>
            <hr>
           </td>
          </tr>

          <td>
           <table width="380px" height="70px" align="center" border="0"
            style="font-size: 16px;">
             <tr>
             <td style = "background : #ececee;" align="center">아이디</td>
             <td>&nbsp;&nbsp;<input type="text" name="id2" id="id2" style="width : 200px;"></td>  
          	 </tr>

            <tr>
             <td style = "background : #ececee;" align="center">이름</td>
             <td>&nbsp;&nbsp;<input type="text" name="name2" id="name2" style="width : 200px;"></td>
            </tr> 
            <tr>
             <td style = "background : #ececee;" align="center">e-mail</td>
             <td>&nbsp;&nbsp;<input type="text" name="email" id="email" style="width : 200px;"> 
              </td>
            </tr>
           </table>
          </td>
          
          </tr>

<tr>
           <td>
            <table width="140px" height="10px" border="0"
             style="margin-top: 2%" align="center">
             <tr>
              <td><input type="submit" name="enter2" value="  찾기  " id="enter2"
               align="center"
               style="cursor: pointer; background: white; color: black; border-color: black;"></td>
              <td><input type="button" name="cancle2"
               value="  취소  " align="center"
               style="cursor: pointer; background: white; color: black; border-color: black"
               onClick="cancle()"></td>
             </tr>
            </table>
            <br>
           </td>
          </tr>
          
          <tr>
           <td>
            <table width="270px" height="10px" border="0"
             style="margin-top: 2%" align="center">
             <tr>
              <td><a href="/semi/views/member/idAndPasswordSearchView.jsp" style="width : 100px; color : #777; font-size : 16px;">아이디 찾기&nbsp;&nbsp;&nbsp; |</a></td>
              <td><a href="/semi/views/member/passwordSearchView.jsp" style="width : 100px; color : #777; font-size : 16px;">비밀번호 찾기</a></td>
             </tr>
            </table>
            <br>
           </td>
          </tr>
         </table>
         </form>
        </td>
       </tr>
      </table>
     </td>
    </tr>
   </table>
   <br>
<%@ include file ="../../footer.jsp" %>
