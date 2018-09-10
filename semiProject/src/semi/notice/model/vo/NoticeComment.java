package semi.notice.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class NoticeComment implements Serializable{
	private final static long serialVersionUID = 3L;
	
	private int commentNum; 			//댓글번호
	private int commentNotice;			//게시글번호
	private String commentId;			//댓글작성자
	private Date commentDate;			//댓글작성일
	private int commentParent;			//부모글
	private String commentContent;	//댓글내용
	
	public NoticeComment(){}

	
	
	
	public NoticeComment(int commentNum, int commentNotice, String commentContent) {
		super();
		this.commentNum = commentNum;
		this.commentNotice = commentNotice;
		this.commentContent = commentContent;
	}

	public NoticeComment(int commentNum, int commentNotice, String commentId, String commentContent) {
		super();
		this.commentNum = commentNum;
		this.commentNotice = commentNotice;
		this.commentId = commentId;
		this.commentContent = commentContent;
	}

	public NoticeComment(int commentNum, int commentNotice, String commentId, Date commentDate, String commentContent) {
		super();
		this.commentNum = commentNum;
		this.commentNotice = commentNotice;
		this.commentId = commentId;
		this.commentDate = commentDate;
		this.commentContent = commentContent;
	}




	public NoticeComment(int commentNum, int commentNotice, String commentId, Date commentDate, int commentParent,
			String commentContent) {
		super();
		this.commentNum = commentNum;
		this.commentNotice = commentNotice;
		this.commentId = commentId;
		this.commentDate = commentDate;
		this.commentParent = commentParent;
		this.commentContent = commentContent;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getCommentNotice() {
		return commentNotice;
	}

	public void setCommentNotice(int commentNotice) {
		this.commentNotice = commentNotice;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public int getCommentParent() {
		return commentParent;
	}

	public void setCommentParent(int commentParent) {
		this.commentParent = commentParent;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "NoticeComment [commentNum=" + commentNum + ", commentNotice=" + commentNotice + ", commentId="
				+ commentId + ", commentDate=" + commentDate + ", commentParent=" + commentParent + ", commentContent="
				+ commentContent + "]";
	}
	
	
	
	
	

}


































