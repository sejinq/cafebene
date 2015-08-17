package cosmantic.cosmantic_khw;

/*손!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!111세~~~~~~~~~~~~~~~~~~~~~~진@@@@@@@@@@@@@@@@@@@@@@@@@ 변수, 메소드 추가함.별점 스코어, 리뷰한 사람 수*/
public class Product {
	// 멤버 변수
	private String objectId;
	private String productName;		// 제품명
	private int price;				// 가격
	private String brand;			// 브랜드
	private byte[] thumnail;		// 썸네일
	private double size;			// 용량
	private int[] effects;		// 효능/태그
	private String skintype;		// 피부 타입
	private String type;			// 종류
	private String curatingInfo;	// 큐레이팅 정보
	private float score; //별점 스코어
	private int reviewNum; //리뷰한 사람 수

	// 기본 생성자
	Product(){}
	
	// 모든 정보를 다 받아오는 생성자
	Product(String productName, int price, String brand, double size, int[] effects, String skintype, String type, String curatingInfo, float score, int reviewNum){
		this.productName = productName;
		this.price = price;
		this.brand = brand;
		this.size = size;
		this.effects = effects;
		this.skintype = skintype;
		this.type = type;
		this.curatingInfo = curatingInfo;
		this.score = score;
		this.reviewNum = reviewNum;
	}
	
	// getter, setter
	public String getObjectId() { return objectId; }

	public String getProductName() {	return productName;	}

	public float getScore() {	return score;	}

	public int getReviewNum() {
		return reviewNum;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public void setReviewNum(int reviewNum) {
		this.reviewNum = reviewNum;
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

	public int[] getEffects() {
		return effects;
	}

	public void setEffects(int[] effects) {
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
