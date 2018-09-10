package semi.member.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.member.model.service.MemberService;
import semi.review.model.vo.ReviewBoard;

/**
 * Servlet implementation class MyBoardServlet
 */
@WebServlet("/myboard")
public class MyBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyBoardServlet() {
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
			ArrayList<ReviewBoard> list = new MemberService().myboard(mId);
			System.out.println("myboard : " + list);
			
			if(list != null){
				view = request.getRequestDispatcher("views/member/myBoardView.jsp");
				request.setAttribute("list", list);
				view.forward(request, response);
			}else{
				view = request.getRequestDispatcher("views/member/memberError.jsp");
				request.setAttribute("message", mId + "의 게시글 조회 실패");
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
