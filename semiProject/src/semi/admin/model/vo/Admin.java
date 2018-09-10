package semi.admin.model.vo;

public class Admin implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private String aId;
	private String aPassword;
	private String aName;
	private String aNickname;
	private String aGrade;
	private int lNo;
	
	public Admin(){}

	public Admin(String aId, String aPassword, String aName, String aNickname, String aGrade, int lNo) {
		super();
		this.aId = aId;
		this.aPassword = aPassword;
		this.aName = aName;
		this.aNickname = aNickname;
		this.aGrade = aGrade;
		this.lNo = lNo;
	}

	public String getaId() {
		return aId;
	}

	public void setaId(String aId) {
		this.aId = aId;
	}

	public String getaPassword() {
		return aPassword;
	}

	public void setaPassword(String aPassword) {
		this.aPassword = aPassword;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public String getaNickname() {
		return aNickname;
	}

	public void setaNickname(String aNickname) {
		this.aNickname = aNickname;
	}

	public String getaGrade() {
		return aGrade;
	}

	public void setaGrade(String aGrade) {
		this.aGrade = aGrade;
	}

	public int getlNo() {
		return lNo;
	}

	public void setlNo(int lNo) {
		this.lNo = lNo;
	}

	@Override
	public String toString() {
		return aId + ", " + aPassword + ", " + aName + ", " + aNickname
				+ ", " + aGrade + ", " + lNo;
	}

	
	
}
