package semi.review.model.dao;

import static semi.common.JDBCTemplat.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import semi.review.model.vo.ReviewBoard;

public class ReviewDao {

	public ArrayList<ReviewBoard> listView(Connection conn) {
		ArrayList<ReviewBoard> list = null;
		
		return list;
	}
	
	public int deleteBoard(Connection conn, int BoardNo) {
		int result = 0;
		
		return result;
	}
	
	public int updateBoard(Connection conn, ReviewBoard review) {
		return 0;
	}
	
	public int insertBoard(Connection conn, ReviewBoard review) {
		return 0;
	}

	public ArrayList<ReviewBoard> selectTop3(Connection con, String mId) {
		ArrayList<ReviewBoard> list = new ArrayList<ReviewBoard>();
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * "
				+ "from (select rownum rnum, rb_no, rb_title, "
				+ "rb_date, rb_count, nvl(rb_file1, ?) as rb_file1, nvl(rb_file2, ?) as rb_file2 "
				+ "from (select * from tb_reviewboard where m_id = ?"
				+ "order by rb_no desc)) "
				+ "where rnum >= 1 and rnum <= 3";
		
		try {
			//stmt = con.createStatement();
			//rset = stmt.executeQuery(query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "없음");
			pstmt.setString(2, "없음");
			pstmt.setString(3, mId);
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				ReviewBoard rb = new ReviewBoard();
				rb.setRbNo(rset.getInt("rb_no"));
				rb.setRbTitle(rset.getString("rb_title"));
				rb.setRbDate(rset.getDate("rb_date"));
				rb.setRbCount(rset.getInt("rb_count"));
				rb.setRbFile1(rset.getString("rb_file1"));
				rb.setRbFile2(rset.getString("rb_file2"));
				
				list.add(rb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
}
