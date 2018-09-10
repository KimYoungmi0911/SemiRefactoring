package semi.qna.model.service;

import static semi.common.JDBCTemplat.close;
import static semi.common.JDBCTemplat.commit;
import static semi.common.JDBCTemplat.getConnection;
import static semi.common.JDBCTemplat.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import semi.board.model.dao.BoardDao;
import semi.board.model.vo.Board;
import semi.notice.model.dao.NoticeDao;
import semi.notice.model.vo.Notice;
import semi.products.model.vo.Product;
import semi.qna.exception.QnaException;
import semi.qna.model.dao.QnaDao;
import semi.qna.model.vo.QnaBoard;
import semi.rental.model.vo.Rental;


public class QnaService {
	public QnaService(){}
	
	//물품 전체 출력
	public ArrayList<Product> selectList1() 
			throws QnaException{
		Connection con = getConnection();
		ArrayList<Product> list = new QnaDao().selectList1(con);
		close(con);
		return list;
	}

	//글전체조회
	public ArrayList<QnaBoard> selectList(int currentPage, int limit) 
			throws QnaException{
		Connection con = getConnection();
		ArrayList<QnaBoard> list = new QnaDao().selectList(con, currentPage, limit);
		close(con);
		return list;
	}
	
	//글작성
	public int insertNotice(QnaBoard qna) throws QnaException{
		Connection con = getConnection();
		int result = new QnaDao().insertNotice(con, qna);
		
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		
		return result;
	}
	
	//해당 게시글 조회
	public QnaBoard selectNotice(int qnaNo ) throws QnaException{
		Connection con = getConnection();
		QnaBoard qna = new QnaDao().selectNotice(con, qnaNo);
		close(con);
		return qna;
	}
	
	//조회수
	public void addReadCount(int qnaNo) throws QnaException{
		Connection con = getConnection();
		int result = new QnaDao().addReadCount(con, qnaNo);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		
	}

	//조회수
	public int getListConut() throws QnaException{
		Connection con = getConnection();
		int listCount = new QnaDao().getListCount(con);
		close(con);
		return listCount;
	}
	
	
	//글삭제
	public int deleteNotice(int qnaNo) throws QnaException{
		Connection con = getConnection();
		int result = new QnaDao().deleteNotice(con, qnaNo);
		
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);		
		return result;
	}

	//글 수정
	public int updateNotice(QnaBoard qna) throws QnaException{
		Connection con = getConnection();
		int result = new QnaDao().updateNotice(con, qna);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);		
		return result;
	}

	//댓글 수정
	public void updateReplySeq(QnaBoard replyBoard) 
		throws QnaException{
		Connection con = getConnection();
		int result = new QnaDao().updateReplySeq(
				con, replyBoard);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		
	}

	//댓글 추가
	public int insertReply(QnaBoard replyBoard) 
		throws QnaException{
		Connection con = getConnection();
		int result = new QnaDao().insertReply(con, replyBoard);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	//댓글 수정
	public int updateReply(QnaBoard qna) 
			throws QnaException {
		Connection con = getConnection();
		int result = new QnaDao().updateReply(con, qna);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	//ajax 게시판 디테일
	public String selectNotice2(int qnaNo) 
			throws QnaException{
		Connection con = getConnection();
		String qname = new QnaDao().selectNotice2(con, qnaNo);
		close(con);
		return qname;
	}

	
	

	

	
	

	
	


	

	

	

	

	
}
