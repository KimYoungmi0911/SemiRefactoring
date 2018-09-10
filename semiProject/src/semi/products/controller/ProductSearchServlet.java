/*package semi.products.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.products.model.service.ProductsService;
import semi.products.model.vo.Product;

*//**
 * Servlet implementation class ProductSearchServlet
 *//*
@WebServlet("/psearch")
public class ProductSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public ProductSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String keyword = "";
		String localselect = "";
		if(request.getParameter("keyword") != null){
			keyword = request.getParameter("keyword");
		}else{
			keyword = "no1";
		}
		if(request.getParameter("localselect") != null){
			localselect = request.getParameter("localselect");
		}else{
			localselect = "no2";
		}

		RequestDispatcher view = null;
		
		
		
		if(!keyword.equals("no1") & localselect.equals("no2")){				//키워드만 선택
		try {
	
			ArrayList<Product> list = new ProductsService().selectProduct(keyword);

			if(list.size() > 0){
				view = request.getRequestDispatcher("views/product/productSearchList.jsp");
				request.setAttribute("productList", list);
				
				view.forward(request, response);
				System.out.println(keyword);
				System.out.println(list);
				
			}
			
		} catch (Exception e) {
			view = request.getRequestDispatcher("views/product/productsError.jsp");
			request.setAttribute("message", e.getMessage());
			view.forward(request, response);
		}
		
	
	}
		
		
		else if(keyword.equals("no1") & !localselect.equals("no2")){ 			//지역만 선택
		try {
			ArrayList<Product> localList = new ProductsService().selectLocal(localselect); 
			
			if(localList.size() > 0){
				view = request.getRequestDispatcher("views/product/productLocalSearchList.jsp");
				request.setAttribute("localList", localList);
				
				view.forward(request, response);
				System.out.println(localList);
			}
			
		} catch (Exception e) {
			view = request.getRequestDispatcher("views/product/productsError.jsp");
			request.setAttribute("message", e.getMessage());
			view.forward(request, response);
		}
	
	}
	
	
		
	}
	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}



*/

package semi.products.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.products.model.service.ProductsService;
import semi.products.model.vo.Product;

/**
 * Servlet implementation class ProductSearchServlet
 */
@WebServlet("/psearch")
public class ProductSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String keyword = "";
		String localselect = "";
		if(request.getParameter("keyword") != ""){
			keyword = request.getParameter("keyword");
		}else{
			keyword = "no1";
		}
		if(request.getParameter("localselect") != ""){
			localselect = request.getParameter("localselect");
		}else{
			localselect = "no2";
		}

		RequestDispatcher view = null;
		

				
		try {
	
			ArrayList<Product> list = new ProductsService().selectProduct(keyword, localselect);
			
			if(list.size() > 0){
				view = request.getRequestDispatcher("views/product/productSearchList.jsp");
				request.setAttribute("productList", list);
				
				view.forward(request, response);
				System.out.println(keyword);
				System.out.println(list.size());
				
			}
			
		} catch (Exception e) {
			view = request.getRequestDispatcher("views/product/productsError.jsp");
			request.setAttribute("message", e.getMessage());
			view.forward(request, response);
		}
		
	
	
		
		
		/*else if(keyword.equals("no1") & !localselect.equals("no2")){ 			//지역만 선택
		try {
			ArrayList<Product> localList = new ProductsService().selectLocal(localselect); 
			
			if(localList.size() > 0){
				view = request.getRequestDispatcher("views/product/productLocalSearchList.jsp");
				request.setAttribute("localList", localList);
				
				view.forward(request, response);
				System.out.println(localList);
			}
			
		} catch (Exception e) {
			view = request.getRequestDispatcher("views/product/productsError.jsp");
			request.setAttribute("message", e.getMessage());
			view.forward(request, response);
		}
	
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












