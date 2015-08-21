package cosmantic.cosmantic_khw;

public class Brand {
    // 멤버 변수
    private String objectId;
    private byte[] thumb;
    private String brandName;       // 브랜드 이름
    private int productNum;         // 제품 등록수

    // 기본 생성자
    Brand(){}

    // 모든 정보를 다 받아오는 생성자
    Brand(String brandName, int productNum){
        this.brandName = brandName;
        this.productNum = productNum;
    }

    Brand(String objectId, byte[] thumb, String name, int productNum){
        this.objectId = objectId;
        this.brandName = name;
        this.productNum = productNum;
    }

    // getter, setter
    public String getObjectId() { return objectId; }

    public String getBrandName() {
        return brandName;
    }
    public byte[] getThumnail() {
        return thumb;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum){
        this.productNum = productNum;
    }
}
