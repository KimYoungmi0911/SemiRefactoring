package semi.payment.model.service;

import semi.payment.exception.PaymentException;
import semi.payment.model.dao.PaymentDao;
import semi.payment.model.vo.Payment;
import static semi.common.JDBCTemplat.*;

import java.sql.Connection;

public class PaymentService {

	public int insertPay(Payment pay) throws PaymentException {
		Connection conn = getConnection();
		
		int result = 0;
		
		result = new PaymentDao().insertPay(conn, pay);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public int selectPoint(String userId) {
		return 0;
	}
	
	public int updatePoint(String userId) {
		return 0;
	}
}
