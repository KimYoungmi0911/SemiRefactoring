package semi.rental.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import semi.rental.exception.RentalException;
import semi.rental.model.service.RentalService;
import semi.rental.model.vo.Rental;

/**
 * Servlet implementation class RentalManagementInsertServlet
 */
@WebServlet("/rmrinsert")
public class RentalManagementInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RentalManagementInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 관리자용 대여등록 컨트롤러
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		int result = 0;
		String json1 = request.getParameter("jsonStr");
		Rental r = null;

		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject job = (JSONObject) jsonParser.parse(json1);
			
			r = new Rental();
			r.setrNo((String)job.get("rno"));
			r.setpNo(Integer.parseInt((String)job.get("pno")));
			r.setmId((String)job.get("mid"));
			r.setpCount(Integer.parseInt((String)job.get("pcount")));
			r.setrPrice(Integer.parseInt((String)job.get("rprice")));
			r.setrReturnDate((String)job.get("rreturn"));
	
			// 전송 값 저장 확인
			System.out.println(r.toString());

			result = new RentalService().mInsertRental(r);

			if (result <= 0)
				throw new RentalException("서블릿 : 등록실패");

			out.print(result);
			out.flush();

		} catch (RentalException e) {
			e.printStackTrace();
			e.getMessage();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
