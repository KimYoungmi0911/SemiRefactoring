package semi.member.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.products.model.service.ProductsService;
import semi.products.model.vo.Product;
import semi.rental.model.service.RentalService;
import semi.rental.model.vo.Rental;

/**
 * Servlet implementation class MyRentServlet
 */
@WebServlet("/myrent")
public class MyRentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyRentServlet() {
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
			ArrayList<Rental> list = new RentalService().myrent(mId);
			//Member member = new MemberService().myRent(mId);
			ArrayList<Product> list2 = new ProductsService().myrent(mId);
			System.out.println("myrent : " + list);
			System.out.println("product : " + list2);
			if(list != null && list2 != null){
				view = request.getRequestDispatcher("views/member/myRentView.jsp");
				request.setAttribute("list2", list2);
				request.setAttribute("list", list);
				view.forward(request, response);
			}else{
				view = request.getRequestDispatcher("views/member/memberError.jsp");
				request.setAttribute("message", mId + "에 대한 조회실패!");
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
