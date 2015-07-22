package cosmantic.cosmantic_khw;

public class Review {
	// 멤버 변수
	private String objectId;
	private String productId;	// User와 관련 있다.
	private String userId;		// Product와 관련 있다.
	private double rate;		// 별점
	private String content;		// 내용
	
	// 기본 생성자
	Review(){}
	
	// 모든 정보를 다 받는 생성자
	Review(String objectId, String productId, String userId, double rate, String content){
		this.objectId = objectId;
		this.productId = productId;
		this.userId = userId;
		this.rate = rate;
		this.content = content;
	}
	
	// getter, setter
	public String getObjectId() { return objectId; }

	public String getProductId() { return productId; }

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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