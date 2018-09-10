package semi.rentalList.model.service;

import semi.rentalList.exception.RentalListException;
import semi.rentalList.model.dao.RentalListDao;
import semi.rentalList.model.vo.RentalList;

import static semi.common.JDBCTemplat.*;

import java.sql.Connection;

public class RentalListService {

	public RentalListService() {
	}

	public String insertRentalList(RentalList rlist) throws RentalListException {
		Connection conn = getConnection();

		String rentalNo = new RentalListDao().insertRentalList(conn, rlist);
		System.out.println("service");
		if (rentalNo != null) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return rentalNo;
	}

	//관리자용 대여목록 생성
	public String minsertRentalList(RentalList rlist) throws RentalListException{
		Connection conn = getConnection();
		String rentalNo = new RentalListDao().minsertRentalList(conn, rlist);
		if (rentalNo != null) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return rentalNo;
	}
}
