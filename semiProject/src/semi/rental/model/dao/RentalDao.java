package semi.rental.model.dao;

import static semi.common.JDBCTemplat.close;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


import org.json.simple.JSONObject;

import semi.locationInfo.model.vo.LocationInfo;
import semi.products.model.vo.Product;
import semi.rental.exception.RentalException;
import semi.rental.model.vo.Rental;

public class RentalDao {
	
	public int insertRental(Connection conn, Rental rental) throws RentalException {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String query = "insert into tb_rental values (?, '3', ?, ?, ?, sysdate, sysdate, sysdate, null, null, ?)";
		
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		
		/*alendar tstart = Calendar.getInstance();
		Calendar tend = Calendar.getInstance();
		
		tstart.set(Integer.parseInt(rental.getrStartDate().substring(0, 4)), Integer.parseInt(rental.getrStartDate().substring(6, 7)), Integer.parseInt(rental.getrStartDate().substring(9)));
		tend.set(Integer.parseInt(rental.getrReturnDate().substring(0, 4)), Integer.parseInt(rental.getrReturnDate().substring(6, 7)), Integer.parseInt(rental.getrReturnDate().substring(9)));
		
		Date ss = (Date) tstart.getTime();
		Date ee = (Date) tend.getTime();
		
		System.out.println("s : " + sdf.format(ss));
		System.out.println("e  :" + sdf.format(ee));*/
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, rental.getrNo());
			//pstmt.setInt(2, rental.getpNo());
			pstmt.setString(2, rental.getmId());
			pstmt.setInt(3, rental.getpCount());
			pstmt.setInt(4, rental.getrPrice());
			//pstmt.setString(6,rental.getrStartDate());
			//pstmt.setString(7, rental.getrReturnDate());
			pstmt.setString(5, rental.getpState());
			System.out.println(rental);
			result = pstmt.executeUpdate();
			
			if(result <= 0) {
				throw new RentalException("물품 대여 정보 등록 실패!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RentalException(e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int manageInsertRental(Connection con, Rental r) throws RentalException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into tb_rental values (대여번호, ?, ?, ?, ?, ?, ?, null, null, ?)";
		
		
		/*R_NO	VARCHAR2(100 BYTE)	No		1	대여번호
		P_NO	NUMBER	No		2	물품번호
		M_ID	VARCHAR2(30 BYTE)	Yes		3	아이디
		R_PRICE	NUMBER	No		4	대여소계
		RENTAL_DATE	DATE	Yes	SYSDATE	5	대여접수일자
		RENTAL_START_DATE	DATE	Yes		6	대여실행일자
		R_RETURN_DATE	DATE	No		7	반납접수일자
		R_RETURN_LAST_DATE	DATE	Yes		8	반납실행일자
		R_BOOKING_DAYE	DATE	Yes		9	예약일자
		P_STATE	VARCHAR2(30 BYTE)	Yes		10	대여상태*/
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "대여번호");
			pstmt.setInt(2, r.getpNo());
			pstmt.setString(3, r.getmId());
			pstmt.setInt(4, r.getrPrice());
			//pstmt.setDate(5, r.getRentalDate());
			//pstmt.setDate(6, r.getRentalStartDate());
			//pstmt.setDate(7, r.getRentalReturnDate());
			pstmt.setString(8, r.getpState());
			
			result = pstmt.executeUpdate();
			
					
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		
		
		return result;
	}

	public String insertRentalNumber(Connection con, Rental r) throws RentalException{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String rentalNum = "";
		
		String query = "select r_no from tb_rentallist where m_id = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, r.getmId());
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				rentalNum = rset.getString(1);
			
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		
		return rentalNum;
	}

