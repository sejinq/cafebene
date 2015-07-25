package cosmantic.cosmantic_khw;

import android.app.Application;

import com.kakao.auth.Session;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Session.initialize(this);
    }
}
