package cosmantic.cosmantic_khw;

/**
 * Created by secpc on 2015-08-20.
 */
public class WebContents {

    public interface ContentsType{
        int SKIN_TYPE_TEST = 0x1;// 피부타입 테스트
        // 화장품 상식
        // 추천 리뷰
        // 성분
    }

    private byte[] image;    //콘텐츠 이미지
    private String title="제목임";   //콘텐츠 제목
    private String subtitle="부제목임";    //콘텐츠 부제목
    private String product_objectId="제품.";
    private String url="http://m.blog.naver.com/cosmeticforman/220456872674";
    //default 생성자
    WebContents(){}
    //생성자
    WebContents(byte[] image, String title, String subtitle, String product_objectId, String url){
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.product_objectId = product_objectId;
        this.url=url;
    }

    public String getTitle() {   return title;    }

    public String getSubTitle() {  return subtitle;    }

    public String getUrl() {  return url;    }

    public String getProductName() {
        return ServerInteraction.getProductInform(product_objectId).getProductName();
    }
    public byte[] getProductImage() {
        return ServerInteraction.getProductInform(product_objectId).getThumnail();
    }
    public String getProductBrand() {
        return ServerInteraction.getProductInform(product_objectId).getBrand();
    }
    public String getProduct_objectId() {
        return product_objectId;
    }
    public byte[] getImage() { return image; }

}
