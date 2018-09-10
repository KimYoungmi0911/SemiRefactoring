package semi.member.management.controller;

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

import semi.member.exception.MemberException;
import semi.member.model.service.MemberService;
import semi.member.model.vo.Member;


/**
 * Servlet implementation class Member09EditServlet
 */
@WebServlet("/mmedit")
public class ManagerMemberEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerMemberEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ManagerMemberEditServlet 서블릿 실행.");
		//관리자 회원 수정용
		//한글 문자 인코딩 처리
		request.setCharacterEncoding("utf-8");
		int result = 0;
		
		String json1 = request.getParameter("jsonStr");
		Member m = null;
		PrintWriter out = response.getWriter();
		
		try {
			
			JSONParser jsonParser = new JSONParser();
			JSONObject job = (JSONObject)jsonParser.parse(json1);
			String mId = (String)job.get("mid");
			String mPwd = (String)job.get("mpwd");
			String mName = (String)job.get("mname");
			String mNick = (String)job.get("mnick");
			String mPhone = (String)job.get("mphone");
			String mAddress = (String)job.get("maddress");
			String mEmail = (String)job.get("memail");
			int mPoint = Integer.parseInt((String)job.get("mpoint"));
			String msno = (String)job.get("msno");
			String mGender = (String)job.get("mgender");
			
			m = new Member();
			m.setmId(mId);
			m.setmPassword(mPwd);
			m.setmName(mName);
			m.setmNickname(mNick);
			m.setmPhone(mPhone);
			m.setmAddress(mAddress);
			m.setmEmail(mEmail);
			m.setmPoint(mPoint);
			m.setmSno(msno);
			m.setmGender(mGender);
			
			//전송 값 저장 확인
			System.out.println(m.toString());

			result = new MemberService().mEditMember(m);
			
			if(result <= 0)
				throw new MemberException("서블릿 : 등록실패");
			
			out.print(result);
			out.flush();

		} catch (MemberException e) {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
