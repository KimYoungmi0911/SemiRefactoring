package semi.admin.model.service;

import static semi.common.JDBCTemplat.*;


import java.sql.Connection;

import semi.admin.exception.AdminException;
import semi.admin.model.dao.AdminDao;
import semi.admin.model.vo.Admin;

public class AdminService {

	public Admin selectAdmin(String aId) throws AdminException{
		Connection con = getConnection();
		Admin admin = new AdminDao().selectAdmin(con, aId);
		close(con);
		return admin;
	}

	public String aloginCheck(String aId, String aPassword) throws AdminException{
		Connection con = getConnection();
		String aName = new AdminDao().aloginCheck(con, aId, aPassword);
		close(con);
		return aName;
		
	}

	public int updateAdmin(Admin admin) throws AdminException{
		Connection con = getConnection();
		int result = new AdminDao().updateAdmin(con, admin);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		return result;
	}
	
	public int deleteAdmin(String aId) throws AdminException{
		Connection con = getConnection();
		int result = new AdminDao().deleteAdmin(con, aId);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		return result;
	}

}
