package semi.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import semi.review.model.service.ReviewService;
import semi.review.model.vo.ReviewBoard;

/**
 * Servlet implementation class BoardTop3Servlet
 */
@WebServlet("/btop3")
public class BoardTop3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardTop3Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mId = request.getParameter("mid");
		System.out.println("mid : " + mId);
		
		ArrayList<ReviewBoard> list = new ReviewService().selectTop3(mId);
		
		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
		
	
		
		for(ReviewBoard rb : list){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String strdate = sdf.format(rb.getRbDate());
			JSONObject job = new JSONObject();
			job.put("rbno", rb.getRbNo());
			job.put("rbtitle", URLEncoder.encode(rb.getRbTitle(), "UTF-8"));
			job.put("rbdate",URLEncoder.encode(strdate, "UTF-8"));
			job.put("rbcount", rb.getRbCount()); 
			job.put("rbf1", URLEncoder.encode(rb.getRbFile1(), "UTF-8"));
			job.put("rbf2", URLEncoder.encode(rb.getRbFile2(), "UTF-8"));
			
			jarr.add(job);
		}
		// 없음 null
		json.put("list", jarr);
		System.out.println(json.toJSONString());
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(json.toJSONString());
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
