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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import semi.products.model.service.ProductsService;
import semi.products.model.vo.Product;

/**
 * Servlet implementation class ProductManagementSelectServlet
 */
@WebServlet("/mproselect")
public class ProductManagementSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductManagementSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		System.out.println("product");
		
		JSONObject json = null;
		
		int currentPage = Integer.parseInt(request.getParameter("page"));
		String filter = request.getParameter("filter");
		String searchTF = "";
		
		int limit = 10;
		
		if(request.getParameter("searchTF") != null){
			searchTF = request.getParameter("searchTF");
		}
		
		ProductsService proService = new ProductsService();
		
		try {
			int listCount = proService.mGetSelectListCount(filter, searchTF);
			
			ArrayList<Product> list = proService.mSelectList(currentPage, limit, filter, searchTF);
			
			int maxPage = (int) Math.ceil(((double)listCount / limit));
			
			int startPage = (((int)((double)currentPage / limit + 0.9)) - 1) * limit + 1;
			
			int endPage = startPage + limit - 1;
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			
			json = new JSONObject();
			JSONArray jarr = new JSONArray();
			
			for(Product p : list) {
				JSONObject job = new JSONObject();
				
				job.put("name", URLEncoder.encode(p.getP_name(), "UTF-8"));
				job.put("price", p.getP_price());
				job.put("count", p.getP_count());
				job.put("local", URLEncoder.encode(p.getP_local(), "UTF-8"));
				job.put("item", URLEncoder.encode(p.getP_item(), "UTF-8"));
				job.put("state", URLEncoder.encode(p.getP_state(), "UTF-8"));
				
				if(job.size() > 0) {
					jarr.add(job);
				}
			}
			
			json.put("list", jarr);
			
			json.put("currentPage", currentPage);
			json.put("maxPage", maxPage);
			json.put("startPage", startPage);
			json.put("endPage", endPage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(json.toJSONString());
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
