package semi.products.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import semi.products.exception.ProductsException;
import semi.products.model.service.ProductsService;
import semi.products.model.vo.Product;

/**
 * Servlet implementation class ProductManagementEnrollServlet
 */
@WebServlet("/pmenroll")
public class ProductManagementEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductManagementEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		int maxSize = 1024 * 1024 * 10;
		
		String mainSavePath = request.getSession().getServletContext().getRealPath("/resources/images/main");
		String detailSavePath = request.getSession().getServletContext().getRealPath("/resources/images/details");
		
		System.out.println(mainSavePath);
		System.out.println(detailSavePath);
		
		int result = 0;
		
		Product p = null;
		//PrintWriter out = response.getWriter();
		
		//response.setContentType("application/json; charset=utf-8");
		
		MultipartRequest mrequest = null;
		try {
			mrequest = new MultipartRequest(request, mainSavePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
			
			String name = mrequest.getParameter("tname");
			int price = Integer.parseInt(mrequest.getParameter("tprice"));
			int count = Integer.parseInt(mrequest.getParameter("tcount"));
			String local = mrequest.getParameter("local2");
			String item = mrequest.getParameter("titem");
			
			String originalFileName = mrequest.getFilesystemName("mainupfile");
			String detailFileName = mrequest.getFilesystemName("detailupfile");
			
			originalFileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "." + originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			
			String num = "";
			
			num = new ProductsService().getImageNum();
			
			int imageNum = Integer.parseInt(num.substring(num.lastIndexOf(".")-2, num.lastIndexOf(".")));
			System.out.println(imageNum);
			
			String renameFileName = (imageNum + 1) + "." + originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			String renameDetailName = (imageNum + 1) + "." + detailFileName.substring(detailFileName.lastIndexOf(".") + 1);
			
			if(originalFileName != null) {
				
				p = new Product();
			
				File originMainFile = new File(mainSavePath + "\\" + originalFileName);
				File originDetailFile = new File(mainSavePath + "\\" + detailFileName);
				System.out.println(originMainFile);
				System.out.println(originDetailFile);
				
				File renameMainFile = new File(mainSavePath + "\\" + renameFileName);
				File renameDetailFile = new File(detailSavePath + "\\" + renameFileName);
				System.out.println(renameMainFile);
				System.out.println(renameDetailFile);
				
				int read = -1;
				byte[] buf = new byte[1024];
					
				FileInputStream fin = new FileInputStream(originMainFile);
				FileOutputStream fout = new FileOutputStream(renameMainFile);
				
				while ((read = fin.read(buf, 0, buf.length)) != -1) {
					fout.write(buf, 0, read);
				}
				
				fin = new FileInputStream(originDetailFile);
				fout = new FileOutputStream(renameDetailFile);
				
				while ((read = fin.read(buf, 0, buf.length)) != -1) {
					fout.write(buf, 0, read);
				}
					
				fin.close();
				fout.close();
				
				p.setP_name(name);
				p.setP_price(price);
				p.setP_count(count);
				p.setP_local(local);
				p.setP_item(item);
				p.setP_main_image("/semi/resources/images/main/" + renameFileName);
				p.setP_detail_image("/semi/resources/images/details/" + renameFileName);
				
				originMainFile.delete();
				originDetailFile.delete();
				
				result = new ProductsService().insertManagement(p);
				
				//out.write(result);
				//out.flush();
			}
			RequestDispatcher view = request.getRequestDispatcher("views/manager/toolManagementView.jsp");
			request.setAttribute("price", p.getP_price());
			view.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} /*finally {
			//out.close();
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
