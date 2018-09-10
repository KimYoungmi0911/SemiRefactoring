package semi.qna.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class QnaBoard implements Serializable{
	private final static long serialVersionUID = 3L;
	
	private int q_no;					//글번호
	private String q_title;				//제목
	private String q_content;			//내용
	private int q_count;				//조회수
	private Date q_date;				//작성날짜
	private String q_file1;				//첨부파일1
	private String q_file2;				//첨부파일2
	private String m_id;				//작성자(회원아이디)
	private int p_no;					//물품번호
	private int q_level;					//글레벨
	private int q_ref;					//참조원글번호
	private int q_reply_ref;			//참조답글번호
	private int q_reply_seq;			//답글순번
	
	public QnaBoard(){}

	public QnaBoard(int q_no, String q_title, String q_content) {
		super();
		this.q_no = q_no;
		this.q_title = q_title;
		this.q_content = q_content;
	}

	public QnaBoard(int q_no, String q_title, String q_content, String m_id) {
		super();
		this.q_no = q_no;
		this.q_title = q_title;
		this.q_content = q_content;
		this.m_id = m_id;
	}

	public QnaBoard(int q_no, String q_title, String q_content, String q_file1, String q_file2) {
		super();
		this.q_no = q_no;
		this.q_title = q_title;
		this.q_content = q_content;
		this.q_file1 = q_file1;
		this.q_file2 = q_file2;
	}

	public QnaBoard(String q_title, String q_content, String q_file1, String q_file2, String m_id) {
		super();
		this.q_title = q_title;
		this.q_content = q_content;
		this.q_file1 = q_file1;
		this.q_file2 = q_file2;
		this.m_id = m_id;
	}

	public QnaBoard(int q_no, String q_title, String q_content, int q_count, Date q_date, String q_file1,
			String q_file2, String m_id, int p_no, int q_level, int q_ref, int q_reply_ref, int q_reply_seq) {
		super();
		this.q_no = q_no;
		this.q_title = q_title;
		this.q_content = q_content;
		this.q_count = q_count;
		this.q_date = q_date;
		this.q_file1 = q_file1;
		this.q_file2 = q_file2;
		this.m_id = m_id;
		this.p_no = p_no;
		this.q_level = q_level;
		this.q_ref = q_ref;
		this.q_reply_ref = q_reply_ref;
		this.q_reply_seq = q_reply_seq;
	}

	public int getQ_no() {
		return q_no;
	}

	public void setQ_no(int q_no) {
		this.q_no = q_no;
	}

	public String getQ_title() {
		return q_title;
	}

	public void setQ_title(String q_title) {
		this.q_title = q_title;
	}

	public String getQ_content() {
		return q_content;
	}

	public void setQ_content(String q_content) {
		this.q_content = q_content;
	}

	public int getQ_count() {
		return q_count;
	}

	public void setQ_count(int q_count) {
		this.q_count = q_count;
	}

	public Date getQ_date() {
		return q_date;
	}

	public void setQ_date(Date q_date) {
		this.q_date = q_date;
	}

	public String getQ_file1() {
		return q_file1;
	}

	public void setQ_file1(String q_file1) {
		this.q_file1 = q_file1;
	}

	public String getQ_file2() {
		return q_file2;
	}

	public void setQ_file2(String q_file2) {
		this.q_file2 = q_file2;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

	public int getQ_level() {
		return q_level;
	}

	public void setQ_level(int q_level) {
		this.q_level = q_level;
	}

	public int getQ_ref() {
		return q_ref;
	}

	public void setQ_ref(int q_ref) {
		this.q_ref = q_ref;
	}

	public int getQ_reply_ref() {
		return q_reply_ref;
	}

	public void setQ_reply_ref(int q_reply_ref) {
		this.q_reply_ref = q_reply_ref;
	}

	public int getQ_reply_seq() {
		return q_reply_seq;
	}

	public void setQ_reply_seq(int q_reply_seq) {
		this.q_reply_seq = q_reply_seq;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "QnaBoard [q_no=" + q_no + ", q_title=" + q_title + ", q_content=" + q_content + ", q_count=" + q_count
				+ ", q_date=" + q_date + ", q_file1=" + q_file1 + ", q_file2=" + q_file2 + ", m_id=" + m_id + ", p_no="
				+ p_no + ", q_level=" + q_level + ", q_ref=" + q_ref + ", q_reply_ref=" + q_reply_ref + ", q_reply_seq="
				+ q_reply_seq + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
