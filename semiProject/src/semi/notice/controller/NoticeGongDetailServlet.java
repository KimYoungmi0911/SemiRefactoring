package semi.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.notice.model.service.NoticeGongService;
import semi.notice.model.vo.Notice;





/**
 * Servlet implementation class NoticeGongDetailServlet
 */
@WebServlet("/ngdetail")
public class NoticeGongDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeGongDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//공지글 상세보기 처리용 컨트롤러
		response.setContentType("text/html; charset=utf-8");
		
		int noticeNo = Integer.parseInt(request.getParameter("no"));
		int currentPage = Integer.parseInt(request.getParameter("page"));
		
		NoticeGongService ngservice = new NoticeGongService();
		
		
		RequestDispatcher view = null;
		try {
			//상세보기시 조회수 1 증가 처리
			ngservice.addReadCount(noticeNo);
			//해당 게시글 조회
			Notice notice = ngservice.selectNotice(noticeNo);
		
			
			if(notice != null){
				view = request.getRequestDispatcher("views/notice/noticeGongDetailView.jsp");
				request.setAttribute("notice", notice);
				request.setAttribute("currentPage", currentPage);
				view.forward(request, response);
			}else{
				view = request.getRequestDispatcher("views/notice/noticeGongError.jsp");
				request.setAttribute("message", noticeNo + "번 글 조회실패!!!!!!!");
				view.forward(request, response);
			}
			
		} catch (Exception e) {
			view = request.getRequestDispatcher("views/notice/noticeGongError.jsp");
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














