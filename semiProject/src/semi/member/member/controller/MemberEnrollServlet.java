package semi.member.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.member.exception.MemberException;
import semi.member.model.service.MemberService;
import semi.member.model.vo.Member;



/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/menroll.cp")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberEnrollServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String m_Id = request.getParameter("m_id");
		String m_Password = request.getParameter("m_password1");
		String m_Name = request.getParameter("m_name");
		String m_NickName = request.getParameter("m_nickname");
		String m_Phone = request.getParameter("m_phone");
		String m_Address = request.getParameter("m_address");
		String m_Email = request.getParameter("m_email");
		String birth1 = request.getParameter("birth1");	
		String birth2 = request.getParameter("birth2");	
		String birth3 = request.getParameter("birth3");	
		String m_Sno = birth1 + birth2 + birth3;
		String m_Gender = request.getParameter("m_gender");
		String m_Pwd = request.getParameter("m_password2");
		
		
		System.out.println("암호화비밀번호는 : " + m_Password);
		System.out.println("비밀번호 :" + m_Pwd);

		Member m = new Member();
		m.setmId(m_Id);
		m.setmPassword(m_Password);
		m.setmName(m_Name);
		m.setmNickname(m_NickName);
		m.setmPhone(m_Phone);
		m.setmAddress(m_Address);
		m.setmEmail(m_Email);
		/*m.setM_Point(0);*/
		m.setmSno(m_Sno);
		m.setmGender(m_Gender);
		m.setmPwd(m_Pwd);
		System.out.println("암호화 안됨 : " + m.getmPwd());
		System.out.println(m.toString());

		response.setContentType("text/html; charset=utf-8");
		int result = 0;
		try {
			result = new MemberService().insertMember(m);
			if (result > 0) {
				// 회원 가입이 성공했을 때
				response.sendRedirect("/semi/index.jsp");
			} else {
				RequestDispatcher errorPage = request.getRequestDispatcher("views/member/memberError.jsp");
				request.setAttribute("message", "회원 가입 실패!");
				errorPage.forward(request, response);
			}
		} catch (Exception e) {
			RequestDispatcher errorPage = request.getRequestDispatcher("views/member/memberError.jsp");
			request.setAttribute("message", e.getMessage());
			errorPage.forward(request, response);
		}
		
		

	}
	/*
	 * M_ID M_PASSWORD M_NAME M_NICKNAME M_PHONE M_ADDRESS M_EMAIL M_POINT M_SNO
	 * M_GENDER
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
