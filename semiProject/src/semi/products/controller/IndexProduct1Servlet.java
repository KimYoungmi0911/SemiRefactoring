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
 * Servlet implementation class IndexProduct1Servlet
 */
@WebServlet("/ipdetail1.bd")
public class IndexProduct1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexProduct1Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		System.out.println("servlet");
		
		String pName = request.getParameter("pname");
		ProductsService pservice = new ProductsService();
		
		RequestDispatcher view = null;
		
		try {
			Product product = pservice.selectProducts1(pName);
			
			ArrayList<String> list = pservice.selectOffice(pName);
			
			if(product != null){
				view = request.getRequestDispatcher("views/product/IndexProductDetailView.jsp");
				request.setAttribute("product1", product);
				request.setAttribute("list", list);
				view.forward(request, response);
			}else{
				view = request.getRequestDispatcher("views/product/productsError.jsp");
				request.setAttribute("message", pName + " 글 조회실패!");
				view.forward(request, response);
			}
		} catch (Exception e) {
			view = request.getRequestDispatcher("views/product/productsError.jsp");
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
