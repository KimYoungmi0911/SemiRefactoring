package semi.products.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import semi.products.model.service.ProductsService;
import semi.products.model.vo.Product;

/**
 * Servlet implementation class ProductRentalTop3Servlet
 */
@WebServlet("/prtop3")
public class ProductRentalTop3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductRentalTop3Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ArrayList<Product> list = null;
		PrintWriter out = response.getWriter();
		try {
			JSONObject json = new JSONObject();
			JSONArray jarr = new JSONArray();
			//list = new ProductsService().selectTop3(mId);
			
			for(Product p : list){
				JSONObject job = new JSONObject();
				job.put("rpno", p.getP_no());
				job.put("rprice", p.getP_price());
				job.put("rpcount", p.getP_count());
				job.put("rpname", URLEncoder.encode(p.getP_name(), "UTF-8"));
			
				jarr.add(job);
			}
			json.put("list", jarr);
			System.out.println(" top3 서블릿 : " + json.toJSONString() + ", size : " + json.size());
			response.setContentType("application/json; charset=utf-8");
			out.write(json.toJSONString());
		} catch (Exception e) {
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
