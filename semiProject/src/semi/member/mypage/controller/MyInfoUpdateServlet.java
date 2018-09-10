package semi.member.mypage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.member.model.service.MemberService;
import semi.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/mupdate.cp")
public class MyInfoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInfoUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 		//필터 래퍼처리해야함
		
		Member member = new Member();
		member.setmId(request.getParameter("mid"));
		member.setmNickname(request.getParameter("mnickname"));
		member.setmPhone(request.getParameter("mphone"));
		member.setmAddress(request.getParameter("maddress"));
		member.setmEmail(request.getParameter("memail"));
		member.setmGender(request.getParameter("mgender"));
		member.setmPassword(request.getParameter("m_password1"));
		member.setmSno(request.getParameter("msno"));
		member.setmPwd(request.getParameter("m_password2"));
		
		try {
			if(new MemberService().updateMember(member) > 0){
				response.sendRedirect("/semi/index.jsp");
			}else{
				RequestDispatcher errorPage = request.getRequestDispatcher("views/member/memberError.jsp");
				request.setAttribute("message", "회원정보 수정 실패!");
				errorPage.forward(request, response);
				
			}
		} catch (Exception e) {
			RequestDispatcher errorPage = request.getRequestDispatcher("views/member/memberError.jsp");
			request.setAttribute("message", e.getMessage());
			errorPage.forward(request, response);
		}
		
		
		
	}

}
