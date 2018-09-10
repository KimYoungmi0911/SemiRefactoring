package semi.qna.model.dao;

import static semi.common.JDBCTemplat.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import semi.board.exception.BoardException;
import semi.board.model.vo.Board;
import semi.notice.exception.NoticeException;
import semi.notice.model.vo.Notice;
import semi.products.model.vo.Product;
import semi.qna.exception.QnaException;
import semi.qna.model.vo.QnaBoard;
import semi.rental.model.vo.Rental;



public class QnaDao {
	
	//물품 전체 조회
	public ArrayList<Product> selectList1(Connection con) 
			throws QnaException{
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select  p_name, p_no from "
				+ "tb_product order by p_no asc";
	
		try {
			pstmt = con.prepareStatement(query);
		
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Product p = new Product();
				p.setP_name(rset.getString("p_name"));
				p.setP_no(rset.getInt("p_no"));
	
				list.add(p);
			}
				if(list.size() == 0)
					throw new QnaException("공지글이 없습니다.");

		} catch (Exception e) {
			e.printStackTrace();
			throw new QnaException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	


	//전체글 조회 
	public ArrayList<QnaBoard> selectList(Connection con, int currentPage, int limit) 
		throws QnaException{
		ArrayList<QnaBoard> list = new ArrayList<QnaBoard>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from ("
				+ "select rownum rnum, q_no, "
				+ "q_title, q_content, q_count, q_date, "
				+ "q_file1, q_file2, m_id, p_no, "
				+ "q_level, q_ref, q_reply_ref, q_reply_seq "
				+ "from (select * from tb_qboard "
				+ "order by q_ref desc, q_reply_ref desc, "
				+ "q_level asc, q_reply_seq asc)) "
				+ "where rnum >=? and rnum <= ?";
				
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				QnaBoard q = new QnaBoard();
				q.setQ_no(rset.getInt("q_no"));
				q.setQ_title(rset.getString("q_title"));
				q.setQ_content(rset.getString("q_content"));
				q.setQ_count(rset.getInt("q_count"));
				q.setQ_date(rset.getDate("q_date"));
				q.setQ_file1(rset.getString("q_file1"));
				q.setQ_file2(rset.getString("q_file2"));
				q.setM_id(rset.getString("m_id"));
				q.setP_no(rset.getInt("p_no"));
				q.setQ_level(rset.getInt("q_level"));
				q.setQ_ref(rset.getInt("q_ref"));
				q.setQ_reply_ref(rset.getInt("q_reply_ref"));
				q.setQ_reply_seq(rset.getInt("q_reply_seq"));
				
				list.add(q);
			}
				if(list.size() == 0)
					throw new QnaException("공지글이 없습니다.");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new QnaException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	//게시글 작성 
	public int insertNotice(Connection con, QnaBoard qna) 
		throws QnaException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into tb_qboard values " 
					+ "((select max(q_no) + 1 from tb_qboard), "
					+ "?, ?, default, sysdate, ?, ?, ?, ?, 0, "
					+ "(select max(q_no) + 1 from tb_qboard), "
					+ "null, default)" ;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, qna.getQ_title());
			pstmt.setString(2, qna.getQ_content());
			pstmt.setString(3, qna.getQ_file1());
			pstmt.setString(4, qna.getQ_file2());
			pstmt.setString(5, qna.getM_id());
			pstmt.setInt(6, qna.getP_no());
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new QnaException("새 공지글 등록 실패!!!");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new QnaException(e.getMessage());
		}finally{
			close(pstmt);
		}
		
		
		return result;
	}
	
	//해당게시글 조회
	public QnaBoard selectNotice(Connection con, int qnaNo) 
			throws QnaException{
		QnaBoard qna = null;
		Product pro = null;
		

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		/*String query = "select * from tb_qboard " 
		+ "where q_no = ?";	*/
		
		String query = "select q_title, q_content, q_count, q_date, q_file1, q_file2, "
				+ "m_id, p_no, q_level, q_ref, q_reply_ref, q_reply_seq, "
				+ "(select p_name from tb_product where p_no in (select p_no from tb_qboard where q_no = ?)) "
				+ "as p_name from tb_qboard where q_no = ? "; 
			
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			pstmt.setInt(2, qnaNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				qna = new QnaBoard();
				pro = new Product();
					
				qna.setQ_no(qnaNo);
				qna.setQ_title(rset.getString("q_title"));				
				qna.setQ_content(rset.getString("q_content"));
				qna.setQ_count(rset.getInt("q_count"));
				qna.setQ_date(rset.getDate("q_date"));
				qna.setQ_file1(rset.getString("q_file1"));
				qna.setQ_file2(rset.getString("q_file2"));
				qna.setM_id(rset.getString("m_id"));
				qna.setP_no(rset.getInt("p_no"));
				qna.setQ_level(rset.getInt("q_level"));
				qna.setQ_ref(rset.getInt("q_ref"));
				qna.setQ_reply_ref(rset.getInt("q_reply_ref"));
				qna.setQ_reply_seq(rset.getInt("q_reply_seq"));
				//pro.setP_name(rset.getString("p_name"));
					
			}else{
				throw new QnaException("공지글 상세 조회 실패!!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new QnaException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return qna;
	}
	
	//조회수 증가 처리용
	public int addReadCount(Connection con, int qnaNo) 
	throws QnaException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update tb_qboard "
				+ "set q_count = q_count + 1 "
				+ "where q_no = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new QnaException(
						qnaNo + "번 게시글 조회수 증가처리실패!!!");
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new QnaException(e.getMessage());
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	//조회수
	public int getListCount(Connection con) throws QnaException{
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from tb_qboard";
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()){
				listCount = rset.getInt(1);
			}else{
				throw new QnaException("게시글이 존재하지 않습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new QnaException(e.getMessage());
		}finally{
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}
	
	//게시글 삭제 처리용
	public int deleteNotice(Connection con, int qnaNo) 
		throws QnaException{
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete from tb_qboard " + "where q_no =?";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new QnaException("게시글 삭제 실패!!!!");

		} catch (Exception e) {
			e.printStackTrace();
			throw new QnaException(e.getMessage());
		} finally {
			close(pstmt);
		}

		return result;
	}

	//게시글 업데이트 처리용
		public int updateNotice(Connection con, QnaBoard qna) 
			throws QnaException{
			int result = 0;
			PreparedStatement pstmt = null;
			
			String query = "";
			if(qna.getQ_file1() != null || qna.getQ_file2() != null ){
				query = "update tb_qboard set " 
							+ "q_title = ?, " 
							+ "q_content = ?, " 
							+ "q_file1 = ?, " 
							+ "q_file2 = ?, "
							+ "p_no = ? "  
							+ "where q_no = ?";
			}else{
				query = "update tb_qboard set " 
							+ "q_title = ?, " 
							+ "q_content = ?, "
							+ "p_no = ? " 
							+ "where q_no = ?";
			}
			
			try {
				pstmt = con.prepareStatement(query);
				
			if(qna.getQ_file1() != null || qna.getQ_file2() != null){
				pstmt.setString(1, qna.getQ_title());
				pstmt.setString(2, qna.getQ_content());
				pstmt.setString(3, qna.getQ_file1());
				pstmt.setString(4, qna.getQ_file2());
				pstmt.setInt(5, qna.getP_no());
				pstmt.setInt(6, qna.getQ_no());
				
			}else{
				pstmt.setString(1, qna.getQ_title());
				pstmt.setString(2, qna.getQ_content());
				pstmt.setInt(3, qna.getP_no());
				pstmt.setInt(4, qna.getQ_no());
				
			}
				result = pstmt.executeUpdate();
				
				if(result <= 0)
					throw new QnaException("원글 수정 실패!!!!!");
				
			} catch (Exception e) {
				throw new QnaException(e.getMessage());
			}finally{
				close(pstmt);
			}
			
			
			return result;
		}

	//댓글 처리용
	public int updateReplySeq(Connection con, QnaBoard replyBoard) 
			throws QnaException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update tb_qboard set "
				+ "q_reply_seq = q_reply_seq + 1 "
				+ "where q_ref = ? "
				+ "and q_level = ? "
				+ "and q_reply_ref = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, replyBoard.getQ_ref());
			pstmt.setInt(2, replyBoard.getQ_level());
			pstmt.setInt(3, replyBoard.getQ_reply_ref());
			
			result = pstmt.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new QnaException(e.getMessage());
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	//댓글 추가용
	public int insertReply(Connection con, QnaBoard replyBoard) 
			throws QnaException{
		int result = 0;
		PreparedStatement pstmt = null;
		System.out.println(replyBoard.getQ_ref());
		System.out.println(replyBoard.getQ_level());
		String query = null;
		
		//원글의 댓글인 경우
		if(replyBoard.getQ_level() == 1){
			query = "insert into tb_qboard values ("
				+ "(select max(q_no) + 1 from tb_qboard), "
				+ "?, ?, default, sysdate, null, null, ?, null, ?, ?, "
				+ "(select max(q_no) + 1 from tb_qboard), "
				+ "1) ";
		}
		
		//댓글의 댓글인 경우
		if(replyBoard.getQ_level() == 2){
			query = "insert into tb_qboard values ("
				+ "(select max(q_no) + 1 from tb_qboard), "
				+ "?, ?, default, sysdate, null, null, ?, null, ?, ?, ?, 1) ";
		}
		
	
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, replyBoard.getQ_title());
			pstmt.setString(2, replyBoard.getQ_content());
			pstmt.setString(3, replyBoard.getM_id());
			pstmt.setInt(4, replyBoard.getQ_level());
			pstmt.setInt(5, replyBoard.getQ_ref());
			
			if(replyBoard.getQ_level() == 2)
				pstmt.setInt(6, replyBoard.getQ_reply_ref());
			
			if(replyBoard.getQ_level() == 3)
				pstmt.setInt(6, replyBoard.getQ_reply_ref());
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new QnaException(
					replyBoard.getQ_ref()
					+ "번글에 대한 댓글 달기 실패!");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new QnaException(e.getMessage());
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	public int updateReply(Connection con, QnaBoard qna) 
			throws QnaException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update tb_qboard set "
				+ "q_title = ?, "
				+ "q_content = ? "				
				+ "where q_no = ?";	
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, qna.getQ_title());			
			pstmt.setString(2, qna.getQ_content());			
			pstmt.setInt(3, qna.getQ_no());
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new QnaException("댓글 수정 실패!");			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new QnaException(e.getMessage());
		}finally{
			close(pstmt);
		}		
		
		return result;
	}





	/*public Product selectList2(Connection con, int p_no, int qnaNo) 
			throws QnaException{
		QnaBoard qna = null;
		Product pro = null;
		

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from tb_qboard " 
		+ "where q_no = ?";	
		String query = "select q_title, q_content, q_count, q_date, q_file1, q_file2, "
				+ "m_id, p_no, q_level, q_ref, q_reply_ref, q_reply_seq, "
				+ "(select p_name from tb_product where p_no in (select p_no from tb_qboard where q_no = ?)) "
				+ "as p_name from tb_qboard where q_no = ? "; 
			
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			pstmt.setInt(2, qnaNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				qna = new QnaBoard();
				pro = new Product();
					
				qna.setQ_no(qnaNo);
				qna.setQ_title(rset.getString("q_title"));				
				qna.setQ_content(rset.getString("q_content"));
				qna.setQ_count(rset.getInt("q_count"));
				qna.setQ_date(rset.getDate("q_date"));
				qna.setQ_file1(rset.getString("q_file1"));
				qna.setQ_file2(rset.getString("q_file2"));
				qna.setM_id(rset.getString("m_id"));
				qna.setP_no(rset.getInt("p_no"));
				qna.setQ_level(rset.getInt("q_level"));
				qna.setQ_ref(rset.getInt("q_ref"));
				qna.setQ_reply_ref(rset.getInt("q_reply_ref"));
				qna.setQ_reply_seq(rset.getInt("q_reply_seq"));
				qna.setP_no(rset.getInt("p_no"));
				pro.setP_name(rset.getString("p_name"));
				System.out.println("p_name : " + pro.getP_name());
					
			}else{
				throw new QnaException("공지글 상세 조회 실패!!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new QnaException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return pro;
	}*/

	//ajax 해당 게시글 조회
	public String selectNotice2(Connection con, int qnaNo) 
			throws QnaException{
		String pro = "";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		//QnaBoard qna = null;
		
		
		/*String query = "select  p_name, p_no from "
				+ "tb_product order by p_no asc";*/

		String query = "select q_title, q_content, q_count, q_date, q_file1, q_file2, "
				+ "m_id, p_no, q_level, q_ref, q_reply_ref, q_reply_seq, "
				+ "(select p_name from tb_product where p_no in (select p_no from tb_qboard where q_no = ?)) "
				+ "as p_name from tb_qboard where q_no = ? "; 

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			pstmt.setInt(2, qnaNo);

			rset = pstmt.executeQuery();
			
			if(rset.next()){
				pro = rset.getString("p_name");
			
			}				

		} catch (Exception e) {
			e.printStackTrace();
			throw new QnaException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return pro;
	}


	

	

	

	

}
