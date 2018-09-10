package semi.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.admin.model.service.AdminService;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/alogin")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aId = request.getParameter("aid");
		
		String aPassword = request.getParameter("apassword");
		System.out.println(aId + ", " + aPassword);
		
		try {
			String aName = new AdminService().aloginCheck(aId, aPassword);
			
			if(aName != null){
				HttpSession session = request.getSession();	
				session.setAttribute("a_Name", aName);
				session.setAttribute("a_Id", aId);	
				
				response.sendRedirect("/semi/adminIndex.jsp");
			}else{
				RequestDispatcher view = request.getRequestDispatcher("views/admin/adminError.jsp");
				request.setAttribute("message", "아이디나 암호를 다시 확인하세요.");
				view.forward(request, response);
			
			}
		} catch (Exception e) {
			RequestDispatcher view = request.getRequestDispatcher("views/admin/adminError.jsp");
			request.setAttribute("message", e.getMessage());
			view.forward(request, response);
		}

	}

}
