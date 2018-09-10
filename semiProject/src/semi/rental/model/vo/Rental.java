package semi.rental.model.vo;

import java.io.Serializable;


public class Rental implements Serializable {
	
	private static final long serialVersionUID = 8L;
	
	private String rNo;
	private String mId;
	private int pNo;
	private int pCount;
	private int rPrice;
	private String rDate;
	private String rStartDate;
	private String rReturnDate;
	private String RReturnLastDate;
	private String rBookingDate;
	private String pState;
	
	public Rental() {}

	public Rental(String rNo, String mId, int pNo, int pCount, int rPrice, String rDate, String rStartDate,
			String rReturnDate, String rReturnLastDate, String rBookingDate, String pState) {
		super();
		this.rNo = rNo;
		this.mId = mId;
		this.pNo = pNo;
		this.pCount = pCount;
		this.rPrice = rPrice;
		this.rDate = rDate;
		this.rStartDate = rStartDate;
		this.rReturnDate = rReturnDate;
		RReturnLastDate = rReturnLastDate;
		this.rBookingDate = rBookingDate;
		this.pState = pState;
	}


	public String getrNo() {
		return rNo;
	}

	public void setrNo(String rNo) {
		this.rNo = rNo;
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

	public int getpCount() {
		return pCount;
	}

	public void setpCount(int pCount) {
		this.pCount = pCount;
	}

	public int getrPrice() {
		return rPrice;
	}

	public void setrPrice(int rPrice) {
		this.rPrice = rPrice;
	}

	public String getrDate() {
		return rDate;
	}

	public void setrDate(String rDate) {
		this.rDate = rDate;
	}

	public String getrStartDate() {
		return rStartDate;
	}

	public void setrStartDate(String rStartDate) {
		this.rStartDate = rStartDate;
	}

	public String getrReturnDate() {
		return rReturnDate;
	}

	public void setrReturnDate(String rReturnDate) {
		this.rReturnDate = rReturnDate;
	}

	public String getRReturnLastDate() {
		return RReturnLastDate;
	}

	public void setRReturnLastDate(String rReturnLastDate) {
		RReturnLastDate = rReturnLastDate;
	}

	public String getrBookingDate() {
		return rBookingDate;
	}

	public void setrBookingDate(String rBookingDate) {
		this.rBookingDate = rBookingDate;
	}

	public String getpState() {
		return pState;
	}

	public void setpState(String pState) {
		this.pState = pState;
	}

	@Override
	public String toString() {
		return "Rental [rNo=" + rNo + ", mId=" + mId + ", pNo=" + pNo + ", pCount=" + pCount + ", rPrice=" + rPrice
				+ ", rDate=" + rDate + ", rStartDate=" + rStartDate + ", rReturnDate=" + rReturnDate
				+ ", RReturnLastDate=" + RReturnLastDate + ", rBookingDate=" + rBookingDate + ", pState=" + pState
				+ "]";
	}

	
}
