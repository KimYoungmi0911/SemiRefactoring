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
 * Servlet implementation class AdminInfoUpdateServlet
 */
@WebServlet("/aupdate")
public class AdminInfoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminInfoUpdateServlet() {
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
		//request.setCharacterEncoding("utf-8"); 		//필터 래퍼처리해야함
		
		Admin admin = new Admin();
		admin.setaId(request.getParameter("aid"));
		admin.setaPassword(request.getParameter("apassword"));
		admin.setaName(request.getParameter("aname"));
		admin.setaNickname(request.getParameter("anickname"));
		admin.setaGrade(request.getParameter("agrade"));
		admin.setlNo(Integer.parseInt(request.getParameter("lno")));
		
		try {
			if(new AdminService().updateAdmin(admin) > 0){
				response.sendRedirect("/semi/adminIndex.jsp");
			}else{
				RequestDispatcher errorPage = request.getRequestDispatcher("views/admin/adminError.jsp");
				request.setAttribute("message", "회원정보 수정 실패!");
				errorPage.forward(request, response);
			}
		} catch (Exception e) {
			RequestDispatcher errorPage = request.getRequestDispatcher("views/admin/adminError.jsp");
			request.setAttribute("message", e.getMessage());
			errorPage.forward(request, response);
		}
	}

}
