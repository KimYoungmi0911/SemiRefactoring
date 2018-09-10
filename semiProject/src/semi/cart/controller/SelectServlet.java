package semi.cart.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/paymentgo")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		
		String pName = request.getParameter("productname");
		String pLocal = request.getParameter("selectpick");
		String startDay = request.getParameter("startday");
		String endDay = request.getParameter("endday");
		String rentalDate = request.getParameter("rentalday");
		String totelPrice = request.getParameter("totalpri");
		String imagecru = request.getParameter("imagecru");
		
		System.out.println("상품정보 : " + pName + "," + pLocal + "," + startDay + "," + "," + endDay + "," + rentalDate + "," + totelPrice);
		
		
		RequestDispatcher view = null;
		
		try {
			view = request.getRequestDispatcher("views/payment/paymentProgressView.jsp");
			
			request.setAttribute("pName", pName);
			request.setAttribute("pLocal", pLocal);
			request.setAttribute("startDay", startDay);
			request.setAttribute("endDay", endDay);
			request.setAttribute("rentalDate", rentalDate);
			request.setAttribute("totelPrice", totelPrice);
			request.setAttribute("imagecru", imagecru);
			
			view.forward(request, response);
		} catch (Exception e) {
			view = request.getRequestDispatcher("views/payment/paymentError.jsp");
			request.setAttribute("message", e.getMessage());
			view.forward(request, response);
		}
		
		
		
		
		/*CartService cservice = new CartService();
		
		RequestDispatcher view = null;
		try {
			ArrayList<Cart> list = new CartService().selectList();
			
			if(list.size() > 0) {
				view = request.getRequestDispatcher("views/cart/cart1.jsp");
				request.setAttribute("list", list);
				view.forward(request, response);
			}else {
				view = request.getRequestDispatcher("views/cart/cartError.jsp");
				request.setAttribute("message", "장바구니 목록이 없습니다.");
				view.forward(request, response);
			}
		} catch (Exception e) {
			view = request.getRequestDispatcher("views/cart/cartError.jsp");
			request.setAttribute("message", e.getMessage());
			view.forward(request, response);
		}*/
	
	
	
	
	
	
	
	}
		
		
		

		
		
		
		
		
		
		/*ArrayList<Cart> list = new CartService().
		
		JSONObject json = new JSONObject().selectCart();
		JSONArray jarr = new JSONArray();
		
		for(Cart c : list) {
			JSONObject job = new JSONObject();
			job.put("pimage", c.getP_Main_Image());
			job.put("pname", c.getP_Name());
			job.put("pprice", c.getP_Price());
			job.put("pstate", c.getP_State());
			
			jarr.add(job);
		}
		json.put("list", jarr);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(json.toJSONString());
		out.flush();
		out.close();
		Re
		*/
		
		
		/*RequestDispatcher view = null;
		view.forward("iews/cart1", arg1);*/


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
