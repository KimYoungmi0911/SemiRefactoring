package semi.notice.model.service;

import static semi.common.JDBCTemplat.close;
import static semi.common.JDBCTemplat.commit;
import static semi.common.JDBCTemplat.getConnection;
import static semi.common.JDBCTemplat.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import semi.notice.exception.NoticeException;
import semi.notice.model.dao.NoticeDao;
import semi.notice.model.vo.Notice;
import semi.notice.model.vo.NoticeComment;


public class NoticeGongService {
	public NoticeGongService(){}

	public ArrayList<Notice> selectList(int currentPage, int limit) throws NoticeException{
		Connection con = getConnection();
		ArrayList<Notice> list = new NoticeDao().selectList(con, currentPage, limit);
		close(con);
		return list;
	}

	public int insertNotice(Notice notice) throws NoticeException {
		Connection con = getConnection();
		int result = new NoticeDao().insertNotice(con, notice);
		
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		
		return result;
	}

	public Notice selectNotice(int noticeNo) throws NoticeException{
		Connection con = getConnection();
		Notice notice = new NoticeDao().selectNotice(con, noticeNo);
		close(con);
		return notice;
	}

	public void addReadCount(int noticeNo) throws NoticeException{
		Connection con = getConnection();
		int result = new NoticeDao().addReadCount(con, noticeNo);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		
	}

	public int getListConut() throws NoticeException{
		Connection con = getConnection();
		int listCount = new NoticeDao().getListCount(con);
		close(con);
		return listCount;
		
	}

	public int deleteNotice(int noticeNo) throws NoticeException{
		Connection con = getConnection();
		int result = new NoticeDao().deleteNotice(con, noticeNo);
		
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);		
		return result;
	}

	public int updateNotice(Notice notice) throws NoticeException{
		Connection con = getConnection();
		int result = new NoticeDao().updateNotice(con, notice);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);		
		return result;
	}

	//댓글달기 처리용
	public int insertComment(NoticeComment notice) throws NoticeException{
		Connection con = getConnection();
		int result = new NoticeDao().insertComment(con, notice);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		
		return result;
	}
	
	

}


























