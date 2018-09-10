package semi.cart.model.dao;

import static semi.common.JDBCTemplat.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import semi.cart.exception.CartException;
import semi.cart.model.vo.Cart;



public class CartDao {

	public ArrayList<Cart> selectList(Connection con) throws CartException{
		ArrayList<Cart> list = new ArrayList<Cart>();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from tb_product";
						
						/*+ "(select p_no, p_name, p_price, p_count, "
						+ "p_local, p_main_image, p_detail_image, p_content, "
						+ "p_item, p_state)";*/
		//System.out.println("dao 들어감?");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Cart c  = new Cart();
				c.setP_No(rset.getInt("p_no"));
				c.setP_Name(rset.getString("p_name"));
				c.setP_Price(rset.getInt("p_price"));
				c.setP_Count(rset.getInt("p_count"));
				c.setP_Local(rset.getString("p_local"));
				c.setP_Main_Image(rset.getString("p_main_image"));
				c.setP_Detail_Image(rset.getString("p_detail_image"));
				c.setP_Item(rset.getString("p_item"));
				c.setP_State(rset.getString("p_state"));
				
				list.add(c);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		/*for() {
			c.setP_No(1);
			
			list.add(c);
		}*/
		
		
		return list;
	}

	public int getListCount(Connection con, String pName, String pLocal) throws CartException {
		
		int pNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT P_NO FROM TB_PRODUCT WHERE P_NAME = ? AND P_LOCAL = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pName);
			pstmt.setString(2, pLocal);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				pNo = rset.getInt(1);
			}else{
				throw new CartException("상품이 존재하지 않습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CartException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		return pNo;
	}
}
