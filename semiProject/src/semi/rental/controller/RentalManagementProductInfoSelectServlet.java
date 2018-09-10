package semi.rental.controller;

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

import semi.products.model.vo.Product;
import semi.rental.exception.RentalException;
import semi.rental.model.service.RentalService;

/**
 * Servlet implementation class RentalManagementProductInfoSelectServlet
 */
@WebServlet("/rmpiselect")
public class RentalManagementProductInfoSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RentalManagementProductInfoSelectServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 물품 정보 출력용 컨트롤러
		System.out.println("RentalManagementProductInfoSelectServlet 실행");
		response.setContentType("text/html; charset=utf-8");

		//ArrayList<Product> list = null;
		JSONObject json = null;
		//JSONArray jarr = null;
		PrintWriter out = response.getWriter();
		Product p = new Product();

		try {
			String pName = (String)request.getParameter("pname");
			String lName = (String)request.getParameter("lname");
			//list = new RentalService().rentalProductNameList(localName);
			json = new JSONObject();
			//jarr = new JSONArray();
			
			p = new RentalService().rentalProductInfoList(pName, lName);
			
			json.put("pno", p.getP_no());
			json.put("pprice", p.getP_price());
			json.put("pcount", p.getP_count());
			json.put("pitem", URLEncoder.encode(p.getP_item(), "UTF-8"));
			json.put("pstate", URLEncoder.encode(p.getP_state(), "UTF-8"));

			/*for (Product p : list) {
				JSONObject job = new JSONObject();

				job.put("pno", p.getP_no());
				job.put("pname", URLEncoder.encode(p.getP_name(), "UTF-8"));
				job.put("pprice", p.getP_price());
				job.put("pcount", p.getP_count());
				// job.put("plocal", URLEncoder.encode(p.getP_local(),
				// "UTF-8"));
				job.put("pitem", URLEncoder.encode(p.getP_item(), "UTF-8"));
				job.put("pstate", URLEncoder.encode(p.getP_state(), "UTF-8"));

				if (job.size() > 0) {
					jarr.add(job);
				}
			}*/

			//json.put("list", jarr);

			response.setContentType("application/json; charset=utf-8");
			out.print(json.toJSONString());

		} catch (RentalException e) {
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
