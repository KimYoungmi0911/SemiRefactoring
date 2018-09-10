package semi.products.model.vo;

public class Product implements java.io.Serializable {

	private final static long serialVersionUID = 12L;
	
	private int p_no;
	private String p_name;
	private int p_price;
	private int p_count;
	private String p_local;
	private String p_main_image;
	private String p_detail_image;
	private String p_item;
	private String p_state;
	private String gu;
	
	
	public String getGu() {
		return gu;
	}

	public void setGu(String gu) {
		this.gu = gu;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(int p_no, String p_name, int p_price, int p_count, String p_local, String p_main_image,
			String p_detail_image, String p_item, String p_state) {
		super();
		this.p_no = p_no;
		this.p_name = p_name;
		this.p_price = p_price;
		this.p_count = p_count;
		this.p_local = p_local;
		this.p_main_image = p_main_image;
		this.p_detail_image = p_detail_image;
		this.p_item = p_item;
		this.p_state = p_state;
	
	}

	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public int getP_price() {
		return p_price;
	}

	public void setP_price(int p_price) {
		this.p_price = p_price;
	}

	public int getP_count() {
		return p_count;
	}

	public void setP_count(int p_count) {
		this.p_count = p_count;
	}

	public String getP_local() {
		return p_local;
	}

	public void setP_local(String p_local) {
		this.p_local = p_local;
	}

	public String getP_main_image() {
		return p_main_image;
	}

	public void setP_main_image(String p_main_image) {
		this.p_main_image = p_main_image;
	}

	public String getP_detail_image() {
		return p_detail_image;
	}

	public void setP_detail_image(String p_detail_image) {
		this.p_detail_image = p_detail_image;
	}

	public String getP_item() {
		return p_item;
	}

	public void setP_item(String p_item) {
		this.p_item = p_item;
	}

	public String getP_state() {
		return p_state;
	}

	public void setP_state(String p_state) {
		this.p_state = p_state;
	}

	

	@Override
	public String toString() {
		return "Product [p_no=" + p_no + ", p_name=" + p_name + ", p_price=" + p_price + ", p_count=" + p_count
				+ ", p_local=" + p_local + ", p_main_image=" + p_main_image + ", p_detail_image=" + p_detail_image
				+ ", p_item=" + p_item + ", p_state=" + p_state ;
	}
	
	
	
	
	

	


	

	
	
}
