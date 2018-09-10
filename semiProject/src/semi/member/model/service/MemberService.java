package semi.member.model.service;

import static semi.common.JDBCTemplat.*;
import java.sql.Connection;
import java.util.ArrayList;

import semi.member.exception.MemberException;
import semi.member.model.dao.MemberDao;
import semi.member.model.vo.Member;

import semi.review.model.vo.ReviewBoard;

public class MemberService {

	public MemberService() {}
	
	public String loginCheck(String mId, String mPassword) throws MemberException{
		Connection con = getConnection();
		String mName = new MemberDao().loginCheck(con, mId, mPassword);
		close(con);
		return mName;
	}
	
	public int deleteMember(String mId) throws MemberException{
		Connection con = getConnection();
		int result = new MemberDao().deleteMember(con, mId);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		return result;
	}

	public Member selectMember(String mId) throws MemberException{
		Connection con = getConnection();
		Member member = new MemberDao().selectMember(con, mId);
		close(con);
		return member;
	}
	
	public Member myPoint(String mId) throws MemberException{
		Connection con = getConnection();
		Member member = new MemberDao().myPoint(con, mId);
		close(con);
		return member;
	}
	
	public int updateMember(Member member) throws MemberException{
		Connection con = getConnection();
		int result = new MemberDao().updateMember(con, member);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		return result;
	}

	public Member myRent(String mId) throws MemberException{
		Connection con = getConnection();
		Member member = new MemberDao().myRent(con, mId);
		close(con);
		return member;
	}
	public ArrayList<ReviewBoard> myboard(String mId) throws MemberException{
		Connection con = getConnection();
		ArrayList<ReviewBoard> list = new MemberDao().myboard(con, mId);
		close(con);
		return list;
	}
	

	public String aloginCheck(String aId, String aPassword) throws MemberException{
		Connection con = getConnection();
		String aName = new MemberDao().aloginCheck(con, aId, aPassword);
		close(con);
		return aName;
	}
	
	public int insertMember(Member m) throws MemberException {
		Connection con = getConnection();
		int result = new MemberDao().insertMember(con, m);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public int selectCheckId(String m_Id) throws MemberException {
		Connection con = getConnection();
		
		int result = new MemberDao().selectCheckId(con, m_Id);
		
		close(con);
		return result;
	}
	public String findId(String fName, String fEmail) throws MemberException{
		Connection con = getConnection();
		String result = new MemberDao().findId(con, fName, fEmail);
		close(con);
		return result;
	}
	public String findPw(String fName, String fEmail, String fId) throws MemberException{
		Connection con = getConnection();
		String resultpw = new MemberDao().findPw(con, fName, fEmail, fId);
		close(con);
		return resultpw;
	}
	
	//관리자용------------------------------------------------------------
		//페이지 당 조회 회원 리밋걸기 위해 회원 수 count(*) 조회
		public int mGetListCount() throws MemberException {
			Connection con = getConnection();
			int listCount = new MemberDao().mGetListCount(con);
			close(con);
			return listCount;
		}
		
		//회원 전체 조회용
		public ArrayList<Member> mSelectList(int currentPage, int limit) throws MemberException {
			Connection con = getConnection();
			ArrayList<Member> list = new MemberDao().mSelectList(con, currentPage, limit);
			close(con);
			return list;
		}
		
		//회원 필터 조회용
		public ArrayList<Member> mFilterSearch(String filter, String value) throws MemberException{
			Connection con = getConnection();
			ArrayList<Member> list = new MemberDao().mFilterSearch(con, filter, value);
			
			close(con);
			return list;
		}
		
		//회원 수정용
		public int mEditMember(Member m) throws MemberException{
			Connection con = getConnection();
			int result = new MemberDao().mEditMember(con, m);
			if(result > 0)
				commit(con);
			else
				rollback(con);
			
			close(con);
			
			return result;
		}
		
		//관리자 회원 등록
		public int mInsertMember(Member m) throws MemberException {
			Connection con = getConnection();
			int result = new MemberDao().mInsertMember(con, m);
			if(result > 0)
				commit(con);
			else
				rollback(con);
			
			close(con);
			
			return result;
		}
		//관리자용 end

		

		

		

}
