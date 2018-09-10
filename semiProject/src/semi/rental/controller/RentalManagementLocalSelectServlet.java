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

import semi.locationInfo.model.vo.LocationInfo;
import semi.rental.exception.RentalException;
import semi.rental.model.service.RentalService;

/**
 * Servlet implementation class RentalManagementLocalSelectServlet
 */
@WebServlet("/rmlselect")
public class RentalManagementLocalSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RentalManagementLocalSelectServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//지역명 출력용 컨트롤러
		System.out.println("RentalManagementLocalSelectServlet 실행");
		response.setContentType("text/html; charset=utf-8");

		ArrayList<LocationInfo> list = null;
		JSONObject json = null;
		JSONArray jarr = null;
		PrintWriter out = response.getWriter();

		try {
			list = new RentalService().rentalLocalList();

			json = new JSONObject();
			jarr = new JSONArray();

			for (LocationInfo l : list) {
				JSONObject job = new JSONObject();
				job.put("lloca", URLEncoder.encode(l.getL_Local(), "UTF-8"));
				
				if (job.size() > 0) {
					jarr.add(job);
				}
			}

			json.put("list", jarr);

			response.setContentType("application/json; charset=utf-8");
			System.out.println(json.toJSONString());
			out.print(json.toJSONString());

		} catch (

		RentalException e) {
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
