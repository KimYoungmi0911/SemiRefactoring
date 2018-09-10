package semi.payment.model.dao;

import static semi.common.JDBCTemplat.close;

import java.sql.Connection;
import java.sql.PreparedStatement;

import semi.payment.exception.PaymentException;
import semi.payment.model.vo.Payment;

public class PaymentDao {

	public int insertPay(Connection conn, Payment pay) throws PaymentException {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String query = "";
		
		if(pay.getPayType().equals("card")) {
			query = "insert into tb_pay values (?, ?, sysdate, ?, ?, ?)";
		} else {
			query = "insert into tb_pay values (?, ?, null, ?, ?, ?)";
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, pay.getrNo());
			pstmt.setString(2, pay.getmId());
			pstmt.setString(3, pay.getPayType());
			pstmt.setInt(4, pay.getPayPrice());
			pstmt.setString(5, pay.getPayConfirm());
			
			result = pstmt.executeUpdate();
			
			if(result <= 0) {
				throw new PaymentException("결제 등록 실패!");
			}
		} catch (Exception e) {
			e.getMessage();
			throw new PaymentException(e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int selectPoint(Connection conn, String userId) {
		return 0;
	}
	
	public int updatePoint(Connection conn, String userId) {
		return 0;
	}
}
