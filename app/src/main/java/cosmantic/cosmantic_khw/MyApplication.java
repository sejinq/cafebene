package cosmantic.cosmantic_khw;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import com.kakao.auth.AuthType;
import com.kakao.auth.Session;

import java.util.Hashtable;

public class MyApplication extends Application {
    private Hashtable<String, Typeface> fontCache;
    @Override
    public void onCreate() {
        super.onCreate();
	    // 카카오톡으로만 로그인을 유도하고 싶으면서 계정이 없을때
	    // 계정생성을 위한 버튼도 같이 제공을 하고 싶다면
	    Session.initialize(this, AuthType.KAKAO_TALK_EXCLUDE_NATIVE_LOGIN);

        // Fontapplyer fontCache
        fontCache = new Hashtable<String, Typeface>();
    }

    public Typeface getFont(String name, Context context){
        Typeface tf = fontCache.get(name);

        if(tf==null){
            try{
                tf=Typeface.createFromAsset(context.getAssets(), name);
            }
            catch(Exception e){
                return null;
            }
            fontCache.put(name, tf);
        }
        return tf;
    }
}
