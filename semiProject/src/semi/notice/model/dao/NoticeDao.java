package semi.notice.model.dao;


import static semi.common.JDBCTemplat.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import semi.notice.exception.NoticeException;
import semi.notice.model.vo.Notice;
import semi.notice.model.vo.NoticeComment;

public class NoticeDao {
	public NoticeDao(){}
	

	public ArrayList<Notice> selectList(Connection con, int currentPage, int limit) 
			throws  NoticeException {
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from ("
				+ "select rownum rnum, n_no, "
				+ "n_title, n_content, n_count, "
				+ "n_date, n_grade, "
				+ "n_file1, n_file2, a_id "
				+ "from (select * from tb_notice "
				+ "order by n_grade desc, n_no desc)) "
				+ "where rnum >= ? and rnum <= ?";
				
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Notice n = new Notice();
				
				n.setN_no(rset.getInt("n_no"));
				n.setN_title(rset.getString("n_title"));
				n.setN_content(rset.getString("n_content"));
				n.setN_count(rset.getInt("n_count"));
				n.setN_date(rset.getDate("n_date"));
				n.setN_grade(rset.getString("n_grade"));
				n.setN_file1(rset.getString("n_file2"));
				n.setN_file2(rset.getString("n_file1"));
				n.setA_id(rset.getString("a_id"));
			
				
				list.add(n);
			}
				if(list.size() == 0)
					throw new NoticeException("공지글이 없습니다.");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new NoticeException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public int insertNotice(Connection con, Notice notice) 
			throws NoticeException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into tb_notice values " 
					+ "((select max(n_no) + 1 from tb_notice), "
					+ "?, ?, default, sysdate, ?, ?, ?, ?)" ;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, notice.getN_title());
			pstmt.setString(2, notice.getN_content());
			pstmt.setString(3, notice.getN_grade());
			pstmt.setString(4, notice.getN_file1());
			pstmt.setString(5, notice.getN_file2());
			pstmt.setString(6, notice.getA_id());
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new NoticeException("새 공지글 등록 실패!!!");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new NoticeException(e.getMessage());
		}finally{
			close(pstmt);
		}
		
		
		return result;
	}

	public Notice selectNotice(Connection con, int noticeNo) 
			throws NoticeException{
		Notice notice = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from tb_notice " 
		+ "where n_no = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				notice = new Notice();
				
				notice.setN_no(noticeNo);
				notice.setN_title(rset.getString("n_title"));
				notice.setA_id(rset.getString("a_id"));
				notice.setN_grade(rset.getString("n_grade"));
				notice.setN_content(rset.getString("n_content"));
				notice.setN_file1(rset.getString("n_file1"));
				notice.setN_date(rset.getDate("n_date"));
				notice.setN_count(rset.getInt("n_count"));
				notice.setN_file2(rset.getString("n_file2"));
				
			}else{
				throw new NoticeException("공지글 상세 조회 실패!!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new NoticeException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return notice;
	}
	
	public int addReadCount(Connection con, int noticeNo) throws NoticeException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update tb_notice "
				+ "set n_count = n_count + 1 "
				+ "where n_no = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new NoticeException(
						noticeNo + "번 게시글 조회수 증가처리실패!!!");
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new NoticeException(e.getMessage());
		}finally{
			close(pstmt);
		}
		
		return result;
	}
	
	public int getListCount(Connection con) throws NoticeException{
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from tb_notice";
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()){
				listCount = rset.getInt(1);
			}else{
				throw new NoticeException("게시글이 존재하지 않습니다!!!!!!!!!!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new NoticeException(e.getMessage());
		}finally{
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}
	
	public int deleteNotice(Connection con, int noticeNo) throws NoticeException {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete from tb_notice " + "where n_no =?";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new NoticeException("게시글 삭제 실패!!!!");

		} catch (Exception e) {
			e.printStackTrace();
			throw new NoticeException(e.getMessage());
		} finally {
			close(pstmt);
		}

		return result;
	}
	

	public int updateNotice(Connection con, Notice notice) 
			throws NoticeException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "";
		if(notice.getN_file1() != null){
			query = "update tb_notice set " + "n_title = ?, " + "n_content = ?, " + "n_grade = ?, " 
						+ "n_file1 = ?, " + "n_file2 = ? "  + "where n_no = ?";
		}else{
			query = "update tb_notice set " 
						+ "n_title = ?, " 
						+ "n_content = ?, " 
						+ "n_grade = ? " 
						+ "where n_no = ?";
		}
		
		try {
			pstmt = con.prepareStatement(query);
			
		if(notice.getN_file1() != null || notice.getN_file2() != null){
			pstmt.setString(1, notice.getN_title());
			pstmt.setString(2, notice.getN_content());
			pstmt.setString(3, notice.getN_grade());
			pstmt.setString(4, notice.getN_file1());
			pstmt.setString(5, notice.getN_file2());
			pstmt.setInt(6, notice.getN_no());
		}else{
			pstmt.setString(1, notice.getN_title());
			pstmt.setString(2, notice.getN_content());
			pstmt.setString(3, notice.getN_grade());
			pstmt.setInt(4, notice.getN_no());
		}
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new NoticeException("원글 수정 실패!!!!!");
			
		} catch (Exception e) {
			throw new NoticeException(e.getMessage());
		}finally{
			close(pstmt);
		}
		
		
		return result;
	}
	
	//댓글달기처리용
		public int insertComment(Connection con, NoticeComment notice) throws NoticeException{
			
			int result = 0;
			PreparedStatement pstmt = null;
			
			String query = "insert into tb_comment values "
					+ "((select max(comment_num) + 1 from notice_comment), "
					+ "?, ?, sysdate, default, ?)";
			
		
			
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, notice.getCommentNotice());
				pstmt.setString(2, notice.getCommentId());
				pstmt.setString(3, notice.getCommentContent());
				
				result = pstmt.executeUpdate();
				
				if(result <= 0)
					throw new NoticeException("댓글 등록 실패DAO!!!!!");
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new NoticeException(e.getMessage());
			}finally{
				close(pstmt);
			}
					
			return result;
		}
	
	
	
}

