	public ArrayList<Rental> myrent(Connection con, String mId) throws RentalException{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Rental> list = new ArrayList<Rental>();
		
		String query = "select * from tb_rental where m_id = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Rental rental = new Rental();
				
				
				rental.setmId(mId);
				rental.setrNo(rset.getString("r_no"));
				rental.setpCount(rset.getInt("p_count"));
				rental.setpNo(rset.getInt("p_no"));
				rental.setrPrice(rset.getInt("r_price"));
				rental.setrDate(rset.getString("rental_date"));
				rental.setrStartDate(rset.getString("rental_start_date"));
				rental.setrReturnDate(rset.getString("r_return_date"));
				rental.setRReturnLastDate(rset.getString("r_return_last_date"));
				rental.setrBookingDate(rset.getString("r_booking_date"));
				rental.setpState(rset.getString("p_state"));
			
				

				list.add(rental);
				
				if(list.size() == 0){
					throw new RentalException("내역이 없습니다.");
				}
			
		
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RentalException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ArrayList<Product> selectTop3(Connection con, String mId) throws RentalException{
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from (select rownum rnum, p_name as \"p_name\", p_price as \"p_price\", p_count as \"p_count\", p_no as \"p_no\" from (select * from tb_product join tb_rental using (p_no, p_count) where m_id = ?)) where rnum >= ? and rnum <= ?";
		
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mId);
			pstmt.setInt(2, 1);
			pstmt.setInt(3, 3);
			rset = pstmt.executeQuery();
			if(rset == null){
				System.out.println("sadsada");
			}
			while(rset.next()){
				Product p = new Product();
				

				p.setP_no(rset.getInt("p_no"));
				p.setP_name(rset.getString("p_name"));
				p.setP_count(rset.getInt("p_count"));
				p.setP_price(rset.getInt("p_price"));
				System.out.println("dao p : " + p.toString());
				list.add(p);	
			}
			
			if(list.size() < 0 )
				throw new RentalException("리스트 빔");
				
		} catch (Exception e) {
			e.printStackTrace();
			throw new RentalException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		return list;
				

	}
	
	//관리자 0831------------------------------------------------------------------------------------------------------

		// 지역명 조회용
		public ArrayList<LocationInfo> rentalLocalList(Connection con) throws RentalException {
			Statement stmt = null;
			ResultSet rset = null;
			ArrayList<LocationInfo> list = new ArrayList<LocationInfo>();

			String query = "select distinct l_local from tb_local order by 1";

			try {
				stmt = con.createStatement();
				rset = stmt.executeQuery(query);

				while (rset.next()) {
					LocationInfo l = new LocationInfo();
					l.setL_Local(rset.getString("l_local"));

					list.add(l);

				}

				if (list == null)
					throw new RentalException("지역 존재하지 않음");

			} catch (Exception e) {
				e.printStackTrace();
				throw new RentalException(e.getMessage());
			} finally {
				close(rset);
				close(stmt);
			}

			return list;
		}

		// 동사무소 조회용
		public ArrayList<LocationInfo> rentalLocalNameList(Connection con, String localName) throws RentalException {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<LocationInfo> list = new ArrayList<LocationInfo>();

			String query = "select l_name from tb_local where l_local = ? order by 1";

			try {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, localName);

				rset = pstmt.executeQuery();

				while (rset.next()) {
					LocationInfo l = new LocationInfo();
					l.setL_Name(rset.getString("l_name"));

					list.add(l);
				}

				if (list == null)
					throw new RentalException("동사무소 존재하지 않음");

			} catch (Exception e) {
				e.printStackTrace();
				throw new RentalException(e.getMessage());
			} finally {
				close(rset);
				close(pstmt);
			}

			return list;
		}

		// 물품이름 조회용
		public ArrayList<Product> rentalProductNameList(Connection con, String localName) throws RentalException {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<Product> list = new ArrayList<Product>();
			String query = "select p_name from tb_product where p_local = ? order by 1";

			try {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, localName);

				rset = pstmt.executeQuery();

				while (rset.next()) {
					Product p = new Product();
					p.setP_name(rset.getString(1));
					list.add(p);
				}

				if (list == null)
					throw new RentalException("조회물품 존재하지 않음");

			} catch (Exception e) {
				e.printStackTrace();
				throw new RentalException(e.getMessage());
			} finally {
				close(rset);
				close(pstmt);
			}

