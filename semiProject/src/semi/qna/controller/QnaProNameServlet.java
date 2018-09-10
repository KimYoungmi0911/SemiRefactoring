package semi.qna.controller;

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

import semi.products.model.vo.Product;
import semi.qna.exception.QnaException;
import semi.qna.model.service.QnaService;

/**
 * Servlet implementation class QnaProNameServlet
 */
@WebServlet("/qname1")
public class QnaProNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QnaProNameServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");

		JSONObject json = null;
		JSONArray jarr = null;
		int qnaNo = Integer.parseInt(request.getParameter("qno"));
		PrintWriter out = response.getWriter();

		try {

			String pro = "";
			pro = new QnaService().selectNotice2(qnaNo);
			json = new JSONObject();
	        json.put("pname", URLEncoder.encode(pro, "UTF-8"));

			response.setContentType("application/json; charset=utf-8");
			System.out.println(json.toString());
			out.print(json.toJSONString());

		} catch (QnaException e) {
			e.printStackTrace();
			e.getMessage();
		} finally {
			out.flush();
			out.close();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
