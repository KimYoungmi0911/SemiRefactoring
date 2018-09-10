package semi.rental.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.rental.exception.RentalException;
import semi.rental.model.service.RentalService;

/**
 * Servlet implementation class RentalManagementUpdateReturnDateServlet
 */
@WebServlet("/rmupreturn")
public class RentalManagementUpdateReturnDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RentalManagementUpdateReturnDateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//대여기간 연장용 컨트롤러
		response.setContentType("text/html; charset=utf-8");
		String rNo = (String)request.getParameter("rno");
		String returnDate = (String)request.getParameter("returndate");
		PrintWriter out = response.getWriter();
		int result = 0;
		
		System.out.println("return : " + returnDate);
		try {
			result = new RentalService().updateReturnDate(rNo, returnDate);
			
			out.print(result);
			
		} catch (RentalException e) {
			e.printStackTrace();
			e.getMessage();
		} finally {
			out.flush();
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
