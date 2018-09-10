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
 * Servlet implementation class QnaReplyServlet
 */
@WebServlet("/qreply")
public class QnaReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 댓글달기 처리용 컨트롤러
				request.setCharacterEncoding("utf-8");
				
				
				int currentPage = Integer.parseInt(
						request.getParameter("page"));
				int qnaNo = Integer.parseInt(request.getParameter("no"));
				System.out.println("qnano"+qnaNo);
				String QnaboardTitle = request.getParameter("qtitle");
				String QnaboardWriter = request.getParameter("qwriter");
				String QnaboardContent = request.getParameter("qcontent");
				
				QnaService qservice = new QnaService();
				RequestDispatcher view = null;
				
				try {
					//원글 조회
					QnaBoard originBoard = qservice.selectNotice(qnaNo);
					//댓글 객체 생성
					QnaBoard replyBoard = new QnaBoard();
					replyBoard.setQ_content(QnaboardContent);
					replyBoard.setQ_title(QnaboardTitle);
					replyBoard.setM_id(QnaboardWriter);
					replyBoard.setQ_level(originBoard.getQ_level() + 1);
					replyBoard.setQ_ref(originBoard.getQ_ref());
					//댓글의 댓글일 때
					if(replyBoard.getQ_level() == 2)
						replyBoard.setQ_reply_ref(originBoard.getQ_no());
					//댓글 순번을 1로 처리함
					replyBoard.setQ_reply_seq(1);
					
					
					
					//같은 레벨의 기존 댓글의 seq 값을 모두 1증가처리함
					qservice.updateReplySeq(replyBoard);
					
					//댓글 등록 처리
					int result = qservice.insertReply(replyBoard);
					
					if(result > 0){
						response.sendRedirect(
							"/semi/qlist?page=" + currentPage);
					}else{
						view = request.getRequestDispatcher(
							"views/qna/qnaError.jsp");
						request.setAttribute("message", 
							qnaNo + "번글의 댓글 달기 실패!");
						view.forward(request, response);
					}
					
				} catch (QnaException e) {
					view = request.getRequestDispatcher(
							"views/qna/qnaError.jsp");
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
