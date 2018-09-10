package semi.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.board.model.service.BoardService;
import semi.board.model.vo.Board;
import semi.notice.model.vo.Notice;
import semi.products.model.vo.Product;

/**
 * Servlet implementation class BoardDetailServlet
 */
@WebServlet("/bdetail")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//공지글 상세보기 처리용 컨트롤러
		response.setContentType("text/html; charset=utf-8");
		
		int boardNo = Integer.parseInt(request.getParameter("no"));
		int currentPage = Integer.parseInt(request.getParameter("page"));

		BoardService bservice = new BoardService();

		RequestDispatcher view = null;
		try {
			//상세보기시 조회수 1 증가 처리
			bservice.addReadCount(boardNo);
			//해당 게시글 조회
			Board board = bservice.selectNotice(boardNo);
		
			
			if(board != null){
				view = request.getRequestDispatcher("views/board/boardDetailView.jsp");
				request.setAttribute("board", board);
				request.setAttribute("currentPage", currentPage);
				view.forward(request, response);
			}else{
				view = request.getRequestDispatcher("views/board/boardError.jsp");
				request.setAttribute("message", boardNo + "번 글 조회실패!!!!!!!");
				view.forward(request, response);
			}
			
		} catch (Exception e) {
			view = request.getRequestDispatcher("views/board/boardError.jsp");
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
