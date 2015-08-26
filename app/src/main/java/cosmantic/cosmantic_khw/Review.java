package cosmantic.cosmantic_khw;

public class Review {
	// 멤버 변수
	private String objectId;
	private String productObjectId="제품아이디";	// User와 관련 있다.
	private String userObjectId="아이디";		// Product와 관련 있다.
	private double rate=4.2;		// 별점
	private String content="이건 리뷰이다";		// 내용
	private String userDisplayedname="닉네임이당"; // user의 닉네임
	private byte[] thumnail; // user의 프로필 사진

	// 기본 생성자
	Review(){}
	
	// 모든 정보를 다 받는 생성자
	Review(String productObjectId, String userObjectId, String userDisplayedname, byte[] thumnail, double rate, String content){
		this.productObjectId = productObjectId;
		this.userObjectId = userObjectId;
		this.rate = rate;
		this.content = content;
		this.userDisplayedname = userDisplayedname;
		this.thumnail = thumnail;
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

	public byte[] getThumnail() {
		return thumnail;
	}


	public String getDisplayedname() {
		return userDisplayedname;
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