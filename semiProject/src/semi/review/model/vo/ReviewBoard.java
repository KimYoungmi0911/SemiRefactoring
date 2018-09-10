package semi.review.model.vo;

import java.sql.Date;

public class ReviewBoard implements java.io.Serializable{

	private final static long serialVersionUID = 16L;
	
	private int rbNo;
	private String rbTitle;
	private String rbContent;
	private int rbCount;
	private Date rbDate;
	private String rbFile1;
	private String rbFile2;
	private String mId;
	private int pNo;
	
	public ReviewBoard(){}

	public ReviewBoard(int rbNo, String rbTitle, String rbContent, int rbCount, Date rbDate, String rbFile1,
			String rbFile2, String mId, int pNo) {
		super();
		this.rbNo = rbNo;
		this.rbTitle = rbTitle;
		this.rbContent = rbContent;
		this.rbCount = rbCount;
		this.rbDate = rbDate;
		this.rbFile1 = rbFile1;
		this.rbFile2 = rbFile2;
		this.mId = mId;
		this.pNo = pNo;
	}

	public int getRbNo() {
		return rbNo;
	}

	public void setRbNo(int rbNo) {
		this.rbNo = rbNo;
	}

	public String getRbTitle() {
		return rbTitle;
	}

	public void setRbTitle(String rbTitle) {
		this.rbTitle = rbTitle;
	}

	public String getRbContent() {
		return rbContent;
	}

	public void setRbContent(String rbContent) {
		this.rbContent = rbContent;
	}

	public int getRbCount() {
		return rbCount;
	}

	public void setRbCount(int rbCount) {
		this.rbCount = rbCount;
	}

	public Date getRbDate() {
		return rbDate;
	}

	public void setRbDate(Date rbDate) {
		this.rbDate = rbDate;
	}

	public String getRbFile1() {
		return rbFile1;
	}

	public void setRbFile1(String rbFile1) {
		this.rbFile1 = rbFile1;
	}

	public String getRbFile2() {
		return rbFile2;
	}

	public void setRbFile2(String rbFile2) {
		this.rbFile2 = rbFile2;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ReviewBoard [rbNo=" + rbNo + ", rbTitle=" + rbTitle + ", rbContent=" + rbContent + ", rbCount="
				+ rbCount + ", rbDate=" + rbDate + ", rbFile1=" + rbFile1 + ", rbFile2=" + rbFile2 + ", mId=" + mId
				+ ", pNo=" + pNo + "]";
	}
	
	
}
