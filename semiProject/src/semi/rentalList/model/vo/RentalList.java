package semi.rentalList.model.vo;

import java.io.Serializable;

public class RentalList implements Serializable {

	private final static long serialVersionUID = 9L;
	
	private String rNo;
	private String mId;
	private int rTotalCount;
	private int rTotalMoney;
	
	public RentalList() {}

	public RentalList(String rNo, String mId, int rTotalCount, int rTotalMoney) {
		super();
		this.rNo = rNo;
		this.mId = mId;
		this.rTotalCount = rTotalCount;
		this.rTotalMoney = rTotalMoney;
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

	public int getrTotalCount() {
		return rTotalCount;
	}

	public void setrTotalCount(int rTotalCount) {
		this.rTotalCount = rTotalCount;
	}

	public int getrTotalMoney() {
		return rTotalMoney;
	}

	public void setrTotalMoney(int rTotalMoney) {
		this.rTotalMoney = rTotalMoney;
	}

	@Override
	public String toString() {
		return "RentalList [rNo=" + rNo + ", mId=" + mId + ", rTotalCount=" + rTotalCount + ", rTotalMoney="
				+ rTotalMoney + "]";
	}
	
	
}
