package semi.products.model.service;

import static semi.common.JDBCTemplat.*;

import java.sql.Connection;
import java.util.ArrayList;

import semi.products.exception.ProductsException;
import semi.products.model.dao.ProductsDao;
import semi.products.model.vo.Product;



public class ProductsService {

	public ProductsService() {}

	public int getListCount() throws ProductsException {
		Connection con = getConnection();
		int listCount = new ProductsDao().getListCount(con);
		close(con);
		System.out.println("serviceCount");
		return listCount;
	}

	public ArrayList<Product> selectList(int currentPage, int limit) throws ProductsException {
		Connection con = getConnection();
		ArrayList<Product> list = 
				new ProductsDao().selectList(con, currentPage, limit);
		close(con);
		System.out.println("serviceselect");
		return list;
	}

	public Product selectProducts(String pName) throws ProductsException {
		Connection con = getConnection();
		Product product = new ProductsDao().selectProducts(con, pName);
		close(con);
		return product;
	}

	/*public ArrayList<Product> selectProduct(String keyword) throws ProductsException{
		Connection con = getConnection();
		ArrayList<Product> list = new ProductsDao().selectProduct(con, keyword);
		close(con);
		return list;
	}*/
	
	
	
	/*public ArrayList<Product> selectLocal(String local) throws ProductsException{
		Connection con = getConnection();
		ArrayList<Product> list = new ProductsDao().selectLocal(con, local);
		close(con);
		return list;
	}*/

	public ArrayList<Product> selectProduct(String keyword, String localselect) throws ProductsException{
		Connection con = getConnection();
		ArrayList<Product> list = new ProductsDao().selectProduct(con, keyword, localselect);
		close(con);
		return list;
	}

	public Product selectProducts1(String pName) throws ProductsException{
		Connection con = getConnection();
		Product product = new ProductsDao().selectProducts1(con, pName);
		close(con);
		return product;
	}



	  public ArrayList<String> selectOffice(String pName) throws ProductsException{
	      Connection con = getConnection();
	      ArrayList<String> list = new ProductsDao().selectOffice(con, pName);
	      close(con);
	      
	         return list;
	   
	   }

	public ArrayList<Product> myrent(String mId) throws ProductsException{
		Connection con = getConnection();
		ArrayList<Product> list2 = new ProductsDao().myrent(con, mId);
		close(con);
		return list2;
	}

	public ArrayList<Product> selectTop3(String mId) {
		Connection con = getConnection();
		ArrayList<Product> list = new ProductsDao().selectTop3(con, mId);
		close(con);
		return list;
	}

	public ArrayList<Product> selectCenterName(String filter) throws ProductsException {
		Connection conn = getConnection();
		ArrayList<Product> list = new ProductsDao().selectCenterName(conn, filter);
		close(conn);
		
		return list;
	}


	public int proDelete(String name, String local) throws ProductsException {
		Connection conn = getConnection();
		int result = new ProductsDao().proDelete(conn, name, local);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public String getImageNum() throws ProductsException {
		Connection conn = getConnection();
		String num = new ProductsDao().getImageNum(conn);
		close(conn);
		
		return num;
	}
	
	public int insertManagement(Product p) throws ProductsException {
		Connection conn = getConnection();
		int result = new ProductsDao().insertManagement(conn, p);
		
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public int mGetListCount() throws ProductsException{
		Connection conn = getConnection();
		int listCount = new ProductsDao().mGetListCount(conn);
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Product> mAllSelectList(int currentPage, int limit) throws ProductsException {
		Connection conn = getConnection();
		ArrayList<Product> list = new ProductsDao().mAllSelectList(conn, currentPage, limit);
		close(conn);
		
		return list;
	}
	
	public ArrayList<Product> localSelectList(String local) {
		Connection conn = getConnection();
		ArrayList<Product> list = new ProductsDao().localSelectList(conn, local);
		close(conn);
		
		return list;
	}
	
	public int mGetSelectListCount(String filter, String searchTF) throws ProductsException {
		Connection conn = getConnection();
		int listCount = new ProductsDao().mGetSelectListCount(conn, filter, searchTF);
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Product> mSelectList(int currentPage, int limit, String filter, String searchTF) throws ProductsException {
		Connection conn = getConnection();
		ArrayList<Product> list = new ProductsDao().mSelectList(conn, currentPage, limit, filter, searchTF);
		close(conn);
		
		return list;
	}
	
	public int proUpdate(int price, int count, String name, String local) throws ProductsException {
		Connection conn = getConnection();
		int result = new ProductsDao().proUpdate(conn, price, count, name, local);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public ArrayList<Product> cutOffSelectList(int currentPage, int limit) throws ProductsException {
		Connection con = getConnection();
		ArrayList<Product> list = new ProductsDao().cutOffSelectList(con, currentPage, limit);
		close(con);
		System.out.println("servicecutOffSelectList");
		return list;
	}
	
	public ArrayList<Product> drillSelectList(int currentPage, int limit) throws ProductsException {
		Connection con = getConnection();
		ArrayList<Product> list = new ProductsDao().drillSelectList(con, currentPage, limit);
		close(con);
		
		return list;
	}
	
	public ArrayList<Product> chargeSelectList(int currentPage, int limit) throws ProductsException {
		Connection con = getConnection();
		ArrayList<Product> list = new ProductsDao().chargeSelectList(con, currentPage, limit);
		close(con);
		
		return list;
	}
	
	public ArrayList<Product> etcSelectList(int currentPage, int limit) throws ProductsException {
		Connection con = getConnection();
		ArrayList<Product> list = new ProductsDao().etcSelectList(con, currentPage, limit);
		close(con);
		
		return list;
	}
	
	public ArrayList<Product> lifeEtcSelectList(int currentPage, int limit) throws ProductsException {
		Connection con = getConnection();
		ArrayList<Product> list = new ProductsDao().lifeEtcSelectList(con, currentPage, limit);
		close(con);
		
		return list;
	}
}