			return list;
		}

		// 물품정보 조회용
		public Product rentalProductInfoList(Connection con, String pName, String lName) throws RentalException {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			Product p = new Product();
			System.out.println("productinfo dao : " + pName + "," + lName);
			String query = "select p_no, p_price, p_count, p_item, p_state "
					+ "from tb_product where p_name = ? and p_local = ?";

			try {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, pName);
				pstmt.setString(2, lName);

				rset = pstmt.executeQuery();

				if (rset.next()) {
					p.setP_no(rset.getInt(1));
					p.setP_price(rset.getInt(2));
					p.setP_count(rset.getInt(3));
					p.setP_item(rset.getString(4));
					p.setP_state(rset.getString(5));
				} else {
					throw new RentalException("물품정보 존재하지 않음");
				}
				System.out.println("물품정보 : " + p.toString());
			} catch (Exception e) {
				e.printStackTrace();
				throw new RentalException(e.getMessage());
			} finally {
				close(rset);
				close(pstmt);
			}

			return p;
		}

		// 대여목록 카운팅
		public int rGetListCount(Connection con) throws RentalException {
			int listCount = 0;
			Statement stmt = null;
			ResultSet rset = null;

			String query = "select count(*) from tb_rental";

			try {
				stmt = con.createStatement();
				rset = stmt.executeQuery(query);

				if (rset.next()) {
					listCount = rset.getInt(1);
				} else {
					throw new RentalException("count 해서 페이지를 측정할만한 대여목록 없음.");
				}

			} catch (Exception e) {
				// 일단 나도 exception 보고
				e.printStackTrace();
				// 던져주자
				throw new RentalException(e.getMessage());
			} finally {
				close(rset);
				close(stmt);
			}
			// listCount = 10;
			return listCount;
		}

		// 대여목록 전체조회
		public ArrayList<Rental> rSelectList(Connection con, int currentPage, int limit) throws RentalException {
			ArrayList<Rental> list = new ArrayList<Rental>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;

			String query = "select * from ("
					+ "select rownum rnum, "
					+ "r_no, "
					+ "p_no, "
					+ "m_id, "
					+ "p_count, "
					+ "r_price, "
					+ "to_char(rental_date, 'yyyy-mm-dd') rental_date, "
					+ "to_char(nvl(rental_start_date, to_date(?)), 'yyyy-mm-dd') rental_start_date, "
					+ "to_char(r_return_date, 'yyyy-mm-dd') r_return_date, "
					+ "to_char(nvl(r_return_last_date, to_date(?)), 'yyyy-mm-dd') r_return_last_date, "
					+ "to_char(nvl(r_booking_date, to_date(?)), 'yyyy-mm-dd') r_booking_date, "
					+ "p_state "
					+ "from (select * from tb_rental)) "
					+ "where rnum >= ? and rnum <= ?";
			
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;

			try {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "11/11/11");
				pstmt.setString(2, "11/11/11");
				pstmt.setString(3, "11/11/11");
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, endRow);

				rset = pstmt.executeQuery();

				while (rset.next()) {
					Rental r = new Rental();
					r.setrNo(rset.getString("r_no"));
					r.setpNo(rset.getInt("p_no"));
					r.setmId(rset.getString("m_id"));
					r.setpCount(rset.getInt("p_count"));
					r.setrPrice(rset.getInt("r_price"));
					r.setrDate(rset.getString("rental_date"));
					if(rset.getString("rental_start_date") == "2011-11-11")
						r.setrStartDate("없음");
					else
						r.setrStartDate(rset.getString("rental_start_date"));
					r.setrReturnDate(rset.getString("r_return_date"));
					if(rset.getString("r_return_last_date") == "2011-11-11")
						r.setRReturnLastDate("없음");
					else
						r.setRReturnLastDate(rset.getString("r_return_last_date"));
					if(rset.getString("r_booking_date") == "2011-11-11")
						r.setrBookingDate("없음");
					else
						r.setrBookingDate(rset.getString("r_booking_date"));
					r.setpState(rset.getString("p_state"));

					list.add(r);
				}
				
				if (list.size() == 0)
					throw new RentalException("전체 조회할 대여목록이 없습니다.");

			} catch (Exception e) {
				e.printStackTrace();
				throw new RentalException(e.getMessage());
			} finally {
				close(rset);
				close(pstmt);
			}

			return list;
		}
		
		// 대여목록 필터조회
		public ArrayList<Rental> rFilterSelectList(Connection con, HashMap filter) throws RentalException {
			ArrayList<Rental> list = new ArrayList<Rental>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String mainFilter = (String)filter.get("mainFilter");	//월별, 일별, 전체
			String value = (String)filter.get("value");	//검색필드 값
			String fVal = (String)filter.get("fVal");	//날짜 값
			String sFilter = (String)filter.get("sFilter");	//필터선택 값
			
			String query = "select r_no, "
						+ "p_no, "
						+ "m_id, "
						+ "p_count, "
						+ "r_price, "
						+ "to_char(rental_date, 'yyyy-mm-dd') rental_date, "
						+ "to_char(nvl(rental_start_date, to_date('11/11/11')), 'yyyy-mm-dd') rental_start_date, "
						+ "to_char(r_return_date, 'yyyy-mm-dd') r_return_date, "
						+ "to_char(nvl(r_return_last_date, to_date('11/11/11')), 'yyyy-mm-dd') r_return_last_date, "
						+ "to_char(nvl(r_booking_date, to_date('11/11/11')), 'yyyy-mm-dd') r_booking_date, "
						+ "p_state "
						+ "from tb_rental where ";
			
			switch(mainFilter){
			case "all" : 
				switch(sFilter){
				case "대여번호" : query += "r_no = ?";
				break;
				case "아이디" : query += "m_id = ?";
				break;
				}		
			break;
			
			case "month" :
				switch(sFilter){
				case "전체" : query += "rental_date like ?"; 
				break;
				case "대여번호" : query += "rental_date like ? and r_no = ?";
				break;
				case "아이디" : query += "rental_date like ? and m_id = ?";
				break;
				}
				fVal = "___%" + fVal + "___";
			break;
			
			case "day" : 
				switch(sFilter){
				case "전체" : query += "rental_date like to_date(?, ?)";
				break;
				case "대여번호" : query += "rental_date like to_date(?, ?) and r_no = ?";
				break;
				case "아이디" : query += "rental_date like to_date(?, ?) and m_id = ?";
				break;
				}
			break;
			
			default : System.out.println("멍미");
			}
			System.out.println("mainFilter" + mainFilter + ", sFilter : " + sFilter + ", fVal : " + fVal + ", value : " + value);
			try {
				System.out.println("query : " + query);
				pstmt = con.prepareStatement(query);
				switch(mainFilter){
				case "all" : 
					pstmt.setString(1, value);
				break;
				case "month" :
					switch(sFilter){
					case "전체" : 
						pstmt.setString(1, fVal);
					break;
					case "대여번호" : 
						pstmt.setString(1, fVal);
						pstmt.setString(2, value);
					break;
					case "아이디" : 
						pstmt.setString(1, fVal);
						pstmt.setString(2, value);
					break;
					}
				break;
				case "day" : 
					switch(sFilter){
					case "전체" :
						pstmt.setString(1, fVal);
						pstmt.setString(2, "yy/mm/dd");		
					break;
					case "대여번호" :
						pstmt.setString(1, fVal);
						pstmt.setString(2, "yy/mm/dd");
						pstmt.setString(3, value);
					break;
					case "아이디" :
						pstmt.setString(1, fVal);
						pstmt.setString(2, "yy/mm/dd");
						pstmt.setString(3, value);
					break;
					}
				break;
				default : System.out.println("멍미");
				}
				
				rset = pstmt.executeQuery();

				while (rset.next()) {
					Rental r = new Rental();

					r.setrNo(rset.getString("r_no"));
					r.setpNo(rset.getInt("p_no"));
					r.setmId(rset.getString("m_id"));
					r.setpCount(rset.getInt("p_count"));
					r.setrPrice(rset.getInt("r_price"));
					r.setrDate(rset.getString("rental_date"));
					r.setrStartDate(rset.getString("rental_start_date"));
					r.setrReturnDate(rset.getString("r_return_date"));
					r.setRReturnLastDate(rset.getString("r_return_last_date"));
					r.setrBookingDate(rset.getString("r_booking_date"));
					r.setpState(rset.getString("p_state"));

					list.add(r);
				}
				
				if(list.size() < 0){
					throw new RentalException("조회 조건에 맞는 대여 list 없음");
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw new RentalException(e.getMessage());
			} finally {
				close(rset);
				close(pstmt);
			}

			return list;
		}
		
		//대여상세정보 조회용
		public JSONObject rentalTablePrintInfo(Connection con, int pno) throws RentalException{
			JSONObject json = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String query = "select "
					+ "(select l_local from tb_local where l_name in (select p_local from tb_product where p_no = ?)) local1, "
					+ "(select p_local from tb_product where p_no = ?) local2, "
					+ "(select p_name from tb_product where p_no = ?) pname, "
					+ "(select p_item from tb_product where p_no = ?) pitem, "
					+ "(select to_char(nvl(r_booking_date, to_date('11/11-11')), 'yyyy-mm-dd') as rbookdate from tb_rental where p_no = ?) rbookdate "
					+ "from tb_rental join tb_product using (p_no) join tb_local on (l_name = p_local)";

			try {
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, pno);
				pstmt.setInt(2, pno);
				pstmt.setInt(3, pno);
				pstmt.setInt(4, pno);
				pstmt.setInt(5, pno);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()){
					json = new JSONObject();
					json.put("local1", URLEncoder.encode(rset.getString("local1"), "UTF-8"));
					json.put("local2", URLEncoder.encode(rset.getString("local2"), "UTF-8"));
					json.put("pname", URLEncoder.encode(rset.getString("pname"), "UTF-8"));
					json.put("pitem", URLEncoder.encode(rset.getString("pitem"), "UTF-8"));	
					json.put("rbookdate", URLEncoder.encode(rset.getString("rbookdate"), "UTF-8"));	
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw new RentalException(e.getMessage());
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return json;
		}
		
		//반납 처리용
		public int updateProductState(Connection con, int pNo, String rNo) throws RentalException {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int result = 0;
			
			String query = "update tb_rental set p_state = '대여가능' where p_no = ?";
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, pNo);
				result = pstmt.executeUpdate();
				
				if(result > 0){
					result = returnLastdate(con, pNo, rNo);
					if(result >0){
						productStateChange(con, pNo, rNo);
					}
				}else
					throw new RentalException("product state 변경 실패");
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new RentalException(e.getMessage());
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return result;
		}
		
		//반납시 반납실행일자 변경
		public int returnLastdate(Connection con, int pNo, String rNo) throws RentalException {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int result = 0;
			
			String query = "update tb_product set p_state = '대여가능' where p_no = ?";
			
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, pNo);
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RentalException(e.getMessage());
			} finally {
				close(rset);
				close(pstmt);
			}
			return result;
		}
		
		//반납시 물품 대여상태 변경
		public int productStateChange(Connection con, int pNo, String rNo) throws RentalException {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int result = 0;
			
			String query = "update tb_rental set r_return_last_date = sysdate where p_no = ?";
			
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, pNo);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RentalException(e.getMessage());
			} finally {
				close(rset);
				close(pstmt);
			}
			return result;
		}
		
		
		//대여기간 연장용
		public int updateReturnDate(Connection con, String rNo, String returnDate) throws RentalException {
			PreparedStatement pstmt = null;
			int result = 0;
			
			String query = "update tb_rental "
					+ "set r_return_date = to_date(?, 'yyyy-mm-dd') "
					+ "where r_no = ?";
			
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, returnDate);
				pstmt.setString(2, rNo);
				
				result = pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new RentalException(e.getMessage());
			} finally {
				close(pstmt);
			}
			
			return result;
		}
		
		
		//관리자용 대여 등록
		public int mInsertRental(Connection con, Rental r) throws RentalException {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int result = mProductStateCheck(con, r);
		
			String query = "insert into tb_rental values (?, ?, ?, ?, ?, sysdate, sysdate, "
					+ "to_date(?, 'yyyy-mm-dd'), null, null, '대여중')";
			System.out.println("대여등록 r : " + r.toString());
		
			try {
				if(result > 0){
					result = 0;
					pstmt = con.prepareStatement(query);
					pstmt.setString(1, r.getrNo());	//대여번호
					pstmt.setInt(2, r.getpNo());	//물품번호
					pstmt.setString(3, r.getmId());	//아이디
					pstmt.setInt(4, r.getpCount());	//물품수량
					pstmt.setInt(5, r.getrPrice()); //대여소계
					pstmt.setString(6, r.getrReturnDate());
						
					result = pstmt.executeUpdate();
				}else{
					throw new RentalException("물품상태 대여중인듯");
				}
				
				if(result <= 0)
					throw new RentalException("대여목록 생성했으나 대여못함");
				
			
			} catch (Exception e) {
				e.printStackTrace();
				throw new RentalException(e.getMessage());
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return result;
		}
		
		//대여등록시 물품 대여상태 확인
		public int mProductStateCheck(Connection con, Rental r) throws RentalException{
			PreparedStatement pstmt = null;
			int result = 0;
			String query = "update tb_product set p_state = '대여중' where p_no = ?";
			
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, r.getpNo());
				
				result = pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new RentalException(e.getMessage());
			} finally {
				close(pstmt);
			}

			return result;
		}
		//관리자 0831------------------------------------------------------------------------------------------------------
}
