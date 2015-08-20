package cosmantic.cosmantic_khw;

/**
 * Created by secpc on 2015-08-20.
 */
public class WebContents {

    private byte[] image;    //콘텐츠 이미지
    private String title;   //콘텐츠 제목
    private String subtitle;    //콘텐츠 부제목
    private String user_objectId;
    private String product_objectId;
    private String url;
    //default 생성자
    WebContents(){}
    //생성자
    WebContents(byte[] image, String title, String subtitle, String user_objectId, String product_objectId, String url){
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.user_objectId = user_objectId;
        this.product_objectId = product_objectId;
        this.url=url;
    }

    public User getUser() {    return ServerInteraction.getUserInform(user_objectId);   }

    public String getTitle() {   return title;    }

    public String getSubTitle() {  return subtitle;    }

    public String getUrl() {  return url;    }

    public Product getProduct() {
        return ServerInteraction.getProductInform(product_objectId);
    }
    public byte[] getImage() { return image; }

}
