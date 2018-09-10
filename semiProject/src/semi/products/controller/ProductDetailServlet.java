package semi.products.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.products.exception.ProductsException;
import semi.products.model.service.ProductsService;
import semi.products.model.vo.Product;


/**
 * Servlet implementation class ProductDetailServlet
 */
@WebServlet("/pdetail.bd")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 21L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				// 게시글 상세보기 처리용 컨트롤러
				//내보내는 값에 한글이 있을 경우
				response.setContentType("text/html; charset=utf-8");
				System.out.println("servlet");
				
				String pName = request.getParameter("pname");
				
				ProductsService pservice = new ProductsService();
				
				RequestDispatcher view = null;
				try {
					//상세보기시 조회수 1 증가 처리
					//pservice.addReadCount(pno);
					//해당 게시글 조회해 옴
					Product product = pservice.selectProducts(pName);
					
					ArrayList<String> list = pservice.selectOffice(pName);
					
					if(product != null){
						view = request.getRequestDispatcher("views/product/productDetailView.jsp");
						request.setAttribute("product", product);
						request.setAttribute("list", list);
						view.forward(request, response);
					}else{
						view = request.getRequestDispatcher("views/product/productsError.jsp");
						request.setAttribute("message", pName + " 글 조회실패!");
						view.forward(request, response);
					}
					
				} catch (ProductsException e) {
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
