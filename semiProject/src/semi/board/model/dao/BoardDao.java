package semi.board.model.dao;

import static semi.common.JDBCTemplat.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import semi.board.exception.BoardException;
import semi.board.model.vo.Board;
import semi.rental.model.vo.Rental;

public class BoardDao {
	public BoardDao(){}
	
	//회원 물품 렌탈 전체 출력
			public ArrayList<Rental> selectList2(Connection con, String mId) 
					throws BoardException {
				ArrayList<Rental> list = new ArrayList<Rental>();
				//ArrayList list = new ArrayList();
				PreparedStatement pstmt = null;
				ResultSet rset = null;
				
				/*String query = "select * from ("
						+ "select rownum rnum, r_no, "
						+ "m_id, p_no, p_count, r_price, rental_date, rental_start_date, "
						+ "r_return_date, r_return_last_date, r_Booking_Date, p_state "
						+ "from (select * from tb_rental "
						+ "order by p_no desc)) ";*/
				String query = "select p_name, p_no from tb_product where p_no in (select p_no from tb_rental where m_id = ?) ";
				
				try {
					pstmt = con.prepareStatement(query);
					pstmt.setString(1, mId);

					rset = pstmt.executeQuery();
					
					while(rset.next()){
						Rental r = new Rental();
						r.setmId(rset.getString("p_name"));
						r.setpNo(rset.getInt("p_no"));
						//Object o = new Object();
						/*r.setrNo(rset.getString("r_no"));
						
						r.setpNo(rset.getInt("p_no"));
						r.setpCount(rset.getInt("p_count"));
						r.setrPrice(rset.getInt("r_price"));
						r.setrDate(rset.getString("rental_date"));
						r.setrStartDate(rset.getString("rental_start_date"));
						r.setrReturnDate(rset.getString("r_return_date"));
						r.setRReturnLastDate(rset.getString("r_return_last_date"));
						r.setrBookingDate(rset.getString("r_Booking_Date"));
						r.setpState(rset.getString("p_state"));*/
		
						list.add(r);
					}
						if(list.size() == 0)
							throw new BoardException("물품이없습니다.");
				
				} catch (Exception e) {
					e.printStackTrace();
					throw new BoardException(e.getMessage());
				}finally{
					close(rset);
					close(pstmt);
				}
				
				return list;
			}

	public ArrayList<Board> selectList(Connection con, int currentPage, int limit) 
			throws BoardException{
		ArrayList<Board> list = new ArrayList<Board>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from ("
				+ "select rownum rnum, rb_no, "
				+ "rb_title, rb_content, rb_count, "
				+ "rb_date, rb_file1, rb_file2, m_id, p_no "
				+ "from (select * from tb_reviewboard "
				+ "order by rb_no desc)) "
				+ "where rnum >= ? and rnum <= ?";
				
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Board b = new Board();
				
				b.setRb_no(rset.getInt("rb_no"));
				b.setRb_title(rset.getString("rb_title"));
				b.setRb_content(rset.getString("rb_content"));
				b.setRb_count(rset.getInt("rb_count"));
				b.setRb_date(rset.getDate("rb_date"));
				b.setRb_file1(rset.getString("rb_file1"));
				b.setRb_file2(rset.getString("rb_file2"));
				b.setM_id(rset.getString("m_id"));
				b.setP_no(rset.getInt("p_no"));
				
				list.add(b);
			}
				if(list.size() == 0)
					throw new BoardException("공지글이 없습니다.");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public int insertNotice(Connection con, Board board) 
	throws BoardException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into tb_reviewboard values "
				+ "((select max(rb_no) + 1 from tb_reviewboard), "
				+ "?, ?, default, sysdate, ?, ?, ?, ?)" ;
	
	try {
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, board.getRb_title());
		pstmt.setString(2, board.getRb_content());
		pstmt.setString(3, board.getRb_file1());
		pstmt.setString(4, board.getRb_file2());
		pstmt.setString(5, board.getM_id());
		pstmt.setInt(6, board.getP_no());

		result = pstmt.executeUpdate();
		
		if(result <= 0)
			throw new BoardException("새 공지글 등록 실패!!!");
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new BoardException(e.getMessage());
	}finally{
		close(pstmt);
	}	
	return result;
	}
	
	public Board selectNotice(Connection con, int boardNo) 
	throws BoardException{
		Board board = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from tb_reviewboard "
				+ "where rb_no =?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				board = new Board();
				
				board.setRb_no(boardNo);
				board.setRb_title(rset.getString("rb_title"));
				board.setRb_content(rset.getString("rb_content"));
				board.setRb_count(rset.getInt("rb_count"));
				board.setRb_date(rset.getDate("rb_date"));
				board.setRb_file1(rset.getString("rb_file1"));
				board.setRb_file2(rset.getString("rb_file2"));
				board.setM_id(rset.getString("m_id"));				
				board.setP_no(rset.getInt("p_no"));
								
			}else{
				throw new BoardException("후기글 조회 실패!!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return board;
	}
	
	public int addReadCount(Connection con, int boardNo) 
	throws BoardException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update tb_reviewboard "
				+ "set rb_count = rb_count + 1 "
				+ "where rb_no = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new BoardException(
						boardNo + "번 게시글 조회수 증가처리실패!!!");
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException(e.getMessage());
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	public int getListCount(Connection con) throws BoardException{
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from tb_reviewboard";
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()){
				listCount = rset.getInt(1);
			}else{
				throw new BoardException("게시글이 존재하지 않습니다!!!!!!!!!!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException(e.getMessage());
		}finally{
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}
	
	public int deleteNotice(Connection con, int boardNo) 
	throws BoardException{
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete from tb_reviewboard " + "where rb_no =?";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new BoardException("게시글 삭제 실패!!!!");

		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException(e.getMessage());
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateNotice(Connection con, Board board) 
	throws BoardException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "";
		if(board.getRb_file1() != null){
			query = "update tb_reviewboard set " 
					+ "rb_title = ?, " 
					+ "rb_content = ?, "
					+ "rb_file1 = ?, " 
					+ "rb_file2 = ?, " 
					+ "p_no = ? " 
					+ "where rb_no = ?";
		}else{
			query = "update tb_reviewboard set "
					+ "rb_title = ?, "
					+ "rb_content = ?, "
					+ "p_no = ? "
					+ "where rb_no = ?";
		}
		
		try {
			pstmt = con.prepareStatement(query);
			
		if(board.getRb_file1() != null || board.getRb_file2() != null){
			pstmt.setString(1, board.getRb_title());
			pstmt.setString(2, board.getRb_content());
			pstmt.setString(3, board.getRb_file1());
			pstmt.setString(4, board.getRb_file2());
			pstmt.setInt(5, board.getP_no());
			pstmt.setInt(6, board.getRb_no());
		}else{
			pstmt.setString(1, board.getRb_title());
			pstmt.setString(2, board.getRb_content());
			pstmt.setInt(3, board.getP_no());
			pstmt.setInt(4, board.getRb_no());
			
		}
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new BoardException("원글 수정 실패!!!!!");
			
		} catch (Exception e) {
			throw new BoardException(e.getMessage());
		}finally{
			close(pstmt);
		}
		
		
		return result;
	}

	//ajax 해당 게시글 조회 이름빼오기
	public String selectNotice2(Connection con, int boardNo) 
			throws BoardException{
		String pro = "";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select p_name from tb_product where p_no in ("
				+ "select p_no from tb_reviewboard where rb_no = ?)"; 
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNo);

			rset = pstmt.executeQuery();
			
			if(rset.next()){
				pro = rset.getString("p_name");
			
			}				

		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return pro;
	}
}














