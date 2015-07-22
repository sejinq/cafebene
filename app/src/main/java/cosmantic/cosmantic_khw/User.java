package cosmantic.cosmantic_khw;

public class User {
	// 플래그
	// 피부 타입
	public static final int SKIN_TYPE_UNKNOWN = 0;			// 모름
	public static final int SKIN_TYPE_OILY = 1;				// 지성
	public static final int SKIN_TYPE_DRY = 2;					// 건성
	public static final int SKIN_TYPE_COMPLEX = 3;			// 복합성
	
	// 관심 효능
	public static final int INEREST_SHARE = 1;					// 보습
	public static final int INEREST_TROUBLE = 2;				// 여드름 개선
	public static final int INEREST_WRINKLE = 3;				// 주름 개선
	public static final int INEREST_PORE = 4;					// 모공 축소
	public static final int INEREST_SUN = 5;					// 자외선 차단
	public static final int INEREST_WHITE = 6;					// 미백
	public static final int INEREST_REPAIR = 7;				// 피부 재생
	public static final int INEREST_SCRUB = 8;					// 각질 제거
		
	// 멤버 변수
	private String objectId;
	private String username;				// 아이디
	private String password;				// 비밀번호
	private String displayedName;		// 닉네임
	private boolean gender;				// 성별
	private int age;						// 나이
	private String skinType;				// 피부 타입
	private String[] skinProblem;		// 피부 문제
	private String[] review;				// 리뷰(사용자 찜 목록)
	
	// 기본 생성자
	User(){}
	
	// 모든 정보를 다 받는 생성자
	User(String objectId, String username, String password, String displayedName, boolean gender, int age, String skinType, String[] skinProblem, String[] review){
		this.objectId = objectId;
		this.username = username;
		this.password = password;
		this.displayedName = displayedName;
		this.gender = gender;
		this.age = age;
		this.skinType = skinType;
		this.skinProblem = skinProblem;
		this.review = review;
	}
	
	// getter, setter
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDisplayedName() {
		return displayedName;
	}
	public void setDisplayedName(String displayedName) {
		this.displayedName = displayedName;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSkinType() {
		return skinType;
	}
	public void setSkinType(String skinType) {
		this.skinType = skinType;
	}
	public String[] getSkinProblem() {
		return skinProblem;
	}
	public void setSkinProblem(String[] skinProblem) {
		this.skinProblem = skinProblem;
	}
	public String[] getReview() {
		return review;
	}
	public void setReview(String[] review) {
		this.review = review;
	}
}