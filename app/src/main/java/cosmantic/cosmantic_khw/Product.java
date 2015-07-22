package cosmantic.cosmantic_khw;

public class Product {
	// 멤버 변수
	private String objectId;	
	private String productName;		// 제품명
	private int price;				// 가격
	private String brand;				// 브랜드
	private byte[] thumnail;			// 썸네일
	private double size;				// 용량
	private String[] effects;			// 효능/태그
	private String skintype;			// 피부 타입
	private String type;				// 종류
	private String curatingInfo;		// 큐레이팅 정보
	
	// 기본 생성자
	Product(){}
	
	// 모든 정보를 다 받아오는 생성자
	Product(String productName, int price, String brand, double size, String[] effects, String skintype, String type, String curatingInfo){
		this.productName = productName;
		this.price = price;
		this.brand = brand;
		this.size = size;
		this.effects = effects;
		this.skintype = skintype;
		this.type = type;
		this.curatingInfo = curatingInfo;
	}
	
	// getter, setter
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public byte[] getThumnail() {
		return thumnail;
	}

	public void setThumnail(byte[] thumnail) {
		this.thumnail = thumnail;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public String[] getEffects() {
		return effects;
	}

	public void setEffects(String[] effects) {
		this.effects = effects;
	}

	public String getSkintype() {
		return skintype;
	}

	public void setSkintype(String skintype) {
		this.skintype = skintype;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCuratingInfo() {
		return curatingInfo;
	}

	public void setCuratingInfo(String curatingInfo) {
		this.curatingInfo = curatingInfo;
	}
	
	
}
