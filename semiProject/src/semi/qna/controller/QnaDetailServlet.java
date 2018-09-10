package semi.qna.controller;

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

import semi.notice.model.service.NoticeGongService;
import semi.notice.model.vo.Notice;
import semi.products.model.vo.Product;
import semi.qna.model.service.QnaService;
import semi.qna.model.vo.QnaBoard;

/**
 * Servlet implementation class QnaDetailServlet
 */
@WebServlet("/qdetail")
public class QnaDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		
		int qnaNo = Integer.parseInt(request.getParameter("no"));
		int currentPage = Integer.parseInt(request.getParameter("page"));

		QnaService qservice = new QnaService();
		
		RequestDispatcher view = null;
		try {
			//상세보기시 조회수 1 증가 처리
			qservice.addReadCount(qnaNo);
			//해당 게시글 조회
			QnaBoard qna = qservice.selectNotice(qnaNo);
		
		         
		         
			if(qna != null){
				view = request.getRequestDispatcher("views/qna/qnaDetailView.jsp");
				request.setAttribute("qna", qna);
				request.setAttribute("currentPage", currentPage);
				view.forward(request, response);
			}else{
				view = request.getRequestDispatcher("views/qna/qnaError.jsp");
				request.setAttribute("message", qna + "번 글 조회실패!!!!!!!");
				view.forward(request, response);
			}
			
		} catch (Exception e) {
			 e.printStackTrace();
	         e.getMessage();
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
