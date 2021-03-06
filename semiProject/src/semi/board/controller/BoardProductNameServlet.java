package semi.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import semi.board.exception.BoardException;
import semi.board.model.service.BoardService;
import semi.rental.model.vo.Rental;

/**
 * Servlet implementation class BoardProductNameServlet
 */
@WebServlet("/bname")
public class BoardProductNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardProductNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Rental ren = new Rental();
		BoardService bservice = new BoardService();
		
		//세션저장하고 넘겨주기
		
		HttpSession session = request.getSession();
		String mId = (String)session.getAttribute("m_Id");

		System.out.println(mId);
		
		response.setContentType("text/html; charset=utf-8");

	      ArrayList<Rental> list = null;
		//ArrayList list = null;
	      JSONObject json = null;
	      JSONArray jarr = null;
	      PrintWriter out = response.getWriter();

	      try {
	    	 
	        
	         list = new BoardService().selectList2(mId);
	         json = new JSONObject();
	         jarr = new JSONArray();
	         

	         for (Rental r : list) {
	            
	            JSONObject job = new JSONObject();
	            job.put("pno", r.getpNo());
	            job.put("pname", URLEncoder.encode(r.getmId(), "UTF-8"));

	            if (job.size() > 0) {
	               jarr.add(job);
	            }
	         }
	         
	         json.put("list", jarr);

	         response.setContentType("application/json; charset=utf-8");
	        // System.out.println(json.toString());
	         out.print(json.toJSONString());

	      } catch (BoardException e) {
	         e.printStackTrace();
	         e.getMessage();
	      } finally {
	         out.flush();
	         out.close();
	      }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
