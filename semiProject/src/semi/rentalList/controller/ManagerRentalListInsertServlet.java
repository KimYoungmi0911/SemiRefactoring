package semi.rentalList.controller;

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

import semi.rentalList.exception.RentalListException;
import semi.rentalList.model.service.RentalListService;
import semi.rentalList.model.vo.RentalList;



/**
 * Servlet implementation class RentalListInsertServlet
 */
@WebServlet("/mrlinsert")
public class ManagerRentalListInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerRentalListInsertServlet() {
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
		//관리자용 대여목록 생성
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String json1 = request.getParameter("jsonStr");
		RentalList rlist = new RentalList();
		
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject job = (JSONObject) jsonParser.parse(json1);
			
			rlist.setmId((String)job.get("mid"));
			rlist.setrTotalMoney(Integer.parseInt((String)job.get("rprice")));
			rlist.setrTotalCount(Integer.parseInt((String)job.get("pcount")));
			
			String rentalNo = new RentalListService().minsertRentalList(rlist);
			
			if(rentalNo != null) {
				out.print(rentalNo);
			}

		} catch (RentalListException e) {
			e.printStackTrace();
			e.getMessage();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}

}
