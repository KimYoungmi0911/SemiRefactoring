package semi.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import semi.board.exception.BoardException;
import semi.board.model.dao.BoardDao;
import semi.board.model.vo.Board;
import semi.products.model.vo.Product;
import semi.qna.exception.QnaException;
import semi.qna.model.dao.QnaDao;
import semi.rental.model.vo.Rental;

import static semi.common.JDBCTemplat.*;

public class BoardService {
	public BoardService(){}
	
	//회원 물품 렌탈 전체 출력
			public ArrayList<Rental> selectList2(String mId) 
					throws BoardException{
				Connection con = getConnection();
				ArrayList<Rental> list = new BoardDao().selectList2(con, mId);
				close(con);
				return list;
			}

			//게시판 전체출력
	public ArrayList<Board> selectList(int currentPage, int limit) 
			throws BoardException{
		Connection con = getConnection();
		ArrayList<Board> list = new BoardDao().selectList(con, currentPage, limit);
		close(con);
		return list;
	}
	

	//글작성
	public int insertNotice(Board board) throws BoardException{
		Connection con = getConnection();
		int result = new BoardDao().insertNotice(con, board);
		
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		
		return result;
	}
	
	//해당게시글
	public Board selectNotice(int boardNo) throws BoardException{
		Connection con = getConnection();
		Board board = new BoardDao().selectNotice(con, boardNo);
		close(con);
		return board;
	}
	
	//조회수
	public void addReadCount(int boardNo) throws BoardException{
		Connection con = getConnection();
		int result = new BoardDao().addReadCount(con, boardNo);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		
	}

	//조회수
	public int getListConut() throws BoardException{
		Connection con = getConnection();
		int listCount = new BoardDao().getListCount(con);
		close(con);
		return listCount;
	}
	
	//글삭제
	public int deleteNotice(int boardNo) throws BoardException{
		Connection con = getConnection();
		int result = new BoardDao().deleteNotice(con, boardNo);
		
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;		
	}

	//글수정
	public int updateNotice(Board board) throws BoardException{
		Connection con = getConnection();
		int result = new BoardDao().updateNotice(con, board);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);		
		return result;
	}

	public String selectNotice2(int boardNo) 
			throws BoardException{
		Connection con = getConnection();
		String qname = new BoardDao().selectNotice2(con, boardNo);
		close(con);
		return qname;
	}
}
