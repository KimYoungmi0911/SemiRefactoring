package semi.rental.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import semi.locationInfo.model.vo.LocationInfo;
import semi.rental.exception.RentalException;
import semi.rental.model.service.RentalService;

/**
 * Servlet implementation class RentalManagementTablePrintServlet
 */
@WebServlet("/rmtp")
public class RentalManagementTablePrintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RentalManagementTablePrintServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//뷰에 존재하지않는 대여 관련 정보 조회용 컨트롤러
		System.out.println("RentalManagementTablePrintServlet 실행");
		response.setContentType("text/html; charset=utf-8"); 
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		JSONObject json = null;
		PrintWriter out = response.getWriter();

		try {
			json = new RentalService().rentalTablePrintInfo(pno);

			response.setContentType("application/json; charset=utf-8");
			System.out.println(json.toJSONString());
			out.print(json.toJSONString());

		} catch (

		RentalException e) {
			e.printStackTrace();
			e.getMessage();
		} finally {
			out.flush();
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
