package semi.member.management.controller;
 import java.io.IOException;
import java.io.PrintWriter;
 import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 import semi.member.exception.MemberException;
import semi.member.model.service.MemberService;
 /**
 * Servlet implementation class Member09DeleteServlet
 */
@WebServlet("/mmdelete")
public class ManagerMemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerMemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
 	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//관리자 - 회원 삭제용 컨트롤러
		System.out.println("ManagerMemberDeleteServlet 서블릿 실행.");
		response.setContentType("text/html; charset=utf-8");
		String mId1 = (String)request.getParameter("mid");
		int result = 0;
		MemberService mservice = new MemberService();
		PrintWriter out = response.getWriter();
		try {
			result = mservice.deleteMember(mId1);
			out.print(result);
			out.flush();
			
			
		} catch (MemberException e) {
			e.printStackTrace();
			e.getMessage();
		}finally{
		
			out.close();
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