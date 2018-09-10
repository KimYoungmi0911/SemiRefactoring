package semi.rental.model.service;

import static semi.common.JDBCTemplat.close;
import static semi.common.JDBCTemplat.commit;
import static semi.common.JDBCTemplat.getConnection;
import static semi.common.JDBCTemplat.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

import semi.locationInfo.model.vo.LocationInfo;
import semi.products.model.vo.Product;
import semi.rental.exception.RentalException;
import semi.rental.model.dao.RentalDao;
import semi.rental.model.vo.Rental;

public class RentalService {
	
	public int insertRental(Rental rental) throws RentalException {
		int result = 0;
		
		Connection conn = getConnection();
		
		result = new RentalDao().insertRental(conn, rental);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	//관리자 대여 등록
		public int manageInsertRental(Rental r) throws RentalException{
			Connection con = getConnection();
			int result = new RentalDao().manageInsertRental(con, r);
			if(result > 0)
				commit(con);
			else
				rollback(con);
			close(con);
			return result;
		}
	
	//대여목록 테이블의 대여번호 생성용
		public Rental insertRentalNumber(Rental r) throws RentalException{
			Connection con = getConnection();
			String rentalNum = new RentalDao().insertRentalNumber(con, r);
			r.setrNo(rentalNum);
			
			if(Integer.parseInt(rentalNum) > 0)
				commit(con);
			else
				rollback(con);
			return r;
				
		}

		public ArrayList<Rental> myrent(String mId) throws RentalException{
			Connection con = getConnection();
			ArrayList<Rental> list = new RentalDao().myrent(con, mId);
			close(con);
			return list;
		}

		public ArrayList<Product> selectTop3(String mId) throws RentalException{
			Connection con = getConnection();
			ArrayList<Product> list = new RentalDao().selectTop3(con, mId);
			close(con);
			return list;
		}
		
		//관리자 0831----------------------------------------------------------------------------------------------
		// 지역명 조회용
		public ArrayList<LocationInfo> rentalLocalList() throws RentalException {
			Connection con = getConnection();
			ArrayList<LocationInfo> list = new RentalDao().rentalLocalList(con);
			if (list != null)
				commit(con);
			else
				rollback(con);
			close(con);
			return list;
		}

		// 동사무소 조회용
		public ArrayList<LocationInfo> rentalLocalNameList(String localName) throws RentalException {
			Connection con = getConnection();
			ArrayList<LocationInfo> list = new RentalDao().rentalLocalNameList(con, localName);
			if (list != null)
				commit(con);
			else
				rollback(con);
			close(con);
			return list;
		}

		// 물품명 출력용
		public ArrayList<Product> rentalProductNameList(String localName) throws RentalException {
			Connection con = getConnection();
			ArrayList<Product> list = new RentalDao().rentalProductNameList(con, localName);
			if (list != null)
				commit(con);
			else
				rollback(con);
			close(con);
			return list;
		}

		// 물품정보 출력용
		public Product rentalProductInfoList(String pName, String lName) throws RentalException {
			Connection con = getConnection();
			Product p = new RentalDao().rentalProductInfoList(con, pName, lName);
			if (p != null)
				commit(con);
			else
				rollback(con);
			close(con);
			return p;
		}
		
		//대여목록 카운팅
		public int rGetListCount() throws RentalException {
			Connection con = getConnection();
			int listCount = new RentalDao().rGetListCount(con);
			if(listCount > 0)
				commit(con);
			else
				rollback(con);
			close(con);
			return listCount;
		}
		
		//대여목록 전체조회
		public ArrayList<Rental> rSelectList(int currentPage, int limit) throws RentalException {
			Connection con = getConnection();
			ArrayList<Rental> list = new RentalDao().rSelectList(con, currentPage, limit);
			if(list != null)
				commit(con);
			else
				rollback(con);
			close(con);
			return list;
		}
		
		//대여목록 필터조회
		public ArrayList<Rental> rFilterSelectList(HashMap filter) throws RentalException {
			Connection con = getConnection();
			ArrayList<Rental> list = new RentalDao().rFilterSelectList(con, filter);
			if(list != null)
				commit(con);
			else 
				rollback(con);
			close(con);
			return list;
		}
		
		//대여정보 상세조회
		public JSONObject rentalTablePrintInfo(int pno) throws RentalException {
			Connection con = getConnection();
			JSONObject json = new RentalDao().rentalTablePrintInfo(con, pno);
			if(json != null)
				commit(con);
			else 
				rollback(con);
			close(con);
			return json;
		}
		
		//반납처리용
		public int updateProductState(int pNo, String rNo) throws RentalException {
			Connection con = getConnection();
			int result = new RentalDao().updateProductState(con, pNo, rNo);
			if(result > 0)
				commit(con);
			else
				rollback(con);
			close(con);
			return result;
		}
		
		//대여기간 수정용
		public int updateReturnDate(String rNo, String returnDate) throws RentalException {
			Connection con = getConnection();
			int result = new RentalDao().updateReturnDate(con, rNo, returnDate);
			if(result > 0 )
				commit(con);
			else
				rollback(con);
			close(con);
			return result;
		}
		
		//관리자용 대여 등록
		public int mInsertRental(Rental r) throws RentalException {
			Connection con = getConnection();
			int result = new RentalDao().mInsertRental(con,  r);
			if(result > 0)
				commit(con);
			else
				rollback(con);
			close(con);
			return result;
		}
		//관리자 0831----------------------------------------------------------------------------------------------

}
