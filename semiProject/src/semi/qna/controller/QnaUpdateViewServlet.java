package semi.qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.notice.model.service.NoticeGongService;
import semi.notice.model.vo.Notice;
import semi.qna.model.service.QnaService;
import semi.qna.model.vo.QnaBoard;

/**
 * Servlet implementation class QnaUpdateViewServlet
 */
@WebServlet("/qupview")
public class QnaUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시글 수정페이지 출력 처리용
				response.setContentType("text/html; charset=utf-8");
				
				int qnaNo = Integer.parseInt(request.getParameter("no"));
				int currentPage = Integer.parseInt(request.getParameter("page"));
				
				
				RequestDispatcher view =null;
				
				try {
					QnaBoard qna = new QnaService().selectNotice(qnaNo);
					
					if(qna != null){
						view = request.getRequestDispatcher("views/qna/qnaUpdateView.jsp");
						request.setAttribute("qna", qna);
						request.setAttribute("page", currentPage);
						view.forward(request, response);
						
					}else{
						view = request.getRequestDispatcher("views/qna/qnaError.jsp");
						request.setAttribute("message", "수정페이지 이동 실패!!!");
						view.forward(request, response);
					}
					
				} catch (Exception e) {
					view = request.getRequestDispatcher("views/qna/qnaError.jsp");
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
