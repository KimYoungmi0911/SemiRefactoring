package semi.locationInfo.controller;

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

import semi.locationInfo.model.service.LocationInfoService;
import semi.locationInfo.model.vo.LocationInfo;

/**
 * Servlet implementation class LocationListAjaxServlet
 */
@WebServlet("/mappage")
public class LocationListAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocationListAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		System.out.println("ajax");
		
		JSONObject json = null;
		
		int currentPage = Integer.parseInt(request.getParameter("page"));
		
		int limit = 10;
		
		if(request.getParameter("page") != null){
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		LocationInfoService infoService = new LocationInfoService();
		
		try {
			int listCount = infoService.getListCount();
			
			ArrayList<LocationInfo> list = infoService.allSelectList(currentPage, limit);
			
			int maxPage = (int) Math.ceil(((double)listCount / limit));
			
			int startPage = (((int)((double)currentPage / limit + 0.9)) - 1) * limit + 1;

			int endPage = startPage + limit -1;
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			
			json = new JSONObject();
			JSONArray jarr = new JSONArray();
			
			for(LocationInfo l : list) {
				JSONObject job = new JSONObject();
				System.out.println(currentPage + "," + startPage + "," + maxPage + "," + endPage + ", " + listCount);
				job.put("local", URLEncoder.encode(l.getL_Local(), "UTF-8"));
				job.put("address", URLEncoder.encode(l.getL_Address(), "UTF-8"));
				job.put("name", URLEncoder.encode(l.getL_Name(), "UTF-8"));
				
				if(job.size() > 0) {
					jarr.add(job);
				}
			}
			
			json.put("list", jarr);
			
			json.put("currentPage", currentPage);
			json.put("maxPage", maxPage);
			json.put("startPage", startPage);
			json.put("endPage", endPage);
			
		} catch (Exception e) {
			e.printStackTrace();
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
