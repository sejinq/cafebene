package cosmantic.cosmantic_khw;

import com.parse.ParseUser;

import java.util.Hashtable;
import java.util.List;

public class User {
    // 플래그
    // 피부 타입 & 상황별 추천 (추천 화장품들을 보여줄 때 필요)
    public static final int SKIN_TYPE_UNKNOWN = 0;      // 모름
    public static final int SKIN_TYPE_OILY = 1;       // 지성
    public static final int SKIN_TYPE_DRY = 2;        // 건성
    public static final int SKIN_TYPE_SENSITIVE = 3;    // 민감성
    public static final int SEASON_SOLDIER = 4;

    // 관심 효능
    public static final int INEREST_SHARE = 0;        // 보습
    public static final int INEREST_TROUBLE = 1;      // 여드름 개선
    public static final int INEREST_WRINKLE = 2;      // 주름 개선
    public static final int INEREST_PORE = 3;       // 모공 축소
    public static final int INEREST_SUN = 4;        // 자외선 차단
    public static final int INEREST_WHITE = 5;        // 미백
    public static final int INEREST_REPAIR = 6;       // 피부 재생
    public static final int INEREST_SCRUB = 7;        // 각질 제거

    // 유저 타입
    public interface UserType {
        int EMAIL = 0x0;
        int FACEBOOK = 0x1;
        int KAKAO = 0x2;
    }

    // 멤버 변수
    private int userType;
    private String objectId;
    private String username;      // 아이디
    private String password;      // 비밀번호
    private String displayedName;   // 닉네임
    private boolean gender;       // 성별
    private int age;          // 나이
    private byte[] image;           // 썸네일
    private int skinType;     // 피부 타입
    private int[] skinProblem;    // 피부 문제
    private String[] review;      // 리뷰
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
    public void initLike(List<String> likeList){
        likeProducts = new Hashtable<String,Boolean>();
        for(int loop = 0; loop< likeList.size(); loop++)
            likeProducts.put(likeList.get(loop),true);
    }

    public String[] getLikeProducts() {
        String[] keys = (String[])likeProducts.keySet().toArray();
        return keys;
    }
    public boolean isLike(String likeProducts)
    {
        if(this.likeProducts == null) initLike(ParseUser.getCurrentUser().getList("like"));
        if(this.likeProducts.get(likeProducts)!=null){
            return true;
        }
        else{
            return false;
        }
    }

    public String[] getLikeContents() {
        String[] keys = (String[])likeProducts.keySet().toArray();
        return keys;
    }
    public boolean isLikeContents(String likeContents)
    {
        if(this.likeProducts.get(likeContents)!=null){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean setLikeProducts(String likeProducts) {
        if(this.likeProducts.get(likeProducts)!=null){
            this.likeProducts.remove(likeProducts);
            return false;
        }
        else{
            this.likeProducts.put(likeProducts,true);
            return true;
        }
    }
    public String getObjectId() { return objectId; }

    public int getUserType(){return userType; }

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

    public boolean getGender() {
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