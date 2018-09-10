package semi.cart.model.vo;

public class Cart implements java.io.Serializable{
	private static final long serialVersionUID = 3L;
	
	private int p_No;
	private String p_Name;
	private int p_Price;
	private int p_Count;
	private String p_Local;
	private String p_Main_Image;
	private String p_Detail_Image;
	private String p_Content;
	private String p_Item;
	private String p_State;
	
	public Cart() {}

	public Cart(int p_No, String p_Name, int p_Price, int p_Count, String p_Local, String p_Main_Image,
			String p_Detail_Image, String p_Content, String p_Item, String p_State) {
		super();
		this.p_No = p_No;
		this.p_Name = p_Name;
		this.p_Price = p_Price;
		this.p_Count = p_Count;
		this.p_Local = p_Local;
		this.p_Main_Image = p_Main_Image;
		this.p_Detail_Image = p_Detail_Image;
		this.p_Content = p_Content;
		this.p_Item = p_Item;
		this.p_State = p_State;
	}

	public int getP_No() {
		return p_No;
	}

	public void setP_No(int p_No) {
		this.p_No = p_No;
	}

	public String getP_Name() {
		return p_Name;
	}

	public void setP_Name(String p_Name) {
		this.p_Name = p_Name;
	}

	public int getP_Price() {
		return p_Price;
	}

	public void setP_Price(int p_Price) {
		this.p_Price = p_Price;
	}

	public int getP_Count() {
		return p_Count;
	}

	public void setP_Count(int p_Count) {
		this.p_Count = p_Count;
	}

	public String getP_Local() {
		return p_Local;
	}

	public void setP_Local(String p_Local) {
		this.p_Local = p_Local;
	}

	public String getP_Main_Image() {
		return p_Main_Image;
	}

	public void setP_Main_Image(String p_Main_Image) {
		this.p_Main_Image = p_Main_Image;
	}

	public String getP_Detail_Image() {
		return p_Detail_Image;
	}

	public void setP_Detail_Image(String p_Detail_Image) {
		this.p_Detail_Image = p_Detail_Image;
	}

	public String getP_Content() {
		return p_Content;
	}

	public void setP_Content(String p_Content) {
		this.p_Content = p_Content;
	}

	public String getP_Item() {
		return p_Item;
	}

	public void setP_Item(String p_Item) {
		this.p_Item = p_Item;
	}

	public String getP_State() {
		return p_State;
	}

	public void setP_State(String p_State) {
		this.p_State = p_State;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return this.p_No + ", " + this.p_Name + ", " + this.p_Price + ", " + this.p_Count + ", " + 
				this.p_Local + ", " + this.p_Main_Image + ", " + this.p_Detail_Image + ", " + 
				this.p_Content + ", " + this.p_Item + ", " + this.p_State;
	}
	
	
	/*P_NO
	P_NAME
	P_PRICE
	P_COUNT
	P_LOCAL
	P_MAIN_IMAGE
	P_DETAIL_IMAGE
	P_CONTENT
	P_ITEM
	P_STATE*/
}
