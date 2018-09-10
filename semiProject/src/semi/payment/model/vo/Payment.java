package semi.payment.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Payment implements Serializable {

	private static final long serialVersionUID = 7L;
	
	private String rNo;
	private String mId;
	private Date payDate;
	private String payType;
	private int payPrice;
	private String payConfirm;
	
	public Payment() {}

	public Payment(String rNo, String mId, Date payDate, String payType, int payPrice, String payConfirm) {
		super();
		this.rNo = rNo;
		this.mId = mId;
		this.payDate = payDate;
		this.payType = payType;
		this.payPrice = payPrice;
		this.payConfirm = payConfirm;
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

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public int getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(int payPrice) {
		this.payPrice = payPrice;
	}

	public String getPayConfirm() {
		return payConfirm;
	}

	public void setPayConfirm(String payConfirm) {
		this.payConfirm = payConfirm;
	}

	@Override
	public String toString() {
		return "Payment [rNo=" + rNo + ", mId=" + mId + ", payDate=" + payDate + ", payType=" + payType + ", payPrice="
				+ payPrice + ", payConfirm=" + payConfirm + "]";
	}
	
	
}
