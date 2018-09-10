package semi.products.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import semi.products.model.service.ProductsService;
import semi.products.model.vo.Product;

/**
 * Servlet implementation class ProductManagementLocalListServlet
 */
@WebServlet("/locallist")
public class ProductManagementLocalListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductManagementLocalListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.setContentType("text/html; charset=utf-8");
		
		JSONObject json = null;
		
		String local = request.getParameter("local");
		
		ProductsService proService = new ProductsService();
		
		try {
			ArrayList<Product> list = proService.localSelectList(local);
			
			json = new JSONObject();
			JSONArray jarr = new JSONArray();
			
			for(Product p : list) {
				JSONObject job = new JSONObject();
				
				job.put("local", URLEncoder.encode(p.getP_local(), "UTF-8"));
				
				if(job.size() > 0) {
					jarr.add(job);
				}
			}
			
			json.put("list", jarr);
			
		} catch (Exception e) {
			e.printStackTrace();
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
