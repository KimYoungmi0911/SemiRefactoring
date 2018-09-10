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
 * Servlet implementation class RetalEnrollServlet
 */
@WebServlet("/renroll")
public class RetalEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RetalEnrollServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("RentalEnrollServlet 서블릿 실행.");
		//관리자가 대여등록시 코드 추가해서 대여번호 따로 생성
		//현장대여는 당일만 가능, 예약불가

		// 한글 문자 인코딩 처리
		request.setCharacterEncoding("utf-8");
		int result = 0;

		String json1 = request.getParameter("jsonStr");
		Rental r = null;

		PrintWriter out = response.getWriter();

		try {

			JSONParser jsonParser = new JSONParser();
			JSONObject job = (JSONObject) jsonParser.parse(json1);
	
			//String rNum = (String)job.get("rnum");
			int pNum = Integer.parseInt((String)job.get("pnum"));
			String mId = (String)job.get("mid");
			int rPrice = Integer.parseInt((String)job.get("rprice"));
			String rDate = (String)job.get("rdate");	
			String rStartDate = (String)job.get("rstartdate");
			String rReturnDate = (String)job.get("rreturndate");
			String pState = (String)job.get("pstate");
			int rCount = Integer.parseInt((String)job.get("rcount"));
			

			r = new Rental();
			
			//r.setRentalNum(rNum);
			r.setpNo(pNum);
			r.setmId(mId);
			r.setrPrice(rPrice);
			r.setrDate(rDate);
			r.setrStartDate(rStartDate);
			r.setrReturnDate(rReturnDate);
			r.setpState(pState);
			r.setpCount(rCount);
			
			// 전송 값 저장 확인
			System.out.println(r.toString());
			
			//대여번호 생성
			Rental r1 = new RentalService().insertRentalNumber(r);
			
			result = new RentalService().insertRental(r);

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
