package semi.locationInfo.model.vo;

public class LocationInfo {

private static final long serialVersionUID = 4L;
	
	private int l_No;
	private String L_Name;
	private String l_Local;
	private String L_Address;
	
	public LocationInfo() {}

	public LocationInfo(int l_No, String l_Name, String l_Local, String l_Address) {
		super();
		this.l_No = l_No;
		L_Name = l_Name;
		this.l_Local = l_Local;
		L_Address = l_Address;
	}

	public int getL_No() {
		return l_No;
	}

	public void setL_No(int l_No) {
		this.l_No = l_No;
	}

	public String getL_Name() {
		return L_Name;
	}

	public void setL_Name(String l_Name) {
		L_Name = l_Name;
	}

	public String getL_Local() {
		return l_Local;
	}

	public void setL_Local(String l_Local) {
		this.l_Local = l_Local;
	}

	public String getL_Address() {
		return L_Address;
	}

	public void setL_Address(String l_Address) {
		L_Address = l_Address;
	}

	@Override
	public String toString() {
		return "LocationInfo [l_No=" + l_No + ", L_Name=" + L_Name + ", l_Local=" + l_Local + ", L_Address=" + L_Address
				+ "]";
	}
}
