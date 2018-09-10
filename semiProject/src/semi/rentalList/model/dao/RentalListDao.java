package semi.rentalList.model.dao;

import static semi.common.JDBCTemplat.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import semi.rentalList.exception.RentalListException;
import semi.rentalList.model.vo.RentalList;

public class RentalListDao {

	private int count = 1;

	public String selectRNo(Connection conn) throws RentalListException {
		String rNo = null;

		Statement stmt = null;
		ResultSet rset = null;

		String query = "select max(r_no) from tb_rentallist";
		System.out.println("select");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				rNo = rset.getString(1);
				System.out.println(rNo);
			} else {
				throw new RentalListException("대여번호 불러오기 실패!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RentalListException(e.getMessage());
		} finally {
			close(rset);
			close(stmt);
		}

		return rNo;
	}

	public String insertRentalList(Connection conn, RentalList rlist) throws RentalListException {
		int result = 0;
		PreparedStatement pstmt = null;
		rlist.setmId(rlist.getmId().trim());

		String rentalNo = "R";

		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		System.out.println(sdf.format(today));

		String rNo = selectRNo(conn);
		
		if (sdf.format(today).equals(rNo.substring(1, 9))) {
			rentalNo += sdf.format(today);
			if ((count + Integer.parseInt(rNo.substring(9))) < 10) {
				rentalNo += ("0" + (count + Integer.parseInt(rNo.substring(9))));
			} else {
				rentalNo += ((count + Integer.parseInt(rNo.substring(9))));
			}

		} else {
			rentalNo += sdf.format(today);
			rentalNo += ("0" + count);
		}

		System.out.println(rentalNo);

		String query = "insert into tb_rentallist values (?, ?, ?, ?)"; //(select max(r_no) from tb_rentallist) + '1'

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, rentalNo);
			pstmt.setString(2, rlist.getmId());
			pstmt.setInt(3, rlist.getrTotalCount());
			pstmt.setInt(4, rlist.getrTotalMoney());

			result = pstmt.executeUpdate();

			if (result <= 0) {
				throw new RentalListException("결제 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RentalListException(e.getMessage());
		} finally {
			close(pstmt);
		}

		return rentalNo;
	}

	// 관리자용 대여목록 생성
	public String minsertRentalList(Connection conn, RentalList rlist) throws RentalListException {
		int result = 0;
		PreparedStatement pstmt = null;
		rlist.setmId(rlist.getmId().trim());

		String rentalNo = "L";

		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		System.out.println(sdf.format(today));

		String rNo = selectRNo(conn);

		if (sdf.format(today).equals(rNo.substring(1, 9))) {
			rentalNo += sdf.format(today);
			if ((count + Integer.parseInt(rNo.substring(9))) < 10) {
				rentalNo += ("0" + (count + Integer.parseInt(rNo.substring(9))));
			} else {
				rentalNo += ((count + Integer.parseInt(rNo.substring(9))));
			}

		} else {
			rentalNo += sdf.format(today);
			rentalNo += ("0" + count);
		}

		System.out.println("생성될 대여번호 : " + rentalNo);

		String query = "insert into tb_rentallist values (?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, rentalNo);
			pstmt.setString(2, rlist.getmId());
			pstmt.setInt(3, rlist.getrTotalCount());
			pstmt.setInt(4, rlist.getrTotalMoney());

			result = pstmt.executeUpdate();

			if (result <= 0) {
				throw new RentalListException("대여목록 등록 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RentalListException(e.getMessage());
		} finally {
			close(pstmt);
		}

		return rentalNo;

	}

}
