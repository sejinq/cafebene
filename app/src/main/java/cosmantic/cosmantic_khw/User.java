package cosmantic.cosmantic_khw;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class User {
	// 플래그
	// 피부 타입
	public static final int SKIN_TYPE_UNKNOWN = 0;			// 모름
	public static final int SKIN_TYPE_OILY = 1;				// 지성
	public static final int SKIN_TYPE_DRY = 2;				// 건성
	public static final int SKIN_TYPE_SENSITIVE = 3;		// 복합성
	
	// 관심 효능
	public static final int INEREST_SHARE = 0;				// 보습
	public static final int INEREST_TROUBLE = 1;			// 여드름 개선
	public static final int INEREST_WRINKLE = 2;			// 주름 개선
	public static final int INEREST_PORE = 3;				// 모공 축소
	public static final int INEREST_SUN = 4;				// 자외선 차단
	public static final int INEREST_WHITE = 5;				// 미백
	public static final int INEREST_REPAIR = 6;				// 피부 재생
	public static final int INEREST_SCRUB = 7;				// 각질 제거
  
	// 멤버 변수
    private int userType;
	private String objectId;
	private String username;			// 아이디
	private String password;			// 비밀번호
	private String displayedName;		// 닉네임
	private boolean gender;				// 성별
	private int age;					// 나이
    private byte[] image;		        // 썸네일
    private int skinType;			// 피부 타입
	private int[] skinProblem;		// 피부 문제
	private String[] review;			// 리뷰
    private Hashtable<String,Boolean> likeProducts;//사용자 찜 목록

  // 기본 생성자
	User(){}
	
	// 모든 정보를 다 받는 생성자
	User(int userType, String username, String password, String displayedName, boolean gender, int age, int skinType, int[] skinProblem){
	    this.userType = userType;
        this.username = username;
		this.password = password;
		this.displayedName = displayedName;
		this.gender = gender;
		this.age = age;
    this.skinType = skinType;
    this.skinProblem = skinProblem;
  }

	// getter, setter

  public ArrayList<String> getLikeProducts() {
      Set<String> keys = likeProducts.keySet();
     return null;
    }

  public void setLikeProducts(String likeProducts) {
    if(this.likeProducts.get(likeProducts)!=null){
      this.likeProducts.remove(likeProducts);
    }
    else{
      this.likeProducts.put(likeProducts,true);
    }
  }
  public String getObjectId() { return objectId; }

  public String getUsername() { return username; }

  public void setUsername(String username) { this.username = username; }

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

  public byte[] getImage() { return image; }

  public void setImage(byte[] image) { this.image = image; }

  public int getSkinType() {
    return skinType;
  }

  public void setSkinType(int skinType) {
    this.skinType = skinType;
  }

  public int[] getSkinProblem() {
    return skinProblem;
  }

  public void setSkinProblem(int[] skinProblem) {
    this.skinProblem = skinProblem;
  }

  public String[] getReview() {
    return review;
  }

  public void setReview(String[] review) {
    this.review = review;
  }
}