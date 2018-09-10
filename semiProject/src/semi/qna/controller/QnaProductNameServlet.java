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
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import semi.products.model.vo.Product;
import semi.qna.exception.QnaException;
import semi.qna.model.service.QnaService;

/**
 * Servlet implementation class QnaProductNameServlet
 */
@WebServlet("/qname")
public class QnaProductNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaProductNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product pro = new Product();
		QnaService qservice = new QnaService();
		
		response.setContentType("text/html; charset=utf-8");

	      ArrayList<Product> list = null;
	      JSONObject json = null;
	      JSONArray jarr = null;
	      PrintWriter out = response.getWriter();

	      try {
	         list = new QnaService().selectList1();
	         /*list = new QnaService().selectList2();*/
	         json = new JSONObject();
	         jarr = new JSONArray();
	                     
	         for (Product p : list) {
	            
	            JSONObject job = new JSONObject();
	            job.put("pname", URLEncoder.encode(p.getP_name(), "UTF-8"));
	            job.put("pno", p.getP_no());

	            if (job.size() > 0) {
	               jarr.add(job);
	            }
	         }
	         
	         json.put("list", jarr);

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
		/*RequestDispatcher view = null;
		try {
			ArrayList<Product> list = qservice.selectList1(pro);
			
			if(list.size() > 0){
				
					HttpSession session = request.getSession();
				
				view = request.getRequestDispatcher(
						"views/qna/qnaWriteView.jsp");
				request.setAttribute("list", list);
				view.forward(request, response);
			}else{
				view = request.getRequestDispatcher(
						"views/qna/qnaError.jsp");
				request.setAttribute("message", "값 못 보냄~~!!!");
				view.forward(request, response);
			}
		} catch (Exception e) {
			view = request.getRequestDispatcher(
					"views/qna/qnaError.jsp");
			request.setAttribute("message", e.getMessage());
			view.forward(request, response);
		}*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
