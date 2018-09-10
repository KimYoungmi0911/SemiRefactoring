package semi.member.member.controller;

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
 * Servlet implementation class MyPointServlet
 */
@WebServlet("/mypoint")
public class MyPointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPointServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mId = request.getParameter("mid");
		
		RequestDispatcher view = null;
		try {
			Member member = new MemberService().myPoint(mId);
			System.out.println("myPoint : " + member);
			
			if(member != null){
				view = request.getRequestDispatcher("views/member/myPointView.jsp");
				request.setAttribute("member", member);
				view.forward(request, response);
			}else{
				view = request.getRequestDispatcher("views/member/memberError.jsp");
				request.setAttribute("member", mId + "에 대한 조회실패!");
				view.forward(request, response);
			}
		} catch (Exception e) {
			view = request.getRequestDispatcher("views/member/memberError.jsp");
			request.setAttribute("member", e.getMessage());
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
