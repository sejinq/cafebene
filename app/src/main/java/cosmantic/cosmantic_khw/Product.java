package cosmantic.cosmantic_khw;

/*손!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!111세~~~~~~~~~~~~~~~~~~~~~~진@@@@@@@@@@@@@@@@@@@@@@@@@ 변수, 메소드 추가함.별점 스코어, 리뷰한 사람 수*/
public class Product {
    // 멤버 변수
    private String objectId;
    private String productName;		// 제품명
    private int price;				// 가격
    private String brand;			// 브랜드
    private String brandKor;		// 브랜드 한글
    private byte[] thumnail;		// 썸네일
    private String size;			// 용량
    private double pricePerSize;    // 가격/크기
    private int[] effects;		    // 효능/태그
    private String skintype;		// 피부 타입
    private String type;			// 종류
    private String curatingInfo;	// 큐레이팅 정보
    private float score; //별점 스코어
    private int reviewNum; //리뷰한 사람 수
    private String description;
    private int[] ingredientCount;
    // 기본 생성자
    Product(){}

    // 모든 정보를 다 받아오는 생성자
    Product(String productName, int price, String brand, String size, double pricePerSize, String description, int[] effects, String skintype, String type, String curatingInfo, float score, int reviewNum){
        this.productName = productName;
        this.price = price;
        this.brand = brand;
        this.size = size;
        this.pricePerSize = pricePerSize;
        this.effects = effects;
        this.description = description;
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

    public void setDescription(String description) {	this.description = description;	}

    public String getDescription() {	return description;	}

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

    public String getBrandKor() {
        return brandKor;
    }

    public void setBrandKor(String brandKor) {
        this.brandKor = brandKor;
    }

    public byte[] getThumnail() {
        return thumnail;
    }

    public void setThumnail(byte[] thumnail) {
        this.thumnail = thumnail;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPricePerSize() {
        return pricePerSize;
    }

    public void setPricePerSize(double pricePerSize) {
        this.pricePerSize = pricePerSize;
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

    public void setIngredientCount(int[] ingredientCount){
        this.ingredientCount = ingredientCount;
    }
    public static interface IngredientCountType{
        int OILY_POSITIVE = 0;
        int OILY_NEGATIVE = 1;
        int DRY_POSITIVE = 2;
        int DRY_NEGATIVE = 3;
        int SENSITIVE_POSITIVE = 4;
        int SENSITIVE_NEGATIVE = 5;
    }
    public int getIngredientCount(int countType){
        return ingredientCount[countType];
    }
}
