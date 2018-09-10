package semi.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.notice.model.service.NoticeGongService;
import semi.notice.model.vo.Notice;


/**
 * Servlet implementation class NoticeGongListServlet
 */
@WebServlet("/nglist")
public class NoticeGongListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeGongListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지사항 전체 글 조회 컨트롤러
		
		response.setContentType("text/html; charset=utf-8");
		
		//페이지 값 처리용 변수
		int currentPage = 1;
		//한 페이지당 출력할 목록 갯수
		int limit =10;
		
		//전달된 페이지값 추출
		if(request.getParameter("page") != null){
			currentPage = Integer.parseInt(request.getParameter("page"));
		}		
		NoticeGongService ngservice = new NoticeGongService();	
		
		
		RequestDispatcher view = null;
		
		try {
			 //전체 목록 갯수 조회함
			int listCount = ngservice.getListConut();
			//해당 페이지에 보이게할 목록 조회
			ArrayList<Notice> list = ngservice.selectList(currentPage, limit);
			
			//총 페이지수 계산
			//목록이 최소 1개일 때 1페이지로 처리하기
			//위해서 0.9를 더하기 함
			
			int maxPage = (int)((double)listCount / limit + 0.9);
			//현재 페이지에 보여줄 시작 페이지 수
			//1, 11, 21...
			//현재 페이지가 13페이지면 시작페이지는 11페이지가 됨.
			int startPage = (((int)((double)currentPage / limit 
					+ 0.9)) - 1) * limit + 1;
			//만약, 목록 아래에 보여질 페이지 갯수가 10개이면
			//끝페이지수는 20페이지가 되어야 함
			int endPage = startPage + limit - 1;
			if(maxPage < endPage)
				endPage = maxPage;
			
			if(list.size() > 0){
				
				/*JSONObject job = new JSONObject();
				job.put("list", list);
				job.put("currentPage", currentPage);
				job.put("maxPage", maxPage);
				job.put("startPage", startPage);
				job.put("endPage", endPage);
				job.put("listCount", listCount);
				
				System.out.println(job.toJSONString());
				
				response.setContentType("application/json; charset=utf-8");
				PrintWriter out = response.getWriter();
				
				out.println();
				out.close();*/
				
				
			
				HttpSession session = request.getSession();
				
				view = request.getRequestDispatcher(
						"views/notice/noticeGongListView.jsp");
				request.setAttribute("list", list);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("maxPage", maxPage);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("listCount", listCount);
				//session.setAttribute("a_Id", aId);
				//request.setAttribute("a_Id", aId);
				
				view.forward(request, response);
			}else{
				view = request.getRequestDispatcher(
						"views/notice/noticeGognError.jsp");
				request.setAttribute("message", "게시글이 없습니다!");
				view.forward(request, response);
			}
			
			
		} catch (Exception e) {
			view = request.getRequestDispatcher(
					"views/notice/noticeGongError.jsp");
			System.out.println("catch");
			request.setAttribute("message", e.getMessage());
			view.forward(request, response);
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
