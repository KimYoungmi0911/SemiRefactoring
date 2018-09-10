package semi.notice.controller;

import java.io.File;
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
 * Servlet implementation class NoticeGongDeleteServlet
 */
@WebServlet("/ngdelete")
public class NoticeGongDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeGongDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시글 삭제 처리용 컨트롤러
		int noticeNo = Integer.parseInt(request.getParameter("no"));
		
		RequestDispatcher view = null;
		try {
			NoticeGongService ngservice = new NoticeGongService();
			Notice notice = ngservice.selectNotice(noticeNo);
			
			if(ngservice.deleteNotice(noticeNo) > 0){
				//삭제 성공시 첨부파일 있을 경우 ngupfiles 해당 파일 삭제
				if(notice.getN_file1() != null && notice.getN_file2() != null){
					String savePath = request.getSession()
							.getServletContext().getRealPath("ngupfiles");
					File removeFile = new File(savePath + "\\" + notice.getN_file1());
					File removeFile2 = new File(savePath + "\\" + notice.getN_file2());
					
					removeFile.delete();
					removeFile2.delete();
				}
				
				response.sendRedirect("/semi/nglist");
			}else{
				view = request.getRequestDispatcher("views/notice/noticeGongError.jsp");
				request.setAttribute("message", notice + "번글 삭제 실패!");
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
