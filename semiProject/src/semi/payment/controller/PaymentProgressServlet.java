package semi.payment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.payment.model.service.PaymentService;
import semi.payment.model.vo.Payment;
import semi.rental.model.vo.Rental;

/**
 * Servlet implementation class PaymentProgressServlet
 */
@WebServlet("/pay")
public class PaymentProgressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentProgressServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paySelect = (String) request.getAttribute("payselect"); // 결제 방법
		String state = (String) request.getAttribute("state"); //대여 가능 상태
		Rental rental = (Rental) request.getAttribute("rental"); // DAO에서 받은 rental 값
		 
		Payment pay = new Payment();
		
		pay.setrNo(rental.getrNo());
		pay.setmId(rental.getmId());
		pay.setPayType(paySelect);
		pay.setPayPrice(rental.getrPrice());
		pay.setPayConfirm(state);
		
		
		response.setContentType("text/html; charset=utf-8");
		
		RequestDispatcher errorPage = null;
		
		try {
			if(new PaymentService().insertPay(pay) > 0) {
				response.sendRedirect("/semi/views/payment/paymentCompleteView.jsp");
			} else {
				errorPage = request.getRequestDispatcher("views/payment/paymentError.jsp");
				request.setAttribute("message", "결제실패!!!!111");
				errorPage.forward(request, response);
			}
		} catch (Exception e) {
			errorPage = request.getRequestDispatcher("views/payment/paymentError.jsp");
			request.setAttribute("message", e.getMessage());
			errorPage.forward(request, response);
		}
	}

}
