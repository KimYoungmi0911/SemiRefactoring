package semi.member.management.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import semi.member.exception.MemberException;
import semi.member.model.service.MemberService;
import semi.member.model.vo.Member;


/**
 * Servlet implementation class Member09AllSearchServlet
 */
@WebServlet("/mmlist")
public class ManagerMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerMemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		System.out.println("ManagerMemberListServlet 실행.");
		// 페이지 값 처리용 변수
		//int currentPage = Integer.parseInt(request.getParameter("page"));
		int currentPage = 1;
		// 한 페이지당 출력할 목록 갯수
		int limit = 10;
		
		if(request.getParameter("page") != null){
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		ArrayList<Member> list = null;
		MemberService mservice = new MemberService();
		JSONObject json = null;
		JSONArray jarr = null;
		
		int listCount;
		int maxPage;
		int startPage;
		int endPage;
		
		try{
			listCount = mservice.mGetListCount();
			list = mservice.mSelectList(currentPage, limit);
			maxPage = (int) ((double) listCount / limit + 0.9);
			startPage = (((int) ((double) currentPage / limit + 0.9)) - 1) * limit + 1;
			endPage = startPage + limit - 1;
			
			if (maxPage < endPage)
				endPage = maxPage;
			
			json = new JSONObject();
			jarr = new JSONArray();
			
			for(Member m : list){
				JSONObject job = new JSONObject();
				job.put("mname",URLEncoder.encode(m.getmName(), "UTF-8"));
				job.put("mid", URLEncoder.encode(m.getmId(), "UTF-8"));
				job.put("mnick", URLEncoder.encode(m.getmNickname(), "UTF-8"));
				job.put("msno", URLEncoder.encode(m.getmSno(), "UTF-8"));
				job.put("mgender", URLEncoder.encode(m.getmGender(), "UTF-8"));
				job.put("mphone", URLEncoder.encode(m.getmPhone(), "UTF-8"));
				job.put("memail", URLEncoder.encode(m.getmEmail(), "UTF-8"));
				job.put("maddress", URLEncoder.encode(m.getmAddress(), "UTF-8"));
				job.put("mpoint", m.getmPoint());
				job.put("mpwd", URLEncoder.encode(m.getmPassword(), "UTF-8"));
			
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

		} catch(MemberException e) {
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
