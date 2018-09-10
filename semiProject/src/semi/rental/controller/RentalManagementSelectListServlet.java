package semi.rental.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import semi.member.model.vo.Member;
import semi.rental.exception.RentalException;
import semi.rental.model.service.RentalService;
import semi.rental.model.vo.Rental;

/**
 * Servlet implementation class RentalManagementSelectListServlet
 */
@WebServlet("/rmslist")
public class RentalManagementSelectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RentalManagementSelectListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//대여목록 전체조회 컨트롤러	
		response.setContentType("text/html; charset=utf-8");
		System.out.println("RentalManagementSelectListServlet 실행.");
		// 페이지 값 처리용 변수
		int currentPage = 1;
		// 한 페이지당 출력할 목록 갯수
		int limit = 10;
		
		if(request.getParameter("page") != null){
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		ArrayList<Rental> list = null;
		RentalService rservice = new RentalService();
		JSONObject json = null;
		JSONArray jarr = null;
		
		int listCount;
		int maxPage;
		int startPage;
		int endPage;
		
		try{
			listCount = rservice.rGetListCount();
			list = rservice.rSelectList(currentPage, limit);
			maxPage = (int) ((double) listCount / limit + 0.9);
			startPage = (((int) ((double) currentPage / limit + 0.9)) - 1) * limit + 1;
			endPage = startPage + limit - 1;
			
			if (maxPage < endPage)
				endPage = maxPage;
			
			json = new JSONObject();
			jarr = new JSONArray();
			
			for(Rental r : list){
				JSONObject job = new JSONObject();
				job.put("rno", r.getrNo());
				job.put("pno", r.getpNo());
				job.put("mid", URLEncoder.encode(r.getmId(), "UTF-8"));
				job.put("pcount", r.getpCount());
				job.put("rprice", r.getrPrice());
				job.put("rdate", URLEncoder.encode(r.getrDate(), "UTF-8"));
				job.put("rstartdate", URLEncoder.encode(r.getrStartDate(), "UTF-8"));
				job.put("rreturndate", URLEncoder.encode(r.getrReturnDate(), "UTF-8"));
				job.put("rlastdate",URLEncoder.encode(r.getRReturnLastDate(), "UTF-8"));
				job.put("rbookdate", URLEncoder.encode(r.getrBookingDate(), "UTF-8"));
				job.put("pstate", URLEncoder.encode(r.getpState(), "UTF-8"));
				//job.put("pname", URLEncoder.encode(r.getpName(), "UTF-8"));
		
				if(job.size() > 0){
					jarr.add(job);
				} 
			}
			
			json.put("list", jarr);
			
			json.put("currentPage", currentPage);
			json.put("maxPage", maxPage);
			json.put("startPage", startPage);
			json.put("endPage", endPage);
			json.put("listCount", listCount);

		} catch(RentalException e) {
			e.printStackTrace();
			e.getMessage();
		}
			
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(json.toJSONString());
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
