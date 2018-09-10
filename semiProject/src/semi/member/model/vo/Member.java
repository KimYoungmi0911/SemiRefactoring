package semi.member.model.vo;

public class Member implements java.io.Serializable{
	private static final long serialVersionUID = 6L;
	
	private String mId;
	private String mPassword;
	private String mName;
	private String mNickname;
	private String mPhone;
	private String mAddress;
	private String mEmail;
	private int mPoint;
	private String mSno;
	private String mGender;
	private String mPwd;
	
	public Member(){}

	public Member(String mId, String mPassword, String mName, String mNickname, String mPhone, String mAddress,
			String mEmail, int mPoint, String mSno, String mGender, String mPwd) {
		super();
		this.mId = mId;
		this.mPassword = mPassword;
		this.mName = mName;
		this.mNickname = mNickname;
		this.mPhone = mPhone;
		this.mAddress = mAddress;
		this.mEmail = mEmail;
		this.mPoint = mPoint;
		this.mSno = mSno;
		this.mGender = mGender;
		this.mPwd = mPwd;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmPassword() {
		return mPassword;
	}

	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmNickname() {
		return mNickname;
	}

	public void setmNickname(String mNickname) {
		this.mNickname = mNickname;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public String getmAddress() {
		return mAddress;
	}

	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public int getmPoint() {
		return mPoint;
	}

	public void setmPoint(int mPoint) {
		this.mPoint = mPoint;
	}

	public String getmSno() {
		return mSno;
	}

	public void setmSno(String mSno) {
		this.mSno = mSno;
	}

	public String getmGender() {
		return mGender;
	}

	public void setmGender(String mGender) {
		this.mGender = mGender;
	}
	public String getmPwd(){
		return mPwd;
	}
	public void setmPwd(String mPwd){
		this.mPwd = mPwd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString(){
		return mId + ", "
				+ mPassword + ", "
				+ mName + ", "
				+ mNickname + ", "
				+ mPhone + ", "
				+ mAddress + ", "
				+ mEmail + ", "
				+ mPoint + ", "
				+ mSno + ", "
				+ mGender + ", "
				+ mPwd;
	}
	
	
}
