package semi.admin.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static semi.common.JDBCTemplat.*;

import semi.admin.exception.AdminException;
import semi.admin.model.vo.Admin;

public class AdminDao {

	public Admin selectAdmin(Connection con, String aId) throws AdminException{
		Admin admin = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from tb_admin where a_id = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, aId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				admin = new Admin();
				
				admin.setaId(aId);
				admin.setaPassword(rset.getString("a_password"));
				admin.setaName(rset.getString("a_name"));
				admin.setaNickname(rset.getString("a_nickname"));
				admin.setaGrade(rset.getString("a_grade"));
				admin.setlNo(rset.getInt("l_no"));
				
			}else{
				throw new AdminException(aId + "에 대한 행이 없음!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AdminException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		return admin;
	}

	public String aloginCheck(Connection con, String aId, String aPassword) throws AdminException{
		String aName = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select a_name from tb_admin where a_id = ? and a_password = ?";
		System.out.println(aId + ", " + aPassword);
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, aId);
			pstmt.setString(2, aPassword);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				System.out.println("admin된다면");
				aName = rset.getString("a_name");
			}else{
				throw new AdminException("로그인 조회 실패!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AdminException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		return aName;
	}

	public int updateAdmin(Connection con, Admin admin) throws AdminException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update tb_admin set a_password = ?, "
				+ "a_name = ?, a_nickname = ?, "
				+ "a_grade = ?, l_no = ? "
				+ "where a_id = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, admin.getaPassword());
			pstmt.setString(2, admin.getaName());
			pstmt.setString(3, admin.getaNickname());
			pstmt.setString(4, admin.getaGrade());
			pstmt.setInt(5, admin.getlNo());
			pstmt.setString(6, admin.getaId());
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new AdminException("회원 정보 수정 실패!");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new AdminException(e.getMessage());
		}finally{
			close(pstmt);
		}
		return result;
	}

	
	public int deleteAdmin(Connection con, String aId) throws AdminException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from tb_admin where a_id = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, aId);
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new AdminException("관리자 정보 삭제 실패!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AdminException(e.getMessage());
		}finally{
			close(pstmt);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
}
