package semi.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.admin.model.service.AdminService;

/**
 * Servlet implementation class AdminDeleteServlet
 */
@WebServlet("/adelete")
public class AdminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String aId = request.getParameter("aid");
	
	try {
		if(new AdminService().deleteAdmin(aId) > 0){
			response.sendRedirect("/semi/alogout");
		}else{
			RequestDispatcher errorPage = request.getRequestDispatcher("views/admin/adminError.jsp");
			request.setAttribute("message", "관리자 정보 삭제 실패!");
			errorPage.forward(request, response);
		}
	} catch (Exception e) {
		RequestDispatcher errorPage = request.getRequestDispatcher("views/admin/adminError.jsp");
		request.setAttribute("message", e.getMessage());
		errorPage.forward(request, response);
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
