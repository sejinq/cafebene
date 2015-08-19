package cosmantic.cosmantic_khw;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;

import com.kakao.auth.Session;
import com.parse.ParseCrashReporting;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.SimpleFacebookConfiguration;
import com.sromku.simple.fb.utils.Logger;

import java.util.Hashtable;

import java.util.Hashtable;

public class MyApplication extends Application {
    private Hashtable<String, Typeface> fontCache;
    //임시 데이터
    private User user = new User(0, "sejin", "sejin", "쏜", true, 21, 0, null);
    private Product product = new Product("올인원", 53000, "미샤", 500.0, "이것은\n물건\n이다", null, "지성?",
            "올이뉴", "이건\n큐레이션\n이다", (float)4.2, 100);

    @Override
    public void onCreate() {
        super.onCreate();
        fontCache = new Hashtable<String, Typeface>();

        Session.initialize(this);
        facebookSetting();
    }

    public void parseCreate(){
        ParseCrashReporting.enable(this);
    }

    public Typeface getFont(String name, Context context){
        Typeface tf = fontCache.get(name);
        if(tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), name);
            }
            catch (Exception e) {
                return null;
            }
            fontCache.put(name, tf);
        }
        return tf;
    }

    private void facebookSetting(){
        Logger.DEBUG_WITH_STACKTRACE = true;
        Permission[] permissions = new Permission[]{
                Permission.EMAIL,
                Permission.USER_PHOTOS
        };
        SimpleFacebookConfiguration config = new SimpleFacebookConfiguration.Builder()
                .setAppId(getResources().getString(R.string.facebook_app_key))
                .setNamespace("cosMantic")
                .setPermissions(permissions)
                .build();
        SimpleFacebook.setConfiguration(config);
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
    public Bitmap getImage(byte[] bytes)
    {
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bitmap;
    }
}
