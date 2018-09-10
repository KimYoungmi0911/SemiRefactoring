package semi.qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.qna.exception.QnaException;
import semi.qna.model.service.QnaService;
import semi.qna.model.vo.QnaBoard;

/**
 * Servlet implementation class QnaReplyUpdateServlet
 */
@WebServlet("/qreplyup")
public class QnaReplyUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaReplyUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 댓글 수정 처리용 컨트롤러
				//request.setCharacterEncoding("utf-8");
				
				String QnaboardTitle = request.getParameter("qtitle");
				String QnaboardContent = request.getParameter("qcontent");
				int qnaNo = Integer.parseInt(request.getParameter("no"));
				int currentPage = Integer.parseInt(request.getParameter("page"));
				
				QnaBoard qna = new QnaBoard();
				qna.setQ_no(qnaNo);
				qna.setQ_title(QnaboardTitle);
				qna.setQ_content(QnaboardContent);
				
				RequestDispatcher view = null;
				try {
					if(new QnaService().updateReply(qna) > 0){
						response.sendRedirect("/semi/qlist?page=" + currentPage);
					}else{
						view = request.getRequestDispatcher("views/qna/qnaError.jsp");
						request.setAttribute("message", qnaNo + "번 댓글 수정 실패!");
						view.forward(request, response);
					}
					
				} catch (QnaException e) {
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
