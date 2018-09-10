package semi.rentalList.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.rentalList.exception.RentalListException;
import semi.rentalList.model.service.RentalListService;
import semi.rentalList.model.vo.RentalList;



/**
 * Servlet implementation class RentalListInsertServlet
 */
@WebServlet("/rlist")
public class RentalListInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RentalListInsertServlet() {
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
		String mId = (String) request.getParameter("loginId"); // 회원 아이디
		String paySelect = (String) request.getParameter("payselect"); // 결제 방법
		String pricesum = (String) request.getParameter("pricesum"); // 총 금액
		
		String pName = (String) request.getParameter("pname"); // 물품번호
		String startDay = (String) request.getParameter("startday"); // 대여실행일
		String endDay = (String) request.getParameter("endday"); //반납신청일.
		String imagee = (String) request.getParameter("imagee");
		System.out.println("dlalwl : " + imagee);
		
		String state = ""; //승인여부
		
		if(paySelect.equals("money")) {
			state = "입금대기중";
		} else {
			state = "결제완료";
		}
		
		RentalList rlist = new RentalList();
		
		rlist.setmId(mId);
		
		
		
		System.out.println(mId + "," + paySelect + "," + pricesum + "," + "," + pName + "," + startDay + "," + endDay);
		
		RequestDispatcher view = null;
		
		try {
			String rentalNo = new RentalListService().insertRentalList(rlist);
			
			if(rentalNo != null) {
				view = request.getRequestDispatcher("views/payment/paymentCompleteView.jsp");
				
				rlist.setrNo(rentalNo);
				
				request.setAttribute("payselect", paySelect);
				request.setAttribute("pName", pName);
				request.setAttribute("startday", startDay);
				request.setAttribute("endday", endDay);
				request.setAttribute("state", state);
				request.setAttribute("rlist", rlist);
				request.setAttribute("imagee", imagee);
				request.setAttribute("pricesum", pricesum);
				
				view.forward(request, response);
			} else {
				view = request.getRequestDispatcher("views/payment/paymentError.jsp");
				request.setAttribute("message", "결제 목록 등록 실패");
				view.forward(request, response);
			}
		} catch (RentalListException e) {
			view = request.getRequestDispatcher("views/payment/paymentError.jsp");
			request.setAttribute("message", e.getMessage());
			view.forward(request, response);
		}
	}

}
