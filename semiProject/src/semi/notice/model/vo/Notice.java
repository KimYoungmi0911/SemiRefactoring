package semi.notice.model.vo;

import java.io.Serializable;
import java.sql.Date;
import static semi.common.JDBCTemplat.*;

public class Notice implements Serializable{
	private final static long serialVersionUID = 3L;
	
	private int n_no;					//글번호
	private String n_title;			//제목
	private String n_content;		//내용
	private int n_count;		//조회수
	private Date n_date;				//작성날짜
	private String n_grade;			//공지등급
	private String n_file1;		//첨부파일1
	private String n_file2;	//첨부파일2
	private String a_id;			//작성자(관리자 아이디)
	
	public Notice(){}

	public Notice(int n_no, String n_title, String n_content) {
		super();
		this.n_no = n_no;
		this.n_title = n_title;
		this.n_content = n_content;
	}

	public Notice(int n_no, String n_title, String n_content, String a_id) {
		super();
		this.n_no = n_no;
		this.n_title = n_title;
		this.n_content = n_content;
		this.a_id = a_id;
	}

	public Notice(int n_no, String n_title, String n_content, String n_file1, String n_file2, String a_id) {
		super();
		this.n_no = n_no;
		this.n_title = n_title;
		this.n_content = n_content;
		this.n_file1 = n_file1;
		this.n_file2 = n_file2;
		this.a_id = a_id;
	}

	public Notice(int n_no, String n_title, String n_content, int n_count, Date n_date, String n_grade, String n_file1,
			String n_file2, String a_id) {
		super();
		this.n_no = n_no;
		this.n_title = n_title;
		this.n_content = n_content;
		this.n_count = n_count;
		this.n_date = n_date;
		this.n_grade = n_grade;
		this.n_file1 = n_file1;
		this.n_file2 = n_file2;
		this.a_id = a_id;
	}

	public int getN_no() {
		return n_no;
	}

	public void setN_no(int n_no) {
		this.n_no = n_no;
	}

	public String getN_title() {
		return n_title;
	}

	public void setN_title(String n_title) {
		this.n_title = n_title;
	}

	public String getN_content() {
		return n_content;
	}

	public void setN_content(String n_content) {
		this.n_content = n_content;
	}

	public int getN_count() {
		return n_count;
	}

	public void setN_count(int n_count) {
		this.n_count = n_count;
	}

	public Date getN_date() {
		return n_date;
	}

	public void setN_date(Date n_date) {
		this.n_date = n_date;
	}

	public String getN_grade() {
		return n_grade;
	}

	public void setN_grade(String n_grade) {
		this.n_grade = n_grade;
	}

	public String getN_file1() {
		return n_file1;
	}

	public void setN_file1(String n_file1) {
		this.n_file1 = n_file1;
	}

	public String getN_file2() {
		return n_file2;
	}

	public void setN_file2(String n_file2) {
		this.n_file2 = n_file2;
	}

	public String getA_id() {
		return a_id;
	}

	public void setA_id(String a_id) {
		this.a_id = a_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Notice [n_no=" + n_no + ", n_title=" + n_title + ", n_content=" + n_content + ", n_count=" + n_count
				+ ", n_date=" + n_date + ", n_grade=" + n_grade + ", n_file1=" + n_file1 + ", n_file2=" + n_file2
				+ ", a_id=" + a_id + "]";
	}
	
	

	
	
	
	
	
}


















