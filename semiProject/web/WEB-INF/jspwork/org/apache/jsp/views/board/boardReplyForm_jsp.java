/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.32
 * Generated at: 2018-07-31 12:17:52 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.views.board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class boardReplyForm_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("/views/board/../../adminHeader.jsp", Long.valueOf(1532595464498L));
    _jspx_dependants.put("/views/board/../../footer.html", Long.valueOf(1532430422975L));
    _jspx_dependants.put("/views/board/../../header.jsp", Long.valueOf(1532944304542L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"boardError.jsp", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');

	int boardNum = Integer.parseInt(request.getParameter("bnum"));
	int currentPage = Integer.parseInt(request.getParameter("page"));
	String userId = (String)session.getAttribute("userId");

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>boardReplyForm</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
 if(userId != null && userId.equals("admin")){ 
      out.write('\r');
      out.write('\n');
      out.write('	');
      out.write('\r');
      out.write('\n');

	//jsp 파일 안에는 request, session 객체도 내장되어 있음
	String userName = (String)session.getAttribute("userName");
	String userid = (String)session.getAttribute("userId");

      out.write("     \r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>header</title>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("\t#menubar ul li {\r\n");
      out.write("\t\tfloat: left;\r\n");
      out.write("\t\twidth: 120px;\r\n");
      out.write("\t\theight: 35px;\r\n");
      out.write("\t\tdisplay: inline;\r\n");
      out.write("\t\tbackground: orange;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t#menubar\r\n");
      out.write("\t\r\n");
      out.write("\thr { clear: both; }\r\n");
      out.write("\t\r\n");
      out.write("\tdiv#loginForm table {\r\n");
      out.write("\t\tbackground : LightSkyBlue;\r\n");
      out.write("\t}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<header>\r\n");
      out.write("<h1>second</h1>\r\n");
      out.write("<nav id=\"menubar\">\r\n");
      out.write("<ul>\r\n");
      out.write("\t<li><a href=\"/second/index.jsp\">home</a></li>\r\n");
      out.write("\t<li><a href=\"/second/mlist\">회원관리</a></li>\r\n");
      out.write("\t<li><a href=\"/second/nlist\">공지관리</a></li>\r\n");
      out.write("\t<li><a>게시글관리</a></li>\r\n");
      out.write("\t<li><a>사진게시판관리</a></li>\r\n");
      out.write("</ul>\r\n");
      out.write("</nav>\r\n");
      out.write("<div id=\"loginForm\">\r\n");
 if(userName == null){ 
      out.write("\r\n");
      out.write("<form action=\"/second/login\" method=\"post\">\r\n");
      out.write("<table width=\"250\" height=\"75\" cellspacing=\"0\" \r\n");
      out.write("cellpadding=\"0\">\r\n");
      out.write("<tr><td width=\"200\">\r\n");
      out.write("\t<input type=\"text\" name=\"userid\" id=\"userid\" size=\"15\">\r\n");
      out.write("\t</td>\r\n");
      out.write("\t<td width=\"50\" rowspan=\"2\">\r\n");
      out.write("\t<input type=\"submit\" value=\"로그인\">\r\n");
      out.write("\t</td></tr>\r\n");
      out.write("<tr><td>\r\n");
      out.write("\t<input type=\"password\" name=\"userpwd\" size=\"15\">\r\n");
      out.write("   </td></tr>\r\n");
      out.write("<tr><td colspan=\"2\">\r\n");
      out.write("\t<a href=\"views/member/memberEnroll.html\">회원가입</a> &nbsp;\r\n");
      out.write("\t<a href=\"#\">아이디/암호조회</a>\r\n");
      out.write("</td></tr>\r\n");
      out.write("</table>\r\n");
      out.write("</form>\r\n");
 }else{ 
      out.write("\r\n");
      out.write("<table width=\"250\" height=\"75\" cellspacing=\"0\" \r\n");
      out.write("cellpadding=\"0\">\r\n");
      out.write("<tr><td width=\"150\">\r\n");
      out.write("\t");
      out.print( userName );
      out.write(" 님.\r\n");
      out.write("\t</td>\r\n");
      out.write("\t<td width=\"100\">\r\n");
      out.write("\t<a href=\"/second/logout\">로그아웃</a>\r\n");
      out.write("\t</td></tr>\r\n");
      out.write("<tr><td>\t메일 개 </td> <td>쪽지  개</td></tr>\r\n");
      out.write("<tr><td colspan=\"2\">\r\n");
      out.write("\t<a href=\"/second/myinfo?userid=");
      out.print( userid );
      out.write("\">내 정보보기</a>\t\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("</td></tr>\r\n");
      out.write("</table>\r\n");
 } 
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("</header>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write('\r');
      out.write('\n');
 }else{ 
      out.write('\r');
      out.write('\n');
      out.write('	');
      out.write('\r');
      out.write('\n');

	//jsp 파일 안에는 request, session 객체도 내장되어 있음
	String userName = (String)session.getAttribute("userName");
	String userid = (String)session.getAttribute("userId");

      out.write("     \r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>header</title>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("\t#menubar ul li {\r\n");
      out.write("\t\tfloat: left;\r\n");
      out.write("\t\twidth: 120px;\r\n");
      out.write("\t\theight: 35px;\r\n");
      out.write("\t\tdisplay: inline;\r\n");
      out.write("\t\tbackground: orange;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t#menubar\r\n");
      out.write("\t\r\n");
      out.write("\thr { clear: both; }\r\n");
      out.write("\t\r\n");
      out.write("\tdiv#loginForm table {\r\n");
      out.write("\t\tbackground : LightSkyBlue;\r\n");
      out.write("\t}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<header>\r\n");
      out.write("<h1>second</h1>\r\n");
      out.write("<nav id=\"menubar\">\r\n");
      out.write("<ul>\r\n");
      out.write("\t<li><a href=\"/second/test\">testServlet</a></li>\r\n");
      out.write("\t<li><a href=\"/second/nlist\">공지사항</a></li>\r\n");
      out.write("\t<li><a href=\"/second/blist\">게시판</a></li>\r\n");
      out.write("\t<li><a>QnA(답글게시판)</a></li>\r\n");
      out.write("\t<li><a>사진게시판</a></li>\r\n");
      out.write("</ul>\r\n");
      out.write("</nav>\r\n");
      out.write("<div id=\"loginForm\">\r\n");
 if(userName == null){ 
      out.write("\r\n");
      out.write("<form action=\"/second/login\" method=\"post\">\r\n");
      out.write("<table width=\"250\" height=\"75\" cellspacing=\"0\" \r\n");
      out.write("cellpadding=\"0\">\r\n");
      out.write("<tr><td width=\"200\">\r\n");
      out.write("\t<input type=\"text\" name=\"userid\" id=\"userid\" size=\"15\">\r\n");
      out.write("\t</td>\r\n");
      out.write("\t<td width=\"50\" rowspan=\"2\">\r\n");
      out.write("\t<input type=\"submit\" value=\"로그인\">\r\n");
      out.write("\t</td></tr>\r\n");
      out.write("<tr><td>\r\n");
      out.write("\t<input type=\"password\" name=\"userpwd\" size=\"15\">\r\n");
      out.write("   </td></tr>\r\n");
      out.write("<tr><td colspan=\"2\">\r\n");
      out.write("\t<a href=\"views/member/memberEnroll.html\">회원가입</a> &nbsp;\r\n");
      out.write("\t<a href=\"#\">아이디/암호조회</a>\r\n");
      out.write("</td></tr>\r\n");
      out.write("</table>\r\n");
      out.write("</form>\r\n");
 }else{ 
      out.write("\r\n");
      out.write("<table width=\"250\" height=\"75\" cellspacing=\"0\" \r\n");
      out.write("cellpadding=\"0\">\r\n");
      out.write("<tr><td width=\"150\">\r\n");
      out.write("\t");
      out.print( userName );
      out.write(" 님.\r\n");
      out.write("\t</td>\r\n");
      out.write("\t<td width=\"100\">\r\n");
      out.write("\t<a href=\"/second/logout\">로그아웃</a>\r\n");
      out.write("\t</td></tr>\r\n");
      out.write("<tr><td>\t메일 개 </td> <td>쪽지  개</td></tr>\r\n");
      out.write("<tr><td colspan=\"2\">\r\n");
      out.write("\t<a href=\"/second/myinfo?userid=");
      out.print( userid );
      out.write("\">내 정보보기</a>\t\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("</td></tr>\r\n");
      out.write("</table>\r\n");
 } 
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("</header>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write('\r');
      out.write('\n');
 } 
      out.write("\r\n");
      out.write("<hr>\r\n");
      out.write("<h1 align=\"center\">");
      out.print( boardNum );
      out.write("글의 댓글 달기</h1>\r\n");
      out.write("<br>\r\n");
      out.write("<form action=\"/second/breply\" method=\"post\">\r\n");
      out.write("<input type=\"hidden\" name=\"bnum\" value=\"");
      out.print( boardNum );
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"page\" value=\"");
      out.print( currentPage );
      out.write("\">\r\n");
      out.write("<table align=\"center\" width=\"500\" border=\"1\" \r\n");
      out.write("cellpadding=\"10\" cellspacing=\"0\">\r\n");
      out.write("<tr>\r\n");
      out.write("\t<td height=\"15\" width=\"100\">제목</td>\r\n");
      out.write("\t<td><input type=\"text\" name=\"btitle\" size=\"50\"></td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("\t<td height=\"15\" width=\"100\">작성자</td>\r\n");
      out.write("\t<td><input type=\"text\" name=\"bwriter\" \r\n");
      out.write("\tvalue=\"");
      out.print( userId );
      out.write("\" readonly></td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("\t<td>내용</td>\r\n");
      out.write("\t<td>\r\n");
      out.write("\t<textarea name=\"bcontent\" cols=\"50\" rows=\"7\">\r\n");
      out.write("\t</textarea></td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr align=\"center\" valign=\"middle\">\r\n");
      out.write("\t<td colspan=\"2\">\r\n");
      out.write("\t<input type=\"submit\" value=\"댓글등록\"> &nbsp;\r\n");
      out.write("\t<input type=\"button\" value=\"취소\" \r\n");
      out.write("\tonclick=\"history.go(-1); return false;\"> &nbsp;\r\n");
      out.write("\t&nbsp; &nbsp;\r\n");
      out.write("\t<a href=\"/second/blist?page=");
      out.print( currentPage );
      out.write("\">[목록]</a>\r\n");
      out.write("\t</td>\r\n");
      out.write("</tr>\r\n");
      out.write("</table>\r\n");
      out.write("</form>\r\n");
      out.write("<br><br>\r\n");
      out.write("<hr>\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title></title>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("\tfooter {\r\n");
      out.write("\t\twidth : 100%;\r\n");
      out.write("\t\theight : 70px;\r\n");
      out.write("\t\tbackground : gray;\r\n");
      out.write("\t\ttext-shadow : 1px 1px 2px white;\r\n");
      out.write("\t}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<center>\r\n");
      out.write("<footer>\r\n");
      out.write("\tcopyright@iei.or.kr 2018.07.24 íê¸¸ë<br>\r\n");
      out.write("\ttel : 070-1234-5678, fax : 070-1111-2345\r\n");
      out.write("</footer>\r\n");
      out.write("</center>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
