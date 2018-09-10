package semi.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import semi.board.exception.BoardException;
import semi.board.model.service.BoardService;
import semi.qna.exception.QnaException;
import semi.qna.model.service.QnaService;

/**
 * Servlet implementation class BoardProNameServlet
 */
@WebServlet("/bname1")
public class BoardProNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardProNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONObject json = null;
		JSONArray jarr = null;
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		PrintWriter out = response.getWriter();
		
		try {

			String pro = "";
			pro = new BoardService().selectNotice2(boardNo);
			json = new JSONObject();
	        json.put("pname", URLEncoder.encode(pro, "UTF-8"));

			response.setContentType("application/json; charset=utf-8");
			System.out.println(json.toString());
			out.print(json.toJSONString());

		} catch (BoardException e) {
			e.printStackTrace();
			e.getMessage();
		} finally {
			out.flush();
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
