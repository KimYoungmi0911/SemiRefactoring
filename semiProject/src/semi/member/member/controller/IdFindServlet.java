package semi.member.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.member.model.service.MemberService;

/**
 * Servlet implementation class IdPwFindServlet
 */
@WebServlet("/findid")
public class IdFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdFindServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fName = request.getParameter("name2");
		String fEmail = request.getParameter("email");
	
		System.out.println(fName);
		System.out.println(fEmail);
		RequestDispatcher view = null;
		try {
			String result = new MemberService().findId(fName, fEmail);
			
			System.out.println("result : " + result);
			
			if(result != null){
				view = request.getRequestDispatcher("views/member/id_searchCtl2.jsp");
				request.setAttribute("result", result);
				view.forward(request, response);
			}
			else{
				view = request.getRequestDispatcher("views/member/memberError.jsp");
				request.setAttribute("message", "아이디가 없습니다.");
				view.forward(request, response);
			}
		} catch (Exception e) {
			view = request.getRequestDispatcher("views/member/memberError.jsp");
			request.setAttribute("message", e.getMessage());
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
