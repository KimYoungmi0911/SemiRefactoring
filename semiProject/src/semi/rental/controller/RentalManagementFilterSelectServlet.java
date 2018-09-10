package semi.rental.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import semi.rental.exception.RentalException;
import semi.rental.model.service.RentalService;
import semi.rental.model.vo.Rental;

/**
 * Servlet implementation class RentalManagementFilterSelectServlet
 */
@WebServlet("/rmfselect")
public class RentalManagementFilterSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RentalManagementFilterSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//대여목록 필터 선택 조회용 컨트롤러
		response.setContentType("text/html; charset=utf-8");
		System.out.println("RentalManagementFilterSelectServlet 실행");
	
		HashMap filter = new HashMap();		
		filter.put("value", request.getParameter("value"));
		filter.put("mainFilter", request.getParameter("mainFilter"));
		filter.put("fVal", request.getParameter("fVal"));
		filter.put("sFilter", request.getParameter("sFilter"));
		
		RentalService rservice = new RentalService();
		JSONObject json = null;
		int result = 0;
	
		try{
			ArrayList<Rental> list = rservice.rFilterSelectList(filter);
			json = new JSONObject();
			JSONArray jarr = new JSONArray();
			
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
			System.out.println("jarr size : " + jarr.size());
			if(jarr.size() > 0){
				result = 1;
				json.put("list", jarr);
				json.put("result", result);
			}else{
				json.put("result", result);
			}
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
