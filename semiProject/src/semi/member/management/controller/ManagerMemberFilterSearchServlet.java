package semi.member.management.controller;

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

import semi.member.exception.MemberException;
import semi.member.model.service.MemberService;
import semi.member.model.vo.Member;

/**
 * Servlet implementation class Member09SelectServlet
 */
@WebServlet("/mmselect")
public class ManagerMemberFilterSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerMemberFilterSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//필터 값으로 회원 조회용 컨트롤러
		response.setContentType("text/html; charset=utf-8");
		System.out.println("ManagerMemberFilterSearchServlet 서블릿 실행");
		
		String filter = request.getParameter("filter");
		String value = request.getParameter("value");
		
		ArrayList<Member> list = null;
		JSONObject json = null;
		JSONArray jarr = null;
		MemberService mservice = new MemberService();
		PrintWriter out = response.getWriter();
		
		try {
			list = mservice.mFilterSearch(filter, value);
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
			
		} catch (MemberException e) {
			e.printStackTrace();
			e.getMessage();
		} 
		
		response.setContentType("application/json; charset=utf-8");
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
