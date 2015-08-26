package cosmantic.cosmantic_khw;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.TextView;

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
    //임시 데이터
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

    //
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
    public Bitmap getImage(byte[] bytes)
    {
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bitmap;
    }
    public void settingStar(ImageView[] star, TextView average)
    {
        int aver = Math.round(getProduct().getScore());
        String text = getProduct().getScore()+" (";
        for(int i=0;i<aver;++i)
        {
            star[i].setImageResource(R.drawable.star_inable);
        }
        aver = getProduct().getReviewNum();
        text += aver+"명)";
        /*리뷰한 사람의 수와 별점의 평균을 수치화해서 보여준다. ex) 4.5(100명) */
        average.setText(text);
    }
    public interface action_bar_tag{
        public static final int AC_HOME = 0x0;		 //기초상식
        public static final int AC_SUB = 0x1; //추천제품리뷰
    }
}
