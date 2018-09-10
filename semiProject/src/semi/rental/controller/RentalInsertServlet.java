package semi.rental.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.rental.model.service.RentalService;
import semi.rental.model.vo.Rental;
import semi.rentalList.model.vo.RentalList;

/**
 * Servlet implementation class RentalInsertServlet
 */
@WebServlet("/rinsert")
public class RentalInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RentalInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getAttribute("rlist"));
		System.out.println(request.getAttribute("productNo"));
		
		
		RentalList rlist = (RentalList) request.getAttribute("rlist");
		String paySelect = (String) request.getAttribute("payselect"); 
		int productNo = (int) request.getAttribute("productNo");
		String startDay = (String) request.getAttribute("startday");
		String endDay = (String) request.getAttribute("endday");
		String state = (String) request.getAttribute("state");
		
		Rental rental = new Rental();
		
		rental.setrNo(rlist.getrNo());
		rental.setmId(rlist.getmId());
		rental.setpNo(productNo);
		rental.setpCount(rlist.getrTotalCount());
		rental.setrPrice(rlist.getrTotalMoney());
		rental.setrStartDate(startDay);
		rental.setrReturnDate(endDay);
		rental.setpState("대여중");
		
		RequestDispatcher view = null;
		
		try {
			if(new RentalService().insertRental(rental) > 0) {
				view = request.getRequestDispatcher("pay");
				
				request.setAttribute("payselect", paySelect);
				request.setAttribute("state", state);
				request.setAttribute("rental", rental);
				
				view.forward(request, response);
			} else {
				view = request.getRequestDispatcher("views/payment/paymentError.jsp");
				request.setAttribute("message", "대여 정보 등록 실패");
				view.forward(request, response);
			}
		} catch (Exception e) {
			view = request.getRequestDispatcher("views/payment/paymentError.jsp");
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
