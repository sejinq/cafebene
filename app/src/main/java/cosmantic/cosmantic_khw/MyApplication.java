package cosmantic.cosmantic_khw;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import com.facebook.FacebookSdk;
import com.kakao.auth.Session;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.Hashtable;

public class MyApplication extends Application {
    private Hashtable<String, Typeface> fontCache;
    private User user;
    private Product product;

    @Override
    public void onCreate() {
        super.onCreate();
        fontCache = new Hashtable<String, Typeface>();

        Session.initialize(this);
        facebookSetting();

        parseCreate();
    }

    public void parseCreate(){
        ParseCrashReporting.enable(this);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);

        ParseFacebookUtils.initialize(getApplicationContext());
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
        FacebookSdk.sdkInitialize(getApplicationContext());
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
