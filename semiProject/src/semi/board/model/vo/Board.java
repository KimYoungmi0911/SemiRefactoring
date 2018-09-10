package semi.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable{
	private final static long serialVersionUID = 3L;
	
	private int rb_no;				//글번호
	private String rb_title;			//제목
	private String rb_content;		//내용
	private int rb_count;			//조회수
	private Date rb_date;			//작성날짜
	private String rb_file1;			//첨부파일1
	private String rb_file2;			//첨부파일2
	private String m_id;			//작성자(회원아이디)
	private int p_no;			//물품이름
	
	public Board(){}

	public Board(int rb_no, String rb_title, String rb_content) {
		super();
		this.rb_no = rb_no;
		this.rb_title = rb_title;
		this.rb_content = rb_content;
	}

	public Board(int rb_no, String rb_title, String rb_content, String m_id) {
		super();
		this.rb_no = rb_no;
		this.rb_title = rb_title;
		this.rb_content = rb_content;
		this.m_id = m_id;
	}

	public Board(int rb_no, String rb_title, String rb_content, String rb_file1, String rb_file2, String m_id) {
		super();
		this.rb_no = rb_no;
		this.rb_title = rb_title;
		this.rb_content = rb_content;
		this.rb_file1 = rb_file1;
		this.rb_file2 = rb_file2;
		this.m_id = m_id;
	}

	public Board(int rb_no, String rb_title, String rb_content, int rb_count, Date rb_date, String rb_file1,
			String rb_file2, String m_id, int p_no) {
		super();
		this.rb_no = rb_no;
		this.rb_title = rb_title;
		this.rb_content = rb_content;
		this.rb_count = rb_count;
		this.rb_date = rb_date;
		this.rb_file1 = rb_file1;
		this.rb_file2 = rb_file2;
		this.m_id = m_id;
		this.p_no = p_no;
	}

	public int getRb_no() {
		return rb_no;
	}

	public void setRb_no(int rb_no) {
		this.rb_no = rb_no;
	}

	public String getRb_title() {
		return rb_title;
	}

	public void setRb_title(String rb_title) {
		this.rb_title = rb_title;
	}

	public String getRb_content() {
		return rb_content;
	}

	public void setRb_content(String rb_content) {
		this.rb_content = rb_content;
	}

	public int getRb_count() {
		return rb_count;
	}

	public void setRb_count(int rb_count) {
		this.rb_count = rb_count;
	}

	public Date getRb_date() {
		return rb_date;
	}

	public void setRb_date(Date rb_date) {
		this.rb_date = rb_date;
	}

	public String getRb_file1() {
		return rb_file1;
	}

	public void setRb_file1(String rb_file1) {
		this.rb_file1 = rb_file1;
	}

	public String getRb_file2() {
		return rb_file2;
	}

	public void setRb_file2(String rb_file2) {
		this.rb_file2 = rb_file2;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Board [rb_no=" + rb_no + ", rb_title=" + rb_title + ", rb_content=" + rb_content + ", rb_count="
				+ rb_count + ", rb_date=" + rb_date + ", rb_file1=" + rb_file1 + ", rb_file2=" + rb_file2 + ", m_id="
				+ m_id + ", p_no=" + p_no + "]";
	}


	
	
	
	
	

}































