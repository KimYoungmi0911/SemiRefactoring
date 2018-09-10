package semi.review.model.service;

import static semi.common.JDBCTemplat.*;

import java.sql.Connection;
import java.util.ArrayList;

import semi.review.model.dao.ReviewDao;
import semi.review.model.vo.ReviewBoard;

public class ReviewService {

	public ArrayList<ReviewBoard> listView() {
		ArrayList<ReviewBoard> list = null;
		
		return list;
	}
	
	public int deleteBoard(int BoardNo) {
		int result = 0;
		
		return result;
	}
	
	public int updateBoard(ReviewBoard review) {
		return 0;
	}
	
	public int insertBoard(ReviewBoard review) {
		return 0;
	}

	public ArrayList<ReviewBoard> selectTop3(String mId) {
		Connection con = getConnection();
		ArrayList<ReviewBoard> list = new ReviewDao().selectTop3(con, mId);
		close(con);
		return list;
	}

	

	
}
