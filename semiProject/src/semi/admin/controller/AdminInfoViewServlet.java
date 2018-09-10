package semi.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.admin.model.service.AdminService;
import semi.admin.model.vo.Admin;

/**
 * Servlet implementation class AdminInfoViewServlet
 */
@WebServlet("/admininfo")
public class AdminInfoViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminInfoViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aId = request.getParameter("aid");
		
		RequestDispatcher view = null;
		try {
			Admin admin = new AdminService().selectAdmin(aId);
			
			System.out.println("admininfo : " + admin);
			
			if(admin != null){
				view = request.getRequestDispatcher("views/admin/adminInfoView.jsp");
				request.setAttribute("admin", admin);
				view.forward(request, response);
			}else{
				view = request.getRequestDispatcher("views/admin/adminError.jsp");
				request.setAttribute("message", aId + "에 대한 조회 실패!");
				view.forward(request, response);
			}
		} catch (Exception e) {
			view = request.getRequestDispatcher("views/admin/adminError.jsp");
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
