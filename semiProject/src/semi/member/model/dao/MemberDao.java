package semi.member.model.dao;


import static semi.common.JDBCTemplat.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import semi.member.exception.MemberException;
import semi.member.model.vo.Member;

import semi.review.model.vo.ReviewBoard;

public class MemberDao {
	
	public MemberDao(){}

	public String loginCheck(Connection conn, String mId, String mPassword) throws MemberException{
		String mName = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select m_name from tb_member where m_id = ? and m_password = ?";
		System.out.println(mId + "," + mPassword);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mId);
			pstmt.setString(2, mPassword);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				System.out.println("된다면");
				mName = rset.getString("m_name");
			}else{
				throw new MemberException("로그인 조회 실패!");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		return mName;
	}
	
	public int deleteMember(Connection con, String mId) throws MemberException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from tb_member where m_id = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mId);
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new MemberException("회원 정보 삭제 실패!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException(e.getMessage());
		}finally{
			close(pstmt);
		}
		return result;
	}
	
	//내정보보기
		public Member selectMember(Connection con, String mId) throws MemberException{
			Member member = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String query = "select * from tb_member where m_id = ?";
			
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, mId);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()){
					member = new Member();
					
					member.setmId(mId);
					member.setmPassword(rset.getString("m_password"));
					member.setmName(rset.getString("m_name"));
					member.setmNickname(rset.getString("m_nickname"));
					member.setmPhone(rset.getString("m_phone"));
					member.setmAddress(rset.getString("m_address"));
					member.setmEmail(rset.getString("m_email"));
					member.setmPoint(rset.getInt("m_point"));
					member.setmSno(rset.getString("m_sno"));
					member.setmGender(rset.getString("m_gender"));
						
				}else{
					throw new MemberException(mId + "에 대한 행이 없음!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new MemberException(e.getMessage());
			}finally{
				close(rset);
				close(pstmt);
			}
			return member;
		}
	
	public int updateMember(Connection con, Member member) throws MemberException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		
		String query = "update tb_member set m_password = ?, m_nickname = ?, m_gender = ?, m_address = ?, m_phone = ?, m_email = ?, m_sno = ?, m_pwd = ? where m_id = ?";
		
		try {
			pstmt = con.prepareStatement(query);			
			pstmt.setString(1, member.getmPassword());			
			pstmt.setString(2, member.getmNickname());
			pstmt.setString(3, member.getmGender());
			pstmt.setString(4, member.getmAddress());
			pstmt.setString(5, member.getmPhone());
			pstmt.setString(6, member.getmEmail());
			pstmt.setString(7, member.getmSno());
			pstmt.setString(8, member.getmPwd());
			pstmt.setString(9, member.getmId());
						
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new MemberException("회원 정보 수정 실패!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException(e.getMessage());
		}finally{
			close(pstmt);
			
		}
		return result;
	}
	
	public Member myPoint(Connection con, String mId) throws MemberException{
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from tb_member where m_id = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				member = new Member();
				
				member.setmId(mId);
				member.setmPassword(rset.getString("m_password"));
				member.setmName(rset.getString("m_name"));
				member.setmNickname(rset.getString("m_nickname"));
				member.setmPhone(rset.getString("m_phone"));
				member.setmAddress(rset.getString("m_address"));
				member.setmEmail(rset.getString("m_email"));
				member.setmPoint(rset.getInt("m_point"));
				member.setmSno(rset.getString("m_sno"));
				member.setmGender(rset.getString("m_gender"));
					
				
			}else{
				throw new MemberException(mId + "에 대한 행이 없음!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return member;
	}

	public Member myRent(Connection con, String mId) throws MemberException{
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from tb_rental where m_id = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				
			}else{
				throw new MemberException(mId + "에 대한 행이 없음!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		return member;
	}

	public String aloginCheck(Connection con, String aId, String aPassword) throws MemberException{
		String aName = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select a_name from tb_admin "
				+ "where a_id = ? and a_password = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, aId);
			pstmt.setString(2, aPassword);
			
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				System.out.println("된다면");
				aName = rset.getString("a_name");
			}else{
				throw new MemberException("로그인 조회 실패!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		return aName;
	}
	
	public int insertMember(Connection con, Member m) throws MemberException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into tb_member values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		System.out.println(m.toString());
		try {
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, m.getmId());
			pstmt.setString(2, m.getmPassword());
			pstmt.setString(3, m.getmName());
			pstmt.setString(4, m.getmNickname());
			pstmt.setString(5, m.getmPhone());
			pstmt.setString(6, m.getmAddress());
			pstmt.setString(7, m.getmEmail());
			pstmt.setInt(8, 0);
			pstmt.setString(9, m.getmSno());
			pstmt.setString(10, m.getmGender());
			pstmt.setString(11, m.getmPwd());
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new MemberException("회원 가입 실패");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException(e.getMessage());
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int selectCheckId(Connection con, String m_Id) throws MemberException {
		int idCount = -1;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(m_id) "
					+ "from tb_member where m_id = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, m_Id);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				idCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return idCount;
	}
	public String findId(Connection con, String fName, String fEmail) throws MemberException{
		String result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		String query = "select m_id from tb_member where m_name = ? and m_email = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, fName);
			pstmt.setString(2, fEmail);
			
			
			rset = pstmt.executeQuery();
	
			while(rset.next()){
				
				System.out.println("아이디비번찾기성공");
				result = rset.getString("m_id");
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		return result;
	}
	
	public String findPw(Connection con, String fName, String fEmail, String fId) throws MemberException{
		String resultpw = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		String query = "select m_pwd from tb_member where m_name = ? and m_email = ? and m_id = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, fName);
			pstmt.setString(2, fEmail);
			pstmt.setString(3, fId);
			
			
			rset = pstmt.executeQuery();
	
			while(rset.next()){
				
				System.out.println("아이디비번찾기성공");
				resultpw = rset.getString("m_pwd");
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		return resultpw;
	}
	
	public ArrayList<ReviewBoard> myboard(Connection con, String mId) throws MemberException{
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ReviewBoard> list = new ArrayList<ReviewBoard>();
		
		String query = "select * from tb_reviewboard where m_id = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				ReviewBoard reviewboard = new ReviewBoard();
				
				reviewboard.setmId(mId);
				reviewboard.setRbNo(rset.getInt("rb_no"));
				reviewboard.setRbTitle(rset.getString("rb_title"));
				reviewboard.setRbContent(rset.getString("rb_content"));
				reviewboard.setRbCount(rset.getInt("rb_count"));
				reviewboard.setRbDate(rset.getDate("rb_date"));
				reviewboard.setRbFile1(rset.getString("rb_file1"));
				reviewboard.setRbFile2(rset.getString("rb_file2"));
				reviewboard.setpNo(rset.getInt("p_no"));
				
				list.add(reviewboard);
				
			if(list.size() == 0){
				throw new MemberException("게시글이 없습니다.");
			}
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	

	//관리자용 ---------------------------------------------------------
	
	//관리자용 회원등록
	public int mInsertMember(Connection con, Member m) throws MemberException {
		int result = 0;
		PreparedStatement pstmt = null;
 		String query = "insert into tb_member values " + "(?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";
 		try {
			pstmt = con.prepareStatement(query);
 			pstmt.setString(1, m.getmId());
			pstmt.setString(2, m.getmPassword());
			pstmt.setString(3, m.getmName());
			pstmt.setString(4, m.getmNickname());
			pstmt.setString(5, m.getmPhone());
			pstmt.setString(6, m.getmAddress());
			pstmt.setString(7, m.getmEmail());
			pstmt.setInt(8, m.getmPoint());
			pstmt.setString(9, m.getmSno());
			pstmt.setString(10, m.getmGender());
 			result = pstmt.executeUpdate();
 			if (result <= 0)
				throw new MemberException("회원 등록 실패");
 		} catch (Exception e) {
			// 나도 일단 보고
			e.printStackTrace();
			// MemberException 으로 전송
			throw new MemberException(e.getMessage());
		} finally {
			close(pstmt);
		}
 		return result;
	}
	
	//관리자용 페이징처리
	public int mGetListCount(Connection con) throws MemberException {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from tb_member";
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()){
				listCount = rset.getInt(1);
			}else{
				throw new MemberException("count 해서 페이지를 측정할만한 회원이 없습니다.");
			}
			
		} catch (Exception e) {
			//일단 나도 exception 보고
			e.printStackTrace();
			//던져주자
			throw new MemberException(e.getMessage());
		} finally {
			close(rset);
			close(stmt);		
		}
		//listCount = 10;
		return listCount;
	}
	
	//관리자용 회원리스트
	public ArrayList<Member> mSelectList(Connection con, int currentPage, int limit) throws MemberException{
		ArrayList<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from ("
				+ "select rownum rnum, "
				+ "m_id, "
				+ "m_password, "
				+ "m_name, "
				+ "m_nickname, "
				+ "m_phone, "
				+ "m_address, "
				+ "m_email, "
				+ "m_point, "
				+ "m_sno, "
				+ "m_gender "
				+ "from (select * from tb_member)) "
				+ "where rnum >= ? and rnum <= ?";
		
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Member m = new Member();
				
				m.setmId(rset.getString("m_id"));
				m.setmPassword(rset.getString("m_password"));
				m.setmName(rset.getString("m_name"));
				m.setmNickname(rset.getString("m_nickname"));
				m.setmPhone(rset.getString("m_phone"));
				m.setmAddress(rset.getString("m_address"));
				m.setmEmail(rset.getString("m_email"));
				m.setmPoint(rset.getInt("m_point"));
				m.setmSno(rset.getString("m_sno"));
				m.setmGender(rset.getString("m_gender"));
				
				
				list.add(m);
			}
			
			if(list.size() == 0)
				throw new MemberException("전체 조회할 회원이 없습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Member> mFilterSearch(Connection con, String filter, String value) throws MemberException {
		//관리자용 필터 회원 검색
		ArrayList<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		System.out.println("dao에서의 filter , value 값 : " + filter + ", " + value);
		String query = "select "
				+ "m_id, "
				+ "m_password, "
				+ "m_name, "
				+ "m_nickname, "
				+ "m_phone, "
				+ "m_address, "
				+ "m_email, "
				+ "m_point, "
				+ "m_sno, "
				+ "m_gender "
				+ "from tb_member ";
 		switch(filter){
		case "이름": query += "where m_name like ?"; break;
		case "아이디": query += "where m_id like ?"; break;
		case "닉네임": query += "where m_nickname like ?"; break;
		case "전화번호": query += "where m_phone like ?"; break;
		default: System.out.println("servelet : 필터 선택 잘못됨");
		}
		
	
		try {
			pstmt = con.prepareStatement(query);
			//pstmt.setString(1, filter);
			pstmt.setString(1, "%" + value + "%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Member m = new Member();
				
				m.setmId(rset.getString("m_id"));
				m.setmPassword(rset.getString("m_password"));
				m.setmName(rset.getString("m_name"));
				m.setmNickname(rset.getString("m_nickname"));
				m.setmPhone(rset.getString("m_phone"));
				m.setmAddress(rset.getString("m_address"));
				m.setmEmail(rset.getString("m_email"));
				m.setmPoint(rset.getInt("m_point"));
				m.setmSno(rset.getString("m_sno"));
				m.setmGender(rset.getString("m_gender"));
				
				
				list.add(m);
			}
			System.out.println("dao if 들어가기 전 listSize : " + list.size());
			//if(list.size() == 0)
				//throw new Member09Exception("검색 조건에 맞는 조회할 회원 없음.");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	//관리자용 회원수정
	public int mEditMember(Connection con, Member m) throws MemberException{
		int result = 0;
		PreparedStatement pstmt = null;
 		String query = "update tb_member set "
				+ "m_password = ?, "
				+ "m_name = ?, "
				+ "m_nickname = ?, "
				+ "m_phone = ?, "
				+ "m_address = ?, "
				+ "m_email = ?, "
				+ "m_point = ?, "
				+ "m_sno = ?, "
				+ "m_gender = ? "
				+ "where m_id = ?";
 		try {
			pstmt = con.prepareStatement(query);
 			//pstmt.setString(1, m.getmId());
			pstmt.setString(1, m.getmPassword());
			pstmt.setString(2, m.getmName());
			pstmt.setString(3, m.getmNickname());
			pstmt.setString(4, m.getmPhone());
			pstmt.setString(5, m.getmAddress());
			pstmt.setString(6, m.getmEmail());
			pstmt.setInt(7, m.getmPoint());
			pstmt.setString(8, m.getmSno());
			pstmt.setString(9, m.getmGender());
			pstmt.setString(10, m.getmId());
 			result = pstmt.executeUpdate();
 			if (result <= 0)
				throw new MemberException("회원 수정 실패");
 		} catch (Exception e) {
			// 나도 일단 보고
			e.printStackTrace();
			// MemberException 으로 전송
			throw new MemberException(e.getMessage());
		} finally {
			close(pstmt);
		}
 		return result;
	}
	//관리자용 end



	
}
