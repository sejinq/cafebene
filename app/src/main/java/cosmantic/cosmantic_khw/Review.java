package cosmantic.cosmantic_khw;

public class Review {
	// 멤버 변수
	private String objectId;
	private String productObjectId;	// User와 관련 있다.
	private String userObjectId;		// Product와 관련 있다.
	private double rate;		// 별점
	private String content;		// 내용
	
	// 기본 생성자
	Review(){}
	
	// 모든 정보를 다 받는 생성자
	Review(String productObjectId, String userObjectId, double rate, String content){
		this.productObjectId = productObjectId;
		this.userObjectId = userObjectId;
		this.rate = rate;
		this.content = content;
	}
	
	// getter, setter
	public String getObjectId() { return objectId; }

	public String getProductObjectId() { return productObjectId; }

	public void setProductObjectId(String productObjectId) {
		this.productObjectId = productObjectId;
	}

	public String getUserObjectId() {
		return userObjectId;
	}

	public void setUserObjectId(String userObjectId) {
		this.userObjectId = userObjectId;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}	
}