package cosmantic.cosmantic_khw;

import android.app.Application;

import com.kakao.auth.AuthType;
import com.kakao.auth.Session;

public class MyApplication extends Application {
    User user;
    Product product;

    @Override
    public void onCreate() {
        super.onCreate();
	    // 카카오톡으로만 로그인을 유도하고 싶으면서 계정이 없을때
	    // 계정생성을 위한 버튼도 같이 제공을 하고 싶다면
	    Session.initialize(this, AuthType.KAKAO_TALK_EXCLUDE_NATIVE_LOGIN);

    }
    public User getUser(){
        return user;
    }
    public void setUser(User user)
    {
        this.user = user;
    }
    public Product getProduct(){
        return product;
    }
    public void setProduct(Product product)
    {
        this.product = product;
    }
}
